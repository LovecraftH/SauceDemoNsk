pipeline {
    agent {
        docker {
            image 'openjdk:17'
            args  '--add-host=host.docker.internal:host-gateway'
        }
    }
    environment {
        // Указываем Docker CLI внутри контейнера, куда дергать Daemon
        DOCKER_HOST = 'tcp://host.docker.internal:2375'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('UI Tests') {
            steps {
                sh './gradlew clean test -Drun.mode=selenoid -Dselenoid.url=http://selenoid:4444/wd/hub'
            }
        }
    }
    post {
        always {
            // Этот шаг теперь выполняется внутри того же agent и workspace
            archiveArtifacts artifacts: 'build/reports/**/*, build/test-results/**/*', allowEmptyArchive: true
        }
    }
}
