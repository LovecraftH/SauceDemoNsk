pipeline {
    agent any // Использовать любой доступный агент Jenkins для выполнения pipeline

    stages {
        // === Этап 2: Запуск тестов (если не выбран пропуск) ===
        stage('Test') {
            steps {
                script {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean test'
                }
            }
        }
    }

    // === Пост-условия: выполняются после всех этапов, независимо от результата ===
    post {
        always {
            script {
                // Генерируем Allure-отчет, если выбран соответствующий параметр
                allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
            }
        }
    }

}
