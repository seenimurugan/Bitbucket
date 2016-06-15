Technology used
=================

1. Spring RESTful webservice 
2. Spring BOOT 
3. Spring JPA(Hibernate impl)
4. Spring security - BASIC authentication using HTTP authorization header
4. h2 in-memory db
5. Logback - logging framework
6. Embedded Tomcat - can also be deployed to external tomcat by un-commenting few lines in the pom.xml file


TO RUN THE APPLICATION
===============================

Run the following command in the terminal from the project folder

1. Maven command

    mvn spring-boot:run 

2. Java command

    java -jar target/sis-rest-service-0.1.0.jar


REST SERVICE CONFIGURATION
==========================

. By default(Spring RESTful) CORS enabled so the services can be accessed from any domain

. In-memory user accounts are created for authentication and authorization 

    1. username: sis password: password roles: USER
    2. username: admin password: password roles: ADMIN

AVAILABLE REST SERVICES
========================

1. createFootballTeam 

    supported http method: post
    credential requirement : user with ADMIN role 
    request body format : {
                                "name":"sis",
                                "city":"mk",
                                "competition":"test"
                                "owner":"sis",
                                "capacity":1233,
                                "numberOfPlayers":12
                          }
    mandatory field in the req body object: name

    e.g : curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"name": "sis", "city": "mk", "owner": "sis", "capacity": 1233, "numberOfPlayers": 12}' http://localhost:8080/createFootballTeam -u admin:password

2. getAllFootballTeams
    
    supported http method: get
    credential requirement : user with USER role 
    query param : sort e.g (sort=capacity) by default it is orderd by team name
    
    e.g: curl -X GET http://localhost:8080/getAllFootballTeams -u sis:password
    

3. getFootballTeamByName
    
    supported http method: get
    credential requirement : user with USER role 
    query param : name e.g (name=sis) 
    mandatory field in the req param: name
    
    e.g: curl -X GET http://localhost:8080/getFootballTeamByName?name=sis -u sis:password



TO DO:
=======

1. Configure Swagger UI - Represent and document the RESTful API.
2. Configure Docker container management
3. Write more test coverage for both E2E and unit test.
4. Configure JWT(instead basic authentication) for secured credential exchange
 

