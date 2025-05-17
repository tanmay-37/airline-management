pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-17'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
    environment {
        JAR_NAME = "airline-0.0.1-SNAPSHOT.jar"
        REMOTE_USER = "ec2-user"
        REMOTE_HOST = "your-ec2-public-dns" // TODO: Replace with your EC2 public DNS
        REMOTE_PATH = "/home/ec2-user/airline"
        SSH_KEY = credentials('ec2-ssh-key')
    }
    stages {
        stage('Validate Environment') {
            steps {
                sh '''
                    java -version
                    mvn -version
                '''
            }
        }
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests=false'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Deploy to EC2') {
            steps {
                // Ensure directory exists and clean old deployment
                sh '''
                    ssh -i $SSH_KEY -o StrictHostKeyChecking=no $REMOTE_USER@$REMOTE_HOST "
                        mkdir -p $REMOTE_PATH
                        rm -f $REMOTE_PATH/$JAR_NAME
                    "
                    scp -i $SSH_KEY -o StrictHostKeyChecking=no target/$JAR_NAME $REMOTE_USER@$REMOTE_HOST:$REMOTE_PATH/
                '''
            }
        }
        stage('Start Application') {
            steps {
                sh '''
                    ssh -i $SSH_KEY -o StrictHostKeyChecking=no $REMOTE_USER@$REMOTE_HOST "
                        echo 'Stopping existing application...'
                        pkill -f $JAR_NAME || true
                        sleep 5
                        echo 'Starting application...'
                        nohup java -jar $REMOTE_PATH/$JAR_NAME > $REMOTE_PATH/app.log 2>&1 &
                        sleep 10
                        echo 'Application logs:'
                        tail -n 50 $REMOTE_PATH/app.log
                    "
                '''
            }
        }
        stage('Health Check') {
            steps {
                sh '''
                    sleep 30
                    curl -f http://$REMOTE_HOST:8082/actuator/health || exit 1
                '''
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
