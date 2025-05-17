# Airline Management System

## Prerequisites
1. AWS Account
2. EC2 Instance for Jenkins (t2.small or larger recommended)
3. EC2 Instance for Application deployment
4. Docker installed on Jenkins server

## Setup Instructions

### 1. Jenkins Server Setup
1. Launch EC2 instance for Jenkins:
   ```bash
   # Update system
   sudo yum update -y
   
   # Install Docker
   sudo yum install docker -y
   sudo service docker start
   sudo usermod -aG docker ec2-user
   
   # Install Jenkins
   sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
   sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io-2023.key
   sudo yum install jenkins java-17-amazon-corretto -y
   sudo systemctl start jenkins
   sudo systemctl enable jenkins
   ```

2. Access Jenkins:
   - [Open `http://<jenkins-server-ip>:8080`](http://<jenkins-server-ip>:8080)
   - Get initial password: `sudo cat /var/lib/jenkins/secrets/initialAdminPassword`
   - Install suggested plugins
   - Create admin user

### 2. Jenkins Configuration
1. Install Required Plugins:
   - Docker Pipeline
   - Pipeline
   - Git

2. Add Credentials:
   - Go to Manage Jenkins > Credentials > System > Global credentials
   - Add AWS EC2 SSH key (Kind: SSH Username with private key)
   - ID: `ec2-ssh-key`

### 3. AWS EC2 Application Server Setup
1. Launch EC2 instance for application
2. Install Java:
   ```bash
   sudo yum update -y
   sudo yum install java-17-amazon-corretto -y
   ```

### 4. Pipeline Setup
1. Create new Pipeline job in Jenkins
2. Configure Pipeline:
   - Definition: Pipeline script from SCM
   - SCM: Git
   - Repository URL: Your repository URL
   - Credentials: Add if repository is private
   - Branch: */main
   - Script Path: Jenkinsfile

### 5. Security Group Configuration
1. Jenkins Server:
   - Inbound: TCP 8080 (Jenkins UI)
   - Inbound: TCP 22 (SSH)

2. Application Server:
   - Inbound: TCP 8082 (Application port)
   - Inbound: TCP 22 (SSH)

## Running the Pipeline
1. Open Jenkins pipeline job
2. Click "Build Now"
3. Monitor build progress in Stage View

## Accessing the Application
After successful deployment, access the application at:
`http://<application-server-ip>:8082`