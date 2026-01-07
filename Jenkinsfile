pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        SONAR_PROJECT_KEY = 'Todo_Project'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn verify sonar:sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t todo-app .'
            }
        }
    }
}
