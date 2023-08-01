pipeline {
    agent any

    environment {
        GIT_URL = "https://github.com/SwaveReleaseNote/BackEnd"
        IMAGE_NAME = "urynure"
    }

    stages {
        stage('Pull') {
            steps {
                git url: "${GIT_URL}", branch: "develop", poll: true, changelog: true
            }
        }

        stage('Build') {
            steps {
                script {
                    sh "chmod +x gradlew"
                    sh  './gradlew bootJar'
                }
            }
        }

        stage('Prepare Docker Build') {
            steps {
                // 빌드된 .jar 파일을 복사하여 Docker 이미지 빌드 디렉토리로 이동합니다.
                sh "cp ./build/libs/ReleaseNoteShareSystem-0.0.1-SNAPSHOT.jar ./src/docker-springboot.jar"
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${IMAGE_NAME} ./src/"
                }
            }
        }

        stage('Finish') {
            steps {
                sh 'docker images -qf dangling=true | xargs -I{} docker rmi {}'
            }
        }
    }
}
