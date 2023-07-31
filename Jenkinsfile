pipeline {
    agent any
    stages {
        stage("Checkout") {
            steps {
                checkout scm
            }
        }
        stage("Build and Push") {
            steps {
                script {
                    def imageName = "cicd_test"

                    sh "docker-compose build ${imageName}"

                    withCredentials([[$class: 'UsernamePasswordMultiBinding',
                                    credentialsId: 'docker_credentials',
                                    usernameVariable: 'DOCKER_USER_ID',
                                    passwordVariable: 'DOCKER_USER_PASSWORD'
                                    ]]) {
                        sh "docker login -u ${DOCKER_USER_ID} -p ${DOCKER_USER_PASSWORD}"
                        sh "docker tag ${imageName}:latest ${DOCKER_USER_ID}/${imageName}:${BUILD_NUMBER}"
                        sh "docker push ${DOCKER_USER_ID}/${imageName}:${BUILD_NUMBER}"
                    }
                }
            }
        }
    }
}
