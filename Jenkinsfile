pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Récupération du code source depuis Git'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Compilation du projet'
                bat 'mvn clean compile'
            }
        }

        stage('Unit Tests') {
            steps {
                echo 'Exécution des tests unitaires'
                bat 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Analyse de la qualité du code avec SonarQube'
                withSonarQubeEnv('SonarQube') {
                    bat 'mvn verify sonar:sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                echo 'Vérification du Quality Gate'
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline Jenkins exécuté avec succès '
        }
        failure {
            echo 'Pipeline Jenkins échoué '
        }
    }
}
