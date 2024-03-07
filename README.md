# Triathlon training management application

<hr>

## Application description

Triathlon training management application is made for athletes and its coaches to create, schedule, realize and track training plans
of four types (running, cycling, swimming, weight training).

Backend is developing using Java with Spring Boot.
Frontend is developing using ReactJS.

Temporarily H2 is used for persistence.

## Application functionalities

- user register and login 
- application authentication and authorization using JWT token
- activities synchronization with Strava account
- coach can add new training plan (using training stages), get all his plans, delete plan and schedule plan to athlete
- athlete can get all his scheduled training plans
- athlete can get all his training realizations, delete realization
- athlete can retrieve all his training realizations from Strava after correct authentication
- athlete can update training realization with his feelings and description

## To run the application, follow these steps
- install Docker on your computer
- run Docker
- clone both backend and frontend repositories from links in section below
- create folder structure like below (cut docker-compose.yaml file from backend repository)
  <img src="https://github.com/Marcin1990x/triathlon-training-management-application/blob/master/folderStructReadme.png?raw=true"/>
- in the terminal go to folder with docker-compose.yaml file and run command docker-compose up
- open localhost:3000 to see UI client or work with postman with the help of source code
- run command docker-compose down to stop the application and remove containers

## Development comments
Application development is in progress.

To do:
 - use relational database MySQL / PostgreSQL
 - create UI to reflect API functionality

## Backend repository
https://github.com/Marcin1990x/triathlon-training-management-application
## Frontend repository
https://github.com/Marcin1990x/triathlon-training-management-ui

## Application is developed using following technologies:
<p align="left">
    <img src="https://ultimateqa.com/wp-content/uploads/2020/12/Java-logo-icon-1.png" alt="java" width="80" height="50"/> 
    <img src="https://e4developer.com/wp-content/uploads/2018/01/spring-boot.png" alt="spring" width="90" height="50"/> 
    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Apache_Maven_logo.svg/1280px-Apache_Maven_logo.svg.png" alt="maven" width="" height="37"/>
    <img src="https://cdn4.iconfinder.com/data/icons/logos-and-brands/512/97_Docker_logo_logos-512.png" alt="docker" width="" height="50"/>
    <img src="https://faq.o2switch.fr/_media/tuto-rapide/o2switch-deployer-react.js.png" alt="java" width="" height="50"/>
    <img src="https://jaki-jezyk-programowania.pl/img/technologies/javascript.png" alt="java" width="" height="50"/>
    <img src="https://junit.org/junit4/images/junit5-banner.png" alt="java" width="" height="50"/>
	   <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqvMgiH1EtBx1yqq1YurzOCwZKPzHDotG_2A&usqp=CAU.jpg" alt="strava" width="" height="50"/>
    <img src="https://javadoc.io/static/org.mockito/mockito-core/1.9.5/org/mockito/logo.jpg" alt="java" width="" height="50"/>
</p>
