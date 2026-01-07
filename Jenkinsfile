pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Tests') {
            steps {
                bat 'mvn clean verify'
            }
        }

        stage('Analyse SonarQube') {
            steps {
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    bat '''
                        mvn sonar:sonar ^
                        -Dsonar.host.url=http://localhost:9000 ^
                        -Dsonar.projectKey=todo-project ^
                        -Dsonar.projectName=todo-project ^
                        -Dsonar.token=%SONAR_TOKEN%
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline Jenkins terminé avec succès '
        }
        failure {
            echo 'Pipeline Jenkins échoué '
        }
    }
}
