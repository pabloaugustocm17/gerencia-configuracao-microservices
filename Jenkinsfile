pipeline {
    agent any
    
    stages {
        stage('Checkout repositório') {
            steps {
                git branch: 'master', url: 'https://github.com/pabloaugustocm17/gerencia-configuracao-microservices.git'
            }
        }
        
        stage('Permissão para utilizar mvnw') {
            steps {
                dir('./tpgerencia') {
                    sh 'chmod +x mvnw'
                }
            }
        }
        
        stage('Build') {
            steps {
                dir('./tpgerencia') {
                    sh './mvnw clean compile'
                }
            }
        }
        
        stage('Test') {
            steps {
                dir('./tpgerencia') {
                    sh './mvnw test'
                }
            }
        }
    }
    
    post {
        success {
            echo 'Build e teste bem-sucedidos!'
        }
        failure {
            echo 'Build ou teste falhou!'
        }
    }
}
