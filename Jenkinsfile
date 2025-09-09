pipeline {
    agent any

    environment {
        IMAGE_NAME = 'my-custom-maven-image'
        CONTAINER_NAME = 'my-running-container'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Run Container') {
            steps {
                sh 'docker run -d --name $CONTAINER_NAME $IMAGE_NAME'
            }
        }

        stage('Run Tests in Container') {
            steps {
                sh 'docker exec $CONTAINER_NAME mvn clean test'
            }
        }

        stage('Show Running Containers') {
            steps {
                sh 'docker ps'
            }
        }
    }

    post {
        always {
            echo 'Inspect the running container now in Docker Desktop or via CLI.'
            echo 'You can also run: docker exec -it my-running-container /bin/bash'
            sleep time: 30, unit: 'SECONDS'
            sh 'docker stop $CONTAINER_NAME || true'
            sh 'docker rm $CONTAINER_NAME || true'
        }
    }
}
