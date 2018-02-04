## Spring Boot AngularJS Application
##### Read and Uplaod CSV file 

This is a Spring boot & AngularJS Application. With the help of this application, we can upload and read CSV.

Below you will find some information on how to do setup and run it.

### Prerequisites
1.  AngularJS
	- NPM
	- Bower
1. Spring Boot
	- Java 8
	- Sprint Boot 1.5.9.RELEASE
	- Maven latest
	- MySQL or H2 (With the dev profile you can use H2, but with the Prod Profile you need to use MySQL)
1. Docker
	- Docker version 17.12.0-ce 
	
### Getting Started

#### Deplyment Process 
1. AngularJS
	- npm install
	- bower install
	- grunt serve
1. Spring Boot
	- mvn clean
	- mvn  package
	- java -jar target/clustered-data-warehouse-1.0.0.jar

### Docker Configuraiton (Docker command and configuration will work only With H2 Database)
	- mvn clean install (Build the applicaiton)
	- mvn install dockerfile:build (Build the Docker Image)	
	- docker images (For checking the created docker )
	- docker run -p 8080:8080 -t springio/clustered-data-warehouse (Run the Docker Image with port)
	- docker ps ( For seeing the running container)
