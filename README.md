# Simple Library API

This is a simple library API that allows you to create, read, update and delete books, authors and publishers.

This project was built for the course [+devs2blu](https://www.devs2blu.com.br/).

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installing](#installing)
  - [Running the tests](#running-the-tests)
- [Documentation](#documentation)
- [Built With](#built-with)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development
and testing purposes.

### Prerequisites

You will need to have [Java 17](https://www.oracle.com/java/technologies/downloads/#java17) installed on your machine.

### Installing

Clone the repository:

1. Clone the repository:

```bash
git clone <repository-url>
```

2. Open the project folder:

```bash
cd <project-folder>
```

3. Install the dependencies:

```bash
./mvnw clean install
```

4. Create a PostgreSQL database (or use docker):

```bash
create database <database-name>;
```

5. Create a `application.properties` file in the `src/main/resources` folder:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/<database-name>
spring.datasource.username=<database-username>
spring.datasource.password=<database-password>
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.enabled=false
springdoc.swagger-ui.url=/swagger-config.json
```

6. Run the application:

```bash
./mvnw spring-boot:run
```

7. Access the application at [http://localhost:8080](http://localhost:8080).

---

> **Note:** You can also run and install the dependencies of the application using your IDE.

### Running the tests

To run the tests, run the following command:

```bash
./mvnw test
```

Or run the tests using your IDE.

## Documentation

The API documentation is available at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
- [Maven](https://maven.apache.org/) - Dependency Management
- [PostgreSQL](https://www.postgresql.org/) - Database
- [Flyway](https://flywaydb.org/) - Database migration tool
- [Railway](https://railway.app/) - Deployment platform
