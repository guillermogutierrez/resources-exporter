pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                withMaven(maven: 'M3',
                    // Maven settings.xml file defined with the Jenkins Config File Provider Plugin
                    // Maven settings and global settings can also be defined in Jenkins Global Tools Configuration
                    mavenSettingsConfig: 'my-maven-settings',
                    mavenLocalRepo: '.repository') {
                        // Run the maven build
                        sh "mvn clean install"
                    } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}