# Project: secured-restful-web-service-houses-management

# Description
  
  ###  Secured RESTful web-service for HousesManagament written using Java Spring Boot provide the following functionalities:
* has functionality to return list of all houses;
* support sorting by any field specified by query parameter, default sorting - by **id**;
* has functionality to return data for single house by id;
* has functionality to return data for single house by price;
* has functionality to add house;
* has functionality to modify house by id;
* has functionality to delete house by id;
* has validation for request parameters,
* accept and return data in JSON format, use standard JSON date format for the **constructionDate** field.


# List of secured APIs/Endpoints:
   - GET `/houses` : return list of all houses.
   - GET `/sortedhouses/{sortedBy}` : sorting by any field specified by query parameter ,default sorting - by **id**
   - POST`/addhouse` :  add house;
   - GET `/house/{id}`: return data for single house by id
   - GET `/house/price/{price}`: return data for single house by price using spring data name convention 
   - PUT `/house/{id}`: update data for single house by id
   - Delete `/house/{id}`: delete data for single house by id


# Miscellaneous 
   1.  H2 database is used, because it's easily integrated with spring Boot (see pom.xml file) and configured in `application.properties` and I create simple query in `data.sql` to insert inital data into the DB.
   2.  I extends inteface of `JpaRepository`  which extends already interface of `CrudRepository` and `PagingAndSortingRepository` which allows me to have all Crud operations and Paging and sorting operations
   3.  no need to make implementation for  `HouseRepository` interface, don't worry Spring Boot take care about it in Runtime .
   4.  implement `BasicAuthentication` for all APIs.
   5.implement Basic Authentication(Baic Auth) is the simplest protocol available for performing web service authentication over HTTP protocol. Basic Auth requires a username and password. The client calling the web service takes these two credentials, converts them to a single Base 64–encoded value and passes it along in the Authentication HTTP header. The server compares the credentials passed to those stored. If it matches, the server fulfills the request and provides access to the data. If the Authentication HTTP header is missing or the password doesn’t match the user name, the server denies access and returns a 401 status code, which means the request is Unauthorized.
   5. Unit testing for service/controller layers and integration test are done and cover all test cases.
   6. integration test cover all cases.
   7. implement Swagger ,which helps to  design, build, document and consume REST APIs. SpringFox is a Swagger integration for the Spring Framework( http://localhost:8080/swagger-ui.html)
   

# Resources
 
   1. [JPA Repository](https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html)
   2. [Paging and sorting operations](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html)
   3. [Crud Operations](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html)
   4. [Spring Boot ](https://spring.io/projects/spring-boot).
   5. [Swagger OpenAPI](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md)
   6. [ Base 64–encoded](https://en.wikipedia.org/wiki/Base64).
   7. [401 status code](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)
   8. [Spring Security](https://spring.io/projects/spring-security)
