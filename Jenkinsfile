 pipeline{
    agent any
    stages {
        
        stage ('Checkout GIT'){
			steps{
				echo 'Pulling...';
					git branch: 'ons',
					url : 'https://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@github.com/Zeinebkarma/Devops.git';
			}
		}
        stage('MVN Clean') { 
            steps {
                 sh 'mvn clean'
            }
        }/*
        stage('BUILD') { 
            steps {
                 sh 'mvn -B -DskipTests clean package';
            }
        }
	    stage ('MVN SONAR') {
		    steps {
			    sh 'mvn sonar:sonar -D sonar.login=admin -D sonar.password=sonar'
		    }
	    }
	    stage ('Build docker image'){
		    steps{
			    sh'docker build -t mogaadions/achat .'
		    }
	    }
	    
	    stage ('DockerHub Push'){
		    steps{
			    sh '''docker login -u mogaadions -p ${DockerHubPassword} 
				docker push mogaadions/achat'''
		    }
	    }
	    stage ('NEXUS'){
		    steps{
			    sh 'mvn clean deploy -DskipTests'
		    }
	    }*/
	    stage("DockerCompose") {
			 steps {
				sh 'docker-compose up -d'
			}
		}
	    stage ("JUNIT / MOCKITO") {
	    	steps {
			sh 'mvn test'
		}
	    }	
    }
 }
