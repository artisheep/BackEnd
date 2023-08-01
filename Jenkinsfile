pipeline {
    agent any



    environment {
        GIT_URL = "https://github.com/artisheep/BackEnd"
        IMAGE_NAME =  "ganghoon"
    }

    stages {
        stage('Pull') {
                steps {
                    git url: "${GIT_URL}", branch: "develop", poll: true, changelog: true
                }

        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${IMAGE_NAME} ."
                }
            }
        }

        stage('Finish') {
            steps{
                sh 'docker images -qf dangling=true | xargs -I{} docker rmi {}'
            }
        }


    }




}
