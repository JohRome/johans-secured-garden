# Johans Secured Garden

## Description

+ The goal of this project was to create RESTful APIs so that a user can perform some requests as a non-authenticated user and some requests as an authenticated user.
+ One API lets a user register as a new Garden Master and also login as a Garden Master to receive a JWT token. This JWT token is then used to authenticate the user for the PUT and DELETE requests.
+ One API lets a user perform CRUD operations on the plants in the garden.


## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Credits](#credits)
- [Dependencies](#dependencies)
- [Features](#features)
- [License](#license)
- [Tests](#tests)

## Installation

Before running the program, make sure you have the following installed:
+ [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
+ [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows)
+ [MySQL](https://dev.mysql.com/downloads/installer/)
+ Go to your MySQL Workbench and create a new schema called "botanical_garden".
+ Go to [application.properties](garden-server/src/main/resources/application.properties) and change the username and password to your MySQL username and password.

## Usage

In order to run the project, open this project in IntelliJ IDEA and go to the garden-server module, right-click on "ServerApp" and click on Run 'ServerApp'.
Wait for the server to start and then go down to [Tests](#tests) to test the API endpoints.

## Credits

+ [ChatGPT](https://chat.openai.com/) for clarification and documenting code.
+ [JavaGuide's paid Udemy course](https://www.udemy.com/course/building-real-time-rest-apis-with-spring-boot/?referralCode=6312172DF8B8C2C11F5E) for understanding and implementation of Spring Security's Authentication and Authorization.
+ [Postman](https://www.postman.com/) for documenting the API endpoints.


## Dependencies

* [Spring Boot Dependencies](pom.xml)
* [jjwt-impl](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl)
* [jjwt-api](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api)
* [jjwt-jackson](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson)

## Features

+ Register as a new Garden Master
+ Log in as a Garden Master to receive a JWT token
+ Add a new plant to the garden
+ View all plants in the garden
+ View a specific plant in the garden
+ Update a specific plant in the garden
+ Delete a specific plant in the garden

## Tests

+ For testing the API endpoints, visit my published API documentation on: https://documenter.getpostman.com/view/29777299/2s9YeD9DMT
