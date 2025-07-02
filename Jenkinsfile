pipeline {
    any agent

    stages {
        stage('Test') {
            steps {
                script {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean test'
                }
            }
        }
    }
    post {
        always {
            script {
                allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
            }
        }
    }
}
