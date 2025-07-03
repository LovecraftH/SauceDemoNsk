pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                // Клонируем репозиторий (по умолчанию Jenkins сам делает checkout, но явно — лучше для читаемости)
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                    // Сборка проекта (например, если есть сборка артефактов)
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean build -x test'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Запуск автотестов
                    sh './gradlew test'
                }
            }
        }

        stage('Allure Report') {
            steps {
                script {
                    // Генерация Allure-отчёта (плагин Allure должен быть установлен в Jenkins)
                    allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
                }
            }
        }
    }

    post {
        always {
            script {
                // Можно добавить дополнительные действия, например, архивировать артефакты
                archiveArtifacts artifacts: 'build/reports/**/*.*', allowEmptyArchive: true
            }
        }
    }
}
