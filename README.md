
# :blue_book:README Structure

---

### Table of Contents
- [Project Title](#project-title)
- [Purpose Of The Project](#purpose-of-the-project)
- [Why This Project Matters](#why-this-project-matters)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Run The Application](#run-the-application)
- [Stop The Application](#stop-the-application)
- [List Of Endpoints](#list-of-endpoints)
- [Swagger Documentation](#swagger-documentation-for-api-endpoints--testing)
- [Contributing](#contributing)


### Project Title
## :pushpin: STUDENT SAFEGUARDING API

---

### Purpose Of The Project
The **`Student-Safeguarding-API`** is designed to provide a secured, reliable, and structured way to record, update, retrieve, delete, manage, and track safeguarding concerns related to children. Safeguarding is a critical responsibility of everyone, ensuring that children and young people are protected from harm, abuse, and neglect. This API supports that mission by enabling staff, safeguarding leads, and anyone working with children to efficiently document observations, access records, and take appropriate follow up actions.

This API is not limited to educational setting alone, as it can be used across a wide range of institutions and environments where children and young people require protection and safeguarding. These institutions include but not limited to:
- Educational settings like schools
- Religious settings like church
- Health and care settings like hospitals and paediatric wards
- Community and recreational settings like youth clubs
- Justice and secure settings like secure residential units and juvenile detention centres
- Digital and online environments like E-learning platforms and gaming communities.




---

### :bulb:Why This Project Matters

Safeguarding is a legal, ethical, and professional responsibility in all child-focused institutions. However, many organisations still rely on inconsistent or paper-based reporting, which can lead to delayed responses and overlooked concerns. This project matters because it provides a **centralised**, **reliable**, and **secure digital system** that ensures every concern is recorded, traceable, accessible, and actioned by safeguarding leads, ultimately helping to protect children from harm.


---

### :toolbox: Prerequisites
- JDK 21 - To compile and run the Spring Boot application
- Spring Boot 3 - Framework used for building the API / dependency management
- MySQL (database) - Used to persist safeguarding concerns and related data
- Swagger - Integrated for interactive API Documentation
- VS Code - Code editor

---

### :rocket:Getting Started
Follow the steps below to set up and run the **Student Safeguarding API** locally:

### Create Database
1.  Login to MySQL:

```sh
mysql -u root -p
```
> :bulb: **Note:** If your root user doesn't have a password set, omit the `-p` flag.
2. Create a new database:

```sh
CREATE DATABASE IF NOT EXISTS safeguarding_db;
exit;
```

#### Initialise Project
1. Open this [pre-configured Initializr project](https://start.spring.io/#!type=maven-project&language=java&packaging=jar&jvmVersion=21&groupId=com.queenmmama.safeguarding&artifactId=safeguarding-api&name=Safeguarding%20API&description=RESTful%20API%20for%20managing%20safeguarding%20concerns&packageName=com.queenmmama.safeguarding.safeguarding_api&dependencies=web,data-jpa,mysql,devtools). Review the configured settings, but do not make any changes. Click "Generate" to download a zipped project.
2. Extract the downloaded zip file
3. Ensure that you are in the root project directory in the terminal, then copy the contents of the extracted directory to your `student-safeguarding-api` subdirectory. **IMPORTANT:** Do NOT copy the extracted files using Finder as not all extracted files may be correctly moved.
4. Delete the extracted directory and the downloaded zip file
5. Open your repository in VS Code
6. Add the following values to `student-safeguarding-api/src/main/resources/application.properties`:

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=true
spring.config.import=optional:./local.properties
```
7. In order to prevent sensitive values from being committed to version control, add a new entry to the .gitignore file:

```
local.properties
```

8. Create a new file at `student-safeguarding-api/src/main/resources/local.properties` and paste in the following: 

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/safeguarding_db

# Replace "root" with your database user, if applicable
spring.datasource.username=root

# Specify your database user's password, if applicable. If your database user doesn't have a password set, delete the line below
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

9. Replace the username and password values with your MySQL credentials. **IMPORTANT:** Ensure there are no spaces before or after the password.


#### Run The Application

To start the API, run the following command from the root project directory:

```sh
./mvnw spring-boot:run
```

If successful, you should see output that ends similarly to the following:

```
2025-09-07T10:03:41.310+01:00  INFO 27596 --- [safeguarding-api] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2025-09-07T10:03:41.310+01:00  INFO 27596 --- [safeguarding-api] [  restartedMain] c.q.s.s.SafeguardingApiApplication       : Started SafeguardingApiApplication in 3.867 seconds (process running for 4.172)
```

**IMPORTANT**: If everything is working correctly, the output will appear "stuck" and the terminal won't return until you stop the application, which should now be running at http://localhost:8080/api/concerns.

#### Stop The Application
Stop the application by pressing `Ctrl + C`


---

### :dart:List Of Endpoints
| Method | Endpoint | Description |
| --- | --- | --- |
| GET | /api/concerns | Retrieves a list of all (optionally filters by specific student name) CONCERNs |
| GET | /api/concerns/{id} | Retrieve a specific CONCERN by its ID |
| POST | /api/concerns | Create a new CONCERN |
| PUT | /api/concerns/{id} | Update an existing CONCERN by ID |
| DELETE | /api/concerns/{id} | Delete a CONCERN by ID |

---

### :satellite:Swagger Documentation For API Endpoints / Testing
Once the application is running, you can test the endpoints using [Swagger](http://localhost:8080/swagger-ui/index.html) or your preferred REST client at http://localhost:8080/api/concerns

The JSON representation of a CONCERN that you'll get in responses or provide in the request body for `POST` and `PUT` requests will resemble the following:

```json
{
    "id": "e26c3949-c463-4eb8-9598-bffdc5bb45ca",
    "studentName": "Benny",
    "reportedBy": "Classmate",
    "description": "Benny said her stepdad sneaks into her room every night to play with her",
    "status": "Open",
    "dateTime": "2025-09-06T22:00:40.145Z"
  }
```

### :handshake:Contributing

We welcome contributions from everyone! By contributing to the **Student Safeguarding API**, you help improve the project and make it more robust and useful for the community.

---

#### 1. How to Contribute
1. **Fork the repository**  
Click the “Fork” button on the GitHub repository page to create your own copy.

2. **Clone your fork locally**  
```bash
git clone https://github.com/MQ-579/student-safeguarding-api.git
```

3. **Create a new branch**
```new branch
git checkout -b feature/your-feature-name
```
4. Make your changes
- Add new features, fix bugs, improve documentation, or refactor code.
- Ensure your changes are related to safeguarding API functionality.

#### 2. Coding Standards
- To maintain a clean and consistent codebase, please follow these standards:
- Java Style: Follow standard Java conventions (camelCase for methods/variables, PascalCase for classes).
- Indentation: 4 spaces per indentation level.
- Comments: Use **`Javadoc`** for public classes/methods, and inline comments for complex logic.
- Testing: Write **`unit tests for`** any new features or bug fixes.
```Example
/**
 * Retrieves a list of all safeguarding concerns.
 * Optionally filters concerns by student name.
 *
 * @param studentName Optional student name to filter concerns
 * @return List of CONCERN objects
 */
public List<CONCERN> getCONCERNs(String studentName) {
    // Implementation here
}
```

#### 3. Pull Request (PR) Process
1. Push your changes to your branch
``` Example
git add .
git commit -m "Add a brief, descriptive message"
git push origin feature/your-feature-name
```
2. Open a Pull Request
- Navigate to the original repository on GitHub.
- Click Compare & Pull Request for your branch.
- Provide a clear title and description of your changes.
3. Code Review and Feedback
- One of the maintainers will review your PR.
- Make requested changes if needed.
4. Merge
- Once approved, your PR will be merged into the main branch.

**IMPORTANT:** Ensure your branch is up to date with the main branch before creating a PR to avoid conflicts



