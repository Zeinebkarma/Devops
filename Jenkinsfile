pipeline{

	agent any

	/*environment {
        dockerhub=credentials('dockerhub')
	}*/ /*groovy*/

	stages {
	    
                stage ('Git') {
            steps {
                
                //git 'https://github.com/Zeinebkarma/Devops

                git branch: 'zeinebkarma', 
                url: 'https://github.com/Zeinebkarma/Devops'

                 
		        
            }
        }
        stage ('Maven Clean') {
            steps {

                sh 'mvn clean'
            }
        }
        stage ('Maven Compile') {
            steps {

                sh 'mvn compile'
            }
        }
        
        

        
        stage ('Maven Test Sonar') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=mootez'
            }

        }

        stage ('Maven Test JUnit') {
            steps {
                echo 'mvn test'
            }
        }
        stage ('Maven Deploy Nexus') {
            steps {
                sh'mvn clean deploy -Dmaven.test.skip=true -Dresume=false'
            }
        }


		stage('Build image') {

			steps {
				sh 'docker build -t oussemakessentini/achat:latest .'
			}
		}

		stage('Login docker hub') {

			steps {

			sh'docker login -u <Zeinebkarma> -p <Docker2022>'	
			}
		}

		stage('Push image') {

			steps {
				sh 'docker push oussemakessentini/achat:latest'
			}
		}
		
		stage('Docker-Compose Up') {

			steps {
				sh 'docker-compose up'
			}
		}
		
		stage('Docker-Compose Down') {

			steps {
				sh 'docker-compose down'
			}
		}
		
	}

/*	post {
		always {
			sh 'docker logout'
		}
	}*/

}