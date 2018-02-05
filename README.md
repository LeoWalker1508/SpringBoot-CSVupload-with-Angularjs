## Spring Boot AngularJS Application
##### Read and Upload CSV file 

This is a Spring boot & AngularJS Application. With the help of this application, we can upload and read CSV.

### Prerequisites
1.  AngularJS
	- Node
	- NPM
	- Bower
	
1. Spring Boot
	- Java 8
	- Sprint Boot 1.5.9.RELEASE
	- Maven latest
	- MySQL or H2 (With the dev profile you can use H2, but with the Prod Profile you need to use MySQL)
1. Docker	 
	
### Getting Started

#### Deployment Process 
1. AngularJS
	- npm install
	- bower install
	- grunt serve
1. Spring Boot
	- mvn clean
	- mvn package
	- java -jar target/clustered-data-warehouse-1.0.0.jar

### Docker Configuration (Docker commands and configuration will work only With H2 Database)
- mvn clean install (Build the application)
- mvn install dockerfile:build (Build the Docker Images)	
- docker images (For checking the Docker created Images )
- docker run -p 8181:8181 -t springio/clustered-data-warehouse (Run the Docker Image with port)
- docker ps ( For seeing the running container)
- docker stop 4f59bee2deb2(Container Id)
- docker rm '< container Id> '
- docker Stop  Container
