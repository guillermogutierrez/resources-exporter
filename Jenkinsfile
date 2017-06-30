pipeline {
    agent any
    tools {
        maven 'Maven 3.5.0'
        jdk 'jdk8'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
            post {
                success {
                    sh 'cp target/*.jar /Users/guillermo/Liferay/bundles/DXP/SP3/platform-demo/deploy'  
                }
            }
        }
    }
}