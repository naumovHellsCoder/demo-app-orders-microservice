# demo-app-orders-microservice

Just a demo project.
A microservice for the orders of my demo spring application.
A user can read / write / update / delete documents and orders.

 # Java
 It requires Java version 17

# Build the App

```
./mvnw clean package
 java -jar target/orders-0.0.1.jar
 ```
 
 ### Or with docker
 ```
 docker-compose up
 ```
 
 # Database
 This app is with postgres configured. See application.properties.
 
 If you need a database local you can use
 ```
 docker-compose up -d orders-database
  ```
 
 Or use the "test" spring profile: 
 
  ```SPRING_PROFILES_ACTIVE=test ```
  
  With this profile a h2 databse will be used.
 
 # Api Docu
 
 * http://localhost:8080/swagger-ui/index.html
 * http://localhost:8080//v3/api-docs/
 
 

