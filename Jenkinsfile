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
                bat 'C:\\Users\\NANDHEESWARAN\\Downloads\\apache-maven-3.9.16\\bin\\mvn.cmd -version'
            }
        }

        stage('Compile') {
            steps {
                bat 'C:\\Users\\NANDHEESWARAN\\Downloads\\apache-maven-3.9.16\\bin\\mvn.cmd -B clean compile'
            }
        }

        stage('Unit Tests') {
            steps {
                bat 'C:\\Users\\NANDHEESWARAN\\Downloads\\apache-maven-3.9.16\\bin\\mvn.cmd -B test'
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
                bat 'C:\\Users\\NANDHEESWARAN\\Downloads\\apache-maven-3.9.16\\bin\\mvn.cmd -B package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar',
                                 fingerprint: true
            }
        }

        stage('Verify JAR') {
            steps {
                bat 'java -jar target\\java-ci-demo-1.0.0.jar'
                withCredentials([
                    string(
                        credentialsId: 'paasword'
                        variable: 'jenkins_Password'
                        )
                    ])
            }
        }
    }

    post {
        success {
            echo 'CI SUCCESS: Build, tests and packaging completed!'
            echo '$jenkins_Password'
        }

        failure {
            echo 'CI FAILED: Check the Jenkins console output.'
        }

        always {
            echo "Build number: ${BUILD_NUMBER}"
        }
    }
}
