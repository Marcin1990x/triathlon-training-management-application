# Triathlon training management application

<hr>

## Application description

Triathlon training management application is made for athletes and its coaches to create, schedule, realize and track training plans
of four types (running, cycling, swimming, weight training).

Backend is developing using Java with Spring Boot.
Frontend is developing using ReactJS.

Temporary I use H2 for persistence.

## Application functionalities

- user register and login 
- application authentication and authorization using JWT token
- activities synchronization with Strava account
- coach can add new training plan (using training stages), get all his plans, delete plan and schedule plan to athlete
- athlete can get all his scheduled training plans
- athlete can get all his training realizations, delete realization
- athlete can retrieve all his training realizations from Strava after correct authentication
- athlete can update training realization with his feelings and description

## Development comments

Application development is in progress.

To do:
 - use another relation database MySQL / PostgreSQL
 - dockerize application
 - finish UI

## Backend repository
https://github.com/Marcin1990x/triathlon-training-management-application
## Frontend repository
https://github.com/Marcin1990x/triathlon-training-management-ui

## Application is developed using following technologies:
<p align="left">
    <img src="https://ultimateqa.com/wp-content/uploads/2020/12/Java-logo-icon-1.png" alt="java" width="80" height="50"/> 
    <img src="https://e4developer.com/wp-content/uploads/2018/01/spring-boot.png" alt="spring" width="90" height="50"/> 
    <img src="https://s3.ap-southeast-1.amazonaws.com/arrowhitech.com/wp-content/uploads/2021/09/01031030/ReactJS.png" alt="java" width="" height="50"/>
    <img src="https://jaki-jezyk-programowania.pl/img/technologies/javascript.png" alt="java" width="" height="50"/>
    <img src="https://junit.org/junit4/images/junit5-banner.png" alt="java" width="90" height="50"/>
    <img src="https://javadoc.io/static/org.mockito/mockito-core/1.9.5/org/mockito/logo.jpg" alt="java" width="90" height="50"/>
</p>


