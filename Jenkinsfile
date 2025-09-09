pipeline {
    agent any

    environment {
        IMAGE_NAME = 'bookmyshow-app'
        CONTAINER_NAME = 'bookmyshow-container'
    }

    stages {
        stage('Build Docker Image') {
            steps {
                bat "docker build -t %IMAGE_NAME% ."
            }
        }

        stage('Run Container') {
            steps {
                bat "docker run -d --name %CONTAINER_NAME% %IMAGE_NAME%"
            }
        }

        stage('Run Tests in Container') {
            steps {
                bat "docker exec %CONTAINER_NAME% mvn test"
            }
        }

        stage('Show Running Containers') {
            steps {
                bat "docker ps"
            }
        }
    }

    post {
        always {
            echo 'Inspect the running container now in Docker Desktop or via CLI.'
            echo 'You can also run: docker exec -it my-running-container /bin/bash'
            sleep(time: 30, unit: 'SECONDS')
            bat "docker stop %CONTAINER_NAME% || exit 0"
        }
    }
}
