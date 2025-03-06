# MatawanTest REST API
## 📌 Description

This project is a REST API to manage football teams and their players

## Models
### Player
* Id (Long)
* Name (String)
* Position (String)
* Team (Team)

### Team
* Id (Long)
* Name (String)
* Acronym (String with pattern [A-Z0-9]*)
* Players (List of players)
---

## 🏗️ Technologies

* **Java 17**
  * This is the most used Java version in companies at the moment
* **Spring Boot 3.4.3**
  * The last version of Springboot
* **PostgreSQL 17**
  * The database which I am the most familiar with
* **Docker**
  * It allows me to embed the API and the database in the same time and to minimize your downloads to test the project
* **JUnit 5** + **MockMvc**
  * Simple tests frameworks
* **Lombok**
  * Simplify the creation of models and their understanding
* **Hibernate** / **JPA**
* **OpenAPI Swagger**
  * Allows the users to understand and learn easily the endpoints of the API
---

## ⚡ Getting started

### Prerequisites

It is mandatory to download and launch [Docker Desktop](https://www.docker.com/products/docker-desktop/) before trying to start the project

### Launch the project
```bash
docker-compose up --build
```
### OR
Launch the project directly on `Docker Desktop`

## 📚 Documentations
Once the project started,

You can find the code documentation here:
* [Javadoc](http://localhost:8080/javadoc/index.html)

And the API documentation here:
* [Swagger](http://localhost:8080/swagger-ui/index.html)

## 📞 Contacts
If you have any questions regarding the API or its installation you can contact me at the following address: thomas.bonin06@gmail.com
