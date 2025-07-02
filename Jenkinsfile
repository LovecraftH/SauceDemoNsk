pipeline {
    agent any

    parameters {
        choice(
                name: 'RUN_MODE',
                choices: ['local', 'selenoid'],
                description: 'Режим запуска тестов: local – локально, selenoid – через Selenoid'
        )
        string(
                name: 'SELENOID_URL',
                defaultValue: 'http://localhost:4444/wd/hub',
                description: 'URL Selenoid (используется только в режиме selenoid)'
        )
    }

    environment {
        GRADLE_OPTS = '-Xmx1024m -Dorg.gradle.daemon=false'
    }

    stages {
        stage('Checkout') {
            steps {
                echo '[Checkout] Клонируем репозиторий'
                checkout scm
            }
        }

        stage('Validate Parameters') {
            steps {
                echo "[Validate] Проверяем RUN_MODE: ${params.RUN_MODE}"
                script {
                    if (!['local', 'selenoid'].contains(params.RUN_MODE)) {
                        error "Некорректное значение RUN_MODE: ${params.RUN_MODE}"
                    }
                }
            }
        }

        stage('Setup Gradle Wrapper') {
            steps {
                echo '[Setup] Выдаём права на выполнение gradlew'
                sh 'chmod +x ./gradlew'
            }
        }

        stage('Build') {
            steps {
                echo '[Build] Собираем проект без тестов'
                sh './gradlew clean build -x test'
            }
        }

        stage('UI Tests') {
            steps {
                echo "[UI Tests] Запускаем UI-тесты в режиме ${params.RUN_MODE}"
                script {
                    def cmd = ['./gradlew', 'clean', 'test', "-Drun.mode=${params.RUN_MODE}"]
                    if (params.RUN_MODE == 'selenoid') {
                        cmd << "-Dselenoid.url=${params.SELENOID_URL}"
                    }
                    sh cmd.join(' ')
                }
            }
        }
    }

    post {
        always {
            echo '[Post] Архивируем результаты и генерируем Allure-отчёт'
            archiveArtifacts artifacts: 'build/reports/**/*, build/test-results/**/*', allowEmptyArchive: true
            // Всегда генерируем Allure-отчёт
            allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
        }
        success {
            echo "✅ Сборка завершена успешно в режиме: ${params.RUN_MODE}"
        }
        failure {
            echo "❌ Сборка упала в режиме: ${params.RUN_MODE}. Проверьте логи."
        }
    }
}
