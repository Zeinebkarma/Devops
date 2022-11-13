pipeline {
    agent any
    
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "localhost:8081"
        NEXUS_REPOSITORY = "maven-nexus-repo"
        NEXUS_CREDENTIAL_ID = "nexus"	    
    }

    stages {
        stage('Pull GIT') {
            steps {
                echo 'Pulling...';
                  git branch: 'feature_SA',
                  url : 'https://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@github.com/Zeinebkarma/Devops.git',
                  credentialsId: 'githubtoken';
            }
        }
        
        stage('Maven Package') {
            steps {
		      sh "docker-compose up&"
                sh "chmod +x mvnw "
                sh "./mvnw package"
                
            }
        }

  stage('Test') {
            steps {
                sh "./mvnw test"	
		            junit '**/target/surefire-reports/TEST-*.xml'
           jacoco execPattern: 'target/jacoco.exec'
            } 
        }
	    
	
        
        
           
        
        stage('SonarQube analysis') {
            steps {
              withSonarQubeEnv (installationName:'sonar'){
              sh """./mvnw sonar:sonar \
  -Dsonar.projectKey=project-jenkins-pipeline \
  -Dsonar.host.url=http://localhost:9000 \
        -Dsonar.coverage.jacoco.xmlReportPaths=../app-it/target/site/jacoco-aggregate/jacoco.xml """ 
    
               
       }
    }   
}
        

stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                    pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
}
	    
	    stage('Build new image') {
		    steps{
			    withDockerRegistry([credentialsId: "docker-cred", url: ""]) {
 		            //sh 'printenv'
			    sh "docker build -t oussemabelaid7/devops:latest ."
			    
		    }
		    }
    }
	        stage('Push new image') {
			steps{
		withDockerRegistry([credentialsId: "docker-cred", url: ""]) {
  		sh "docker push oussemabelaid7/devops:latest"
			
		      sh "docker-compose down"
	}
			}
		}
	   
	    }   
}
