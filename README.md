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
3. Set the `id` field as the primary key. So that the system automatically generate a UUID each time a new record is created.
4. Define a constructor that accepts the following parameters: `Concerns(String studentName, String reportedBY, reportedBy, String description, String status, Instant createdAt)`
5. Define a default (parameterless) constructor that calls the parameterised constructor internally.
6. Create getter and setter methods for each field, except `id`, which should only have a getter
7. Create a `CONCERNRepository` interface that extends `ListCrudRepository<CONCERN, UUID>`
8. If it's not already running, start your API with `./mvnw clean spring-boot:run`. Check the output and confirm there are no errors
9. Check your database contains a "concerns" table with the correct columns and data types
10. Commit your changes

### Exercise 2

1. Create a CONCERNService class that accepts a CONCERNRepository as a dependency and implements the following methods:
    - `List<CONCERN> getAllCONCERNs()`
    - `CONCERN getCONCERN(UUID id) throws NoSuchElementException`
    - `CONCERN createCONCERN(CONCERN concern) throws IllegalArgumentException, OptimisticLockingFailureException`
    - `CONCERN updateCONCERN(UUID id, CONCERN updatedCONCERN) throws NoSuchElementException`
    - `void deleteCONCERN(UUID id)`
2. Create a `CONCERNController` class that implements the endpoints below. Ensure your service class is injected as a dependency and apply the appropriate annotations
3. Start your API and confirm there are no errors
4. Commit your changes

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | /api/concerns | Retrieve a list of (optionally filtered) CONCERNs |
| GET | /api/concerns/{id} | Retrieve a specific CONCERN by its ID |
| POST | /api/concerns | Create a new CONCERN |
| PUT | /api/concerns/{id} | Update an existing CONCERN by ID |
| DELETE | /api/concerns/{id} | Delete a CONCERN by ID |

You can now test your endpoints using [Postman](https://www.postman.com) or your preferred REST client at http://localhost:8080/api/concerns

The JSON representation of a CONCERN that you'll get in responses or provide in the request body for `POST` and `PUT` requests will resemble the following:

```json
{
  "id": "b45fc062-7d3d-4738-bc76-6ae23d27506b",
  "studentName": "Eka-ete",
  "reportedBy": "Mum",
  "description": "Eka-ete said she is maltreated by her teacher at school.",
  "status": "resolved",
  "dateTime": "2025-08-29T09:20:00Z"
```

> :bulb: **Note:** Remember that the `id` property may not be needed for all request types.

### Exercise 3

1. Create a `concerns` package inside the `student-safeguarding-api/src/test/java/com/queenmmama/safeguarding/safeguarding_api` package
2.  **Carry out JUnit test**: Create a CONCERNServiceTest class
3. Run the tests with `./mvnw clean test`
4. Examine the results. Do any tests fail? If so, what reasons are given? Modify your code so all tests pass
5. Commit your changes

### Exercise 4

1. Create a new API endpoint to return CONCERNs for a specific student:
   1. Create a method in your repository interface called `findByStudentName` that accepts a string `StudentName` parameter.
   2. Create a method in your service class called `getCONCERNsByBorrower`.
   3. Extend the `getCONCERN` method of your controller to accept an optional query string parameter, e.g.: `getCONCERNs(@RequestParam(required = false) String StudentName)`
   4. Check the value of the `StudentName` parameter to determine whether to call the existing service method or the new, filtered, one
2. Test the modified endpoint
3. Commit your changes
