pipeline {
    agent any

    parameters {
        choice(
                name: 'RUN_MODE',
                choices: ['local', 'selenoid'],
                description: 'Выберите режим запуска тестов: local или selenoid'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean build -x test'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Передаём выбранный режим как системное свойство
                    sh "./gradlew test -Drun.mode=${params.RUN_MODE}"
                }
            }
        }

        stage('Allure Report') {
            steps {
                script {
                    allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
                }
            }
        }
    }

    post {
        always {
            script {
                archiveArtifacts artifacts: 'build/reports/**/*.*', allowEmptyArchive: true
            }
        }
    }
}
