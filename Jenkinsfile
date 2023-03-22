pipeline {
	agent any
	
	stages {
		stage ('Compile Stage') {
			steps {
				withMaven(maven: 'apache-maven-3.8.4') {
					bat 'mvn clean install'
				}
			}
		}
		
		stage ('Test Stage') {
			steps {
				withMaven(maven: 'apache-maven-3.8.4') {
					bat 'mvn test'
				}
			}
		}
		stage ('Cucumber Reports') {
			steps {
				cucumber buildStatus: "UNSTABLE",
				fileIncludePattern: "**/cucumber-report.json",
				jsonReportDirectory: 'target'
			}
		}
	}
}