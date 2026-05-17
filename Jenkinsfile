pipeline {

    agent {
        label 'built-in'
    }

    environment {
        AWS_REGION = 'us-east-1'
        ECR_REPO = '950473445707.dkr.ecr.us-east-1.amazonaws.com/attendance-management'
    }

    stages {

        stage('Clone') {
            steps {
                git 'https://github.com/arya-hadekar/attendance-management.git'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t attendance-app:${BUILD_NUMBER} .'
            }
        }

        stage('Push to ECR') {

            steps {

                withCredentials([[
                                         $class: 'AmazonWebServicesCredentialsBinding',
                                         credentialsId: 'aws-creds'
                                 ]]) {

                    sh '''
                    aws ecr get-login-password --region $AWS_REGION | \
                    docker login --username AWS --password-stdin $ECR_REPO

                    docker tag attendance-app:${BUILD_NUMBER} \
                    $ECR_REPO:${BUILD_NUMBER}

                    docker tag attendance-app:${BUILD_NUMBER} \
                    $ECR_REPO:latest

                    docker push $ECR_REPO:${BUILD_NUMBER}

                    docker push $ECR_REPO:latest
                    '''
                }
            }
        }
    }
}