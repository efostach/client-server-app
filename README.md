# Employees Management Application
[![Build Status](https://travis-ci.org/efostach/spring-client-server-app.svg?branch=master)](https://travis-ci.org/efostach/spring-client-server-app)
## Application Info
This application allows storing information about employees (name, salary, date of birth, etc.) and departments.

There are three type of accounts: 
* Admin - access to crete, read, update, remove employees, departments and users entities
* Moderator - access to create, read, update and remove employee and department entities
* User - access just for reading information about all entities

When user make a registration in the app the system sent generated code to the mobile phone number to accept the registration (in this case used https://www.twilio.com/).

## Used technologies
* Java
* Spring (MVC, Web, Data, Security, Boot)
* Lombok
* Liquibase
* MySQL
* Maven
* Travis
* Heroku