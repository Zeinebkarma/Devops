pipeline {
    agent any
    stages {
        
        stage ('Checkout GIT'){
			steps{
				echo 'Pulling...';
					git branch: 'ons',
					url : 'https://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@https://github.com/Zeinebkarma/Devops.git';
			}
		}
        stage('MVN Clean') { 
            steps {
                 sh 'mvn clean'
            }
        }
        stage('MVN Compile') { 
            steps {
                 sh 'mvn compile'
            }
        }
               
        }
         
    }
