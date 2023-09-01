pipeline {
    agent any
    tools {
        maven 'Maven by Jenkins'
        jdk 'jdk-15.0.2'
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
        stage ('Test') {
            steps {
                sh 'mvn clean test' 
            }
            post {
                always {
                    
                    archiveArtifacts artifacts: 'target/cucumber.html'
                    
                    publishHTML target : [
                         allowMissing: false,
                         alwaysLinkToLastBuild: true,
                         keepAll: true,
                         reportDir: 'target',
                         reportFiles: 'cucumber.html',
                         reportName: 'cucumber-report',
                         reportTitles: 'cucumber-report'
                         ]
                         
                    sshPublisher (
                        continueOnError: false, 
                        failOnError: true,
                        publishers: [
                            sshPublisherDesc (
                            configName: "aws-ec2",
                            transfers: [
                                sshTransfer (
                                    sourceFiles: 'target/cucumber.html',
                                    removePrefix: 'target',
                                    remoteDirectory: '/cucumber-jenkins'
                                    )
                                ],
                            verbose: true
                            )
                        ]
                    )
                }
            }
        }
    }
}
