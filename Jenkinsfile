pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 15, unit: 'MINUTES')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo 'Source code checked out successfully'
            }
        }

        stage('Check Tools') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Compile') {
            steps {
                bat 'mvn -B clean compile'
            }
        }

        stage('Unit Tests') {
            steps {
                bat 'mvn -B test'
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/*.xml',
                          allowEmptyResults: true
                }
            }
        }

        stage('Package') {
            steps {
                bat 'mvn -B package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar',
                                 fingerprint: true
            }
        }

        stage('Verify JAR') {
            steps {
                bat 'java -jar target\\java-ci-demo-1.0.0.jar'
            }
        }
    }

    post {
        success {
            echo 'CI SUCCESS: Build, tests and packaging completed!'
        }

        failure {
            echo 'CI FAILED: Check the Jenkins console output.'
        }

        always {
            echo "Build number: ${BUILD_NUMBER}"
        }
    }
}
