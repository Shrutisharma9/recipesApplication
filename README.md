# Recipes Application
A project to manage recipes and its ingredients. Using Java, springboot, maven, h2 db to perform CRUD operation and swagger to document, test and consume RESTful web services.

## Introduction
This is the project to add new recipes and its ingredients, delete or update recipes or ingredients. Search for recipes based on different criteria, list of recipes and ingredient.
When execute this project please open http://localhost:9000/swagger-ui.html to test results on swagger-ui.

## Technology & Version used
#### JAVA 11
    Download java 11 openJDK
    extract zip or install if its .exe file
    add JAVA_HOME in system variable
    add Path in environment variable 
    verify java: open command prompt and execute java -version
#### Maven 3.8.6
    Download maven from https://maven.apache.org/download.cgi 
    Extract folder
    Add MAVEN_HOME in system variable
    Add Maven Path in environment variable
    verify maven: open command prompt and execute mvn -version    

#### Springboot 2.7.7
#### H2 database 2.1.214
#### Swagger 2.9.2
#### JUnit, CSS, Freemarker
### How to run locally
To build project:

    mvn clean install

Then to run the project:

    java -jar /target/recipesApp-0.0.1.jar
    or
    mvn spring-boot:run
    
### Result page screenshots

##### Home page

![image](https://user-images.githubusercontent.com/117658621/212176519-573e4d5f-8125-47b3-9ad3-be5952ad93db.png)

##### Swagger-ui to test CRUD Operations

![image](https://user-images.githubusercontent.com/117658621/212176603-3da27f6f-8d2b-4e2c-a61e-f834c2e3911b.png)

##### Result after click on Recipe List button

![image](https://user-images.githubusercontent.com/117658621/212178078-c40a952e-497f-4359-a31d-d7eaba8acdcf.png)


