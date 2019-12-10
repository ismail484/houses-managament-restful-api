# Project: secured-restful-web-service-houses-management

# Description
  
  ###  Secured RESTful web-service for HousesManagament written using Java Spring Boot provide the following functionalities:
* have functionality to return list of all houses;
* support sorting by any field specified by query parameter, default sorting - by **id**;
* have functionality to return data for single house by id;
* have functionality to return data for single house by price;
* have functionality to add house;
* have functionality to modify house by id;
* have functionality to delete house by id;
* have validation for request parameters,
* accept and return data in JSON format, use standard JSON date format for the **constructionDate** field.


# List of secured APIs/Endpoints:
   - GET `/houses` : return list of all houses adverts
   - GET `/sortedhouses/{sortedBy}` : sorting by any field specified by query parameter ,default sorting - by **id**
   - POST`/addhouse` :  add house advert;
   - GET `/house/{id}`: return data for single house advert by id
   - GET `/house/price/{price}`: return data for single house advert by price using JPQL
   - PUT `/house/{id}`: update data for single house advert by id
   - Delete `/house/{id}`: delete data for single house advert by id


# Miscellaneous 
   1.  H2 database is used, because it's easily integrated with spring Boot (see pom.xml file) and configured in `application.properties` and I create simple query in `data.sql` to insert inital data into the DB.
   2.  I extends inteface of `JpaRepository`  which extends already interface of `CrudRepository` and `PagingAndSortingRepository` which allows me to have all Crud operations and Paging and sorting operations
   3.  no need to make implementation for  `HouseRepository` interface, don't worry Spring Boot take care about it .
   4.  implement `BasicAuthentication` for all APIs. by sending username and password in the header of the request.
   5. Unit testing for service/controller layers and integration test are done and cover all test cases.
   6. integration test cover all cases.
   7. implement Swagger ,which helps to  design, build, document and consume REST APIs. SpringFox is a Swagger integration for the Spring Framework( http://localhost:8080/swagger-ui.html)
   

# Resources
 
   1. [Paging and sorting operations](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html)
   2. [Crud Operations](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html)
   3. [Spring Boot ](https://spring.io/projects/spring-boot)
