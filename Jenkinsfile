pipeline {
    agent {
        docker {
            image 'selenium-java-project'
        }
    }
    stages {
        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}
