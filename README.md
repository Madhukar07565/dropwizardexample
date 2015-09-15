# Introduction
Dropwizard JDBI Swagger example application was developed to, as its name implies, provide examples of some of the features present in Dropwizard, JDBI and Swagger.

#Overview
This Application contains the Dropwizard Helloworld example and Person to Perform CURD Operations.

#Running Application

*To package the example run.

      mvn package
      
*To run db migration script.

      java -jar target\1.0.1-0.0.1.jar migrate config.yaml
      
*To Run the server

      java -jar target\1.0.1-0.0.1.jar server config.yaml

#Rest URL's
Rest URL for HellowWorld Resource is 

     http://localhost:8080/hello 

Rest URL for Person Resource:  You can Navigate to the below URL to find what are the REST Url's existed for Person Resource.

#Swagger
Swagger URL : 

     http://localhost:8080/swagger 

In this page you can click on show/hide operation whicjh will display the list of resources to access.

#Health CheckUp

URL : 

    http://localhost:8081/healthcheckup
