pipeline {
    agent any
    tools {
        maven 'maven_3_9_8'
    }
    environment {
        IMAGE_NAME = "rodfeitosa28/plataformaead"
        TAG = "latest"
    }
    stages {
        stage('Build maven'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Rodrigo2898/eadflow-rr']])
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t $IMAGE_NAME:$TAG .'
                }
            }
        }
        stage('Push image to hub'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                       sh 'docker login -u rodfeitosa28 -p ${dockerhubpwd}'
                    }
                    sh 'docker push $IMAGE_NAME:$TAG'
                }
            }
        }
    }
}