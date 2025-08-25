## :pushpin: STUDENT SAFEGUARDING API

### Description

The goal of these exercises is to build a RESTful API service that allow users to manage safeguarding concerns using Spring Boot.

### Getting Started

#### Create Database
1. Login to MySQL:

```sh
mysql -u root -p
```
> :bulb: **Note:** If your root user doesn't have a password set, omit the `-p` flag.

2. Create a new database:

```sh
CREATE DATABASE IF NOT EXISTS safeguarding_db;
exit;
```

### Exercise 1

1. Create a concerns package in the main safeguarding_api package
2. Create a CONCERN entity class that maps to the "concerns" table  and has the following fields:
    - `UUID id`
    - `String studentName`
    - `String reportedBy`
    - `String description`
    - `String status`
    - `Instant dateTime`
3. 3. Set the `id` field as the primary key. So that the system automatically generate a UUID each time a new record is created.
4. Define a constructor that accepts the following parameters: `Concerns(String studentName, String reportedBY, reportedBy, String description, String status, Instant createdAt)`
5. Define a default (parameterless) constructor that calls the parameterised constructor internally.
6. Create getter and setter methods for each field, except `id`, which should only have a getter
7. Create a `CONCERNRepository` interface that extends `ListCrudRepository<CONCERN, UUID>`
8. If it's not already running, start your API with `./mvnw clean spring-boot:run`. Check the output and confirm there are no errors
9. Check your database contains a "concerns" table with the correct columns and data types
10. Commit your changes

