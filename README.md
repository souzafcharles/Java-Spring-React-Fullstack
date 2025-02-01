![GitHub language count](https://img.shields.io/github/languages/count/souzafcharles/java-spring-react-fullstack)
![GitHub top language](https://img.shields.io/github/languages/top/souzafcharles/java-spring-react-fullstack)
![GitHub](https://img.shields.io/github/license/souzafcharles/java-spring-react-fullstack)
![GitHub last commit](https://img.shields.io/github/last-commit/souzafcharles/java-spring-react-fullstack)

# 💻 Building a Full-Stack Application with Java Spring and React
:triangular_flag_on_post: Project developed based on tutorials by **Fernanda Kipper** - [kipperDev](https://www.youtube.com/@kipperdev).
***
## Project Stack:
| Technology        | Version   | Description                                 |
|-------------------|-----------|---------------------------------------------|
| ✒️ IntelliJ IDEA  | `2024.3`  | Integrated Development Environment (IDE)    |
| ☕ Java           | `21`      | Backend programming language                |
| 🍃 Spring Boot    | `v3.4.2`  | Framework for creating Spring applications  |
| 🐦 Maven          | `v3.9.9`  | Build automation and dependency management  |
| 🐘 PostgreSQL     | `17`      | Relational database management system       |
| 👩‍🚀 Postman        | `11.19`   | API testing and development tool            |
***
## Dependencies:
| Dependency              | Category        | Description                                                                                                                          |
|-------------------------|-----------------|--------------------------------------------------------------------------------------------------------------------------------------|
| 🛠️ Spring Boot DevTools | Developer Tools | Provides fast application restarts, LiveReload, and configurations for enhanced development experience                               |
| 🌐 Spring Web           | Web             | Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container                    |
| 💾 Spring Data JPA      | SQL             | Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate                                                 |
| 🐘 PostgreSQL Driver    | SQL             | A JDBC and R2DBC driver that allows Java programs to connect to a PostgreSQL database using standard, database independent Java code |
| 🗝️ dotenv-java          | Configuration   | Loads environment variables from a `.env` file into the application, aiding in secure configuration management                       |
| 🦅 Flyway Migration     | SQL             | Version control for your database so you can migrate from any version (incl. an empty database) to the latest version of the schema  |
***

## Instructions:
<p align="justify">
This guide provides a structured approach to developing a full-stack application using <code>Java Spring</code>, <code>React</code>, and <code>PostgreSQL</code>. The process is divided into two key phases: backend development and frontend development.
</p>

### Part 1: Backend Development with Java Spring and PostgreSQL:
<p align="justify">
This section details the construction of the application's backend using the <code>Java Spring</code> framework. The initial step involves creating a <code>Spring Boot</code> project and configuring the <code>PostgreSQL</code> database. Subsequently, the guide covers the creation of data models and <code>RESTful</code> controllers to manage <code>CRUD</code> operations within the application. The utilisation of essential libraries, such as <code>Spring Data JPA</code>, will be explored to streamline data access and manipulation.
</p>

### Part 2: Frontend Development with React and TypeScript:
<p align="justify">
This section focuses on developing the application's frontend using the <code>React</code> library and <code>TypeScript</code>. The process includes creating <code>React</code> components and leveraging <code>React Hooks</code> to implement the application's functionalities. The guide also explores the use of <code>TypeScript</code> to enhance code maintainability. Furthermore, it details the connection of the frontend to the backend (developed in Part 1) using <code>Axios</code> for <code>HTTP</code> requests and <code>React Query</code> for fetching, caching, synchronising, and updating server state within the application.
</p>

***
▶️ **Tutorial Video kipperDev**: [Part 1](https://www.youtube.com/watch?v=lUVureR5GqI&ab_channel=FernandaKipper%7CDev)</br>
▶️ **Tutorial Video kipperDev**: [Part 2](https://www.youtube.com/watch?v=WHruc3_2z68&ab_channel=FernandaKipper%7CDev)
***
## Project Requirements and Configurations:
### Food Domain Entity:
![Food Domain Entity](https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/food-entity.png)
***
### Logical Layers:
![Logical Layers](https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/logical-layers.png)
***
### 1. Requirements Specification:
#### 1.1 Dependencies and Tools:
- **Spring Initializr Configuration:**
    - Build Tool: **Maven;**
    - Programming Language: **Java 21;**
    - Packaging Type: **JAR;**
    - Required Dependencies:
        - **Spring Web;**
        - **Spring Data JPA;**
        - **PostgreSQL Driver;**
        - **Spring Boot DevTools;**
        - **dotenv-java.**
***
### 2. Creating the `.env` File:
- At the root of the project, create a file named `.env`, and declare the following environment variables:
```properties
DATABASE_URL=your_database_url
DATABASE_USERNAME=your_database_username
DATABASE_PASSWORD=your_database_password
```
***
### 3. Configuring the application.properties File:
- Modify the `application.properties` file to reference the environment variables defined in the `.env` file:
````properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
````
***
### 4. Adding the Dotenv Dependency to pom.xml:
- To enable environment variable loading, add the following `Maven` dependency to the `pom.xml` file:
````xml
<dependency>
    <groupId>io.github.cdimascio</groupId>
    <artifactId>dotenv-java</artifactId>
    <version>3.0.0</version>
</dependency>
````
### 5. Automatically Loading Environment Variables:
- Create a utility class to load the environment variables from the `.env` file:
````java
import io.github.cdimascio.dotenv.Dotenv;

public class LoadEnvironment {
    public static void loadEnv() {
        Dotenv dotenv = Dotenv.configure().load();
        dotenv.entries().forEach(entry -> 
            System.setProperty(entry.getKey(), entry.getValue())
        );
    }
}
````
#### 5.1 Entry Point Integration:
- Call the environment loader at the project's entry point to ensure environment variables are available at runtime:
````java
public static void main(String[] args) {
    LoadEnvironment.loadEnv();
    SpringApplication.run(YourApplicationClass.class, args);
}
````
***
### 6. Protecting Sensitive Information in Git:
- To protect sensitive credentials, add the `.env` file to `.gitignore`:
````gitignore
.env
````
***
### 7. Entity, DTOs, Repository, and Controller Classes:
#### 7.1 Requirements for Food Entity Class:
- **Entity Mapping:**
    - Create the `Food` class as an entity to represent a database table in the application;
    - Annotate the class with `@Entity` to define it as a persistent entity;
    - Use `@Table(name = "tb_foods")` to map it to the database table named `tb_foods`;
- **Attributes and Annotations:**
    - Define attributes `id`, `title`, `price`, and `imgUri` to map to the respective columns in the database;
    - Annotate the `id` field with `@Id` and `@GeneratedValue(strategy = GenerationType.IDENTITY)` for automatic primary key generation;
    - Annotate `imgUri` with `@Column(name = "imguri")` to explicitly define its column name;
- **Constructors:**
    - Create a no-argument constructor required by `JPA`;
    - Provide an all-arguments constructor for convenience;
    - Include a constructor that accepts a `FoodRequestDTO` for mapping `DTO` data to the entity;
- **Accessors and Mutators:**
    - Implement `getters` and `setters` for all attributes to allow data manipulation;
- **Equals and HashCode:**
    - Override the `equals()` method to compare entities based on the `id` attribute;
    - Override `hashCode()` to provide a consistent hash for `Food` objects, using `Objects.hashCode(id)`;
- **Serializable Interface:**
    - Implement the `Serializable` interface to support object serialization for the entity when necessary (e.g., when transferring objects between systems).
#### 7.2 Requirements for FoodResponseDTO Record Class:
- **Record Declaration:**
    - Create the `FoodResponseDTO` as a `record` class to represent a simplified data structure for `responses`;
- **Attribute Definition:**
    - Define the attributes `id`, `title`, `price`, and `imgUri` directly in the record's header to make them immutable and automatically provide accessor methods;
- **Entity-based Constructor:**
    - Implement a custom constructor that accepts a `Food` entity object as an argument;
    - Extract and map values from the `Food` entity to initialise the `FoodResponseDTO` attributes.
- **Purpose:**
    - Use this `record` for `transferring` food-related data from the `backend service layer` to the `controller response`, following RESTful API conventions.
#### 7.3 Requirements for FoodRequestDTO Record Class:
- **Record Declaration:**
    - Create the `FoodRequestDTO` as a `record` class to represent the request payload for creating or updating food entries.
- **Attribute Definition:**
    - Define the attributes `String title`, `Double price`, and `String imgUri` directly in the record's header, enabling immutability and automatic generation of accessor methods.
- **Purpose:**
    - Use this `record` for `receiving` and validating user input from `client requests` to `create/insert` or `update` `Food` entities within the application.
#### 7.4 Requirements for FoodRepository Interface:
- **Repository Creation:**
    - Create the `FoodRepository` interface to handle data access operations for the `Food` entity;
- **JpaRepository Extension:**
    - Extend `JpaRepository<Food, Long>` to inherit common CRUD operations and JPA-specific functionalities;
- **Entity Association:**
    - Specify `Food` as the associated entity and `Long` as the type for its primary key;
#### 7.5 Requirements for FoodService Class:
- Use `@Service` annotation to define the class as a Spring service component;
- Inject `FoodRepository` using `@Autowired` for dependency injection;
- Implement methods to retrieve all `Food` entities:
    - `findAll`: Fetches all entries from the database and maps them to `FoodResponseDTO` objects;
    - Use `@Transactional(readOnly = true)` to ensure the method runs within a read-only transaction for optimized database performance;
- Implement a method to insert a new `Food` entity:
    - `insert`: Saves a new `Food` entity in the database;
    - Use `@Transactional` to ensure that this method runs within a transactional context, enabling database operations rollback if any exceptions occur;
- Implement a method to retrieve a `Food` entity by its `id`:
    - `findById`: Fetches a single `Food` entity by its identifier and maps it to a `FoodResponseDTO`;
    - Use `@Transactional(readOnly = true)` to ensure that this method runs within a read-only transaction;
- Implement a method to update an existing `Food` entity:
    - `update`: Updates an existing `Food` entity by its identifier;
    - Use `@Transactional` to ensure the method is executed in a transactional context for data consistency;
- Implement a method to delete a `Food` entity:
    - `delete`: Deletes a `Food` entity by its identifier;
    - Use `@Transactional` to ensure the operation is part of a transaction, allowing rollback in case of failure.
#### 7.6 Requirements for FoodController Class:
- Create the `FoodController` class to manage RESTful endpoints for the `Food` resource;
- Use `@RestController` annotation to mark it as a REST controller for Spring;
- Map requests using the `@RequestMapping` annotation for the `/foods` endpoint;
- Inject `FoodService` using `@Autowired` for service dependency injection;
- Implement a method to handle `GET` requests:
    - Use `@GetMapping` annotation to map GET requests to `/foods`;
    - Return a `ResponseEntity<List<FoodResponseDTO>>` with an HTTP 200 (OK) status and the list of foods;
    - Handle empty lists and return an HTTP 204 (No Content) status if no foods are found;
- Implement a method to handle `POST` requests:
    - Use `@PostMapping` annotation to map POST requests to `/foods`;
    - Return a `ResponseEntity<FoodResponseDTO>` with an HTTP 201 (Created) status and the created `Food` object;
- Implement a method to handle `GET` requests by `id`:
    - Use `@GetMapping(value = "/{id}")` annotation to map GET requests for a specific `Food` by its identifier;
    - Return a `ResponseEntity<FoodResponseDTO>` with an HTTP 200 (OK) status;
- Implement a method to handle `PUT` requests for updating a `Food` by `id`:
    - Use `@PutMapping(value = "/{id}")` annotation to map PUT requests for updating an existing `Food`;
    - Return a `ResponseEntity<FoodResponseDTO>` with an HTTP 200 (OK) status and the updated `Food` object;
- Implement a method to handle `DELETE` requests for deleting a `Food` by `id`:
    - Use `@DeleteMapping(value = "/{id}")` annotation to map DELETE requests for removing a specific `Food`;
    - Return a `ResponseEntity<Void>` with an HTTP 204 (No Content) status after successful deletion;
- Use `@CrossOrigin(origins = "*", allowedHeaders = "*")` annotation to allow cross-origin requests from any origin;
- Ensure that the class implements the `Serializable` interface to support object serialization when needed.
***
### 8. Database Seeding with Food Data in PostgreSQL:
````sql
-- Drop the table if it exists
DROP TABLE IF EXISTS tb_foods;

-- Create the table
CREATE TABLE tb_foods (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    imgUri TEXT NOT NULL
);

-- Insert data into the table
INSERT INTO tb_foods (title, price, imgUri) VALUES
('Pizza Margherita', 29.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/pizza.jpg'),
('Hambúrguer Clássico', 19.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/burger.jpg'),
('Suco Natural de Laranja', 9.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/orange-juice.jpg'),
('Macarronada à Bolonhesa', 24.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/spaghetti.jpg'),
('Batata Frita Crocante', 14.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/fries.jpg'),
('Sorvete de Chocolate', 12.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/ice-cream.jpg'),
('Sushi Variado', 34.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/sushi.jpg'),
('Sanduíche Natural', 16.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/sandwich.jpg'),
('Pizza Calabresa', 32.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/calabresa-pizza.jpg'),
('Hambúrguer Vegetariano', 22.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/veggie-burger.jpg'),
('Suco de Morango', 10.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/strawberry-juice.jpg'),
('Lasanha à Bolonhesa', 27.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/lasagna.jpg'),
('Sorvete de Morango', 13.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/strawberry-ice-cream.jpg'),
('Temaki de Salmão', 29.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/temaki.jpg'),
('Sanduíche de Frango', 18.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/chicken-sandwich.jpg');
````
***
### 9. Requesting and Responding Food Data via Spring Boot RESTful API:
#### 9.1 Setting Up the RESTful API for HTTP Methods (Idempotent):
- Endpoint: GET `/foods`: Retrieves a list of all Food.
#### 9.2 Example GET Request:
````json
http://localhost:8080/foods
````
#### 9.3 Example Response:
````json
[
  {
    "id": 1,
    "title": "Pizza Margherita",
    "price": 29.99,
    "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/pizza.jpg"
  },
  {
    "id": 2,
    "title": "Hambúrguer Clássico",
    "price": 19.99,
    "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/burger.jpg"
  },
  {
    "id": 3,
    "title": "Suco Natural de Laranja",
    "price": 9.99,
    "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/orange-juice.jpg"
  },
  {
    "id": 4,
    "title": "Macarronada à Bolonhesa",
    "price": 24.99,
    "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/spaghetti.jpg"
  },
  {
    "id": 5,
    "title": "Batata Frita Crocante",
    "price": 14.99,
    "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/fries.jpg"
  },
  {
    "id": 6,
    "title": "Sorvete de Chocolate",
    "price": 12.99,
    "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/ice-cream.jpg"
  },
  {
    "id": 7,
    "title": "Sushi Variado",
    "price": 34.99,
    "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/sushi.jpg"
  },
  {
    "id": 8,
    "title": "Sanduíche Natural",
    "price": 16.99,
    "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/sandwich.jpg"
  },
  {
    "id": 9,
    "title": "Pizza Calabresa",
    "price": 32.99,
    "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/calabresa-pizza.jpg"
  },
  {
    "id": 10,
    "title": "Hambúrguer Vegetariano",
    "price": 22.99,
    "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/veggie-burger.jpg"
  },
  {
    "id": 11,
    "title": "Suco de Morango",
    "price": 10.99,
    "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/strawberry-juice.jpg"
  },
  {
    "id": 12,
    "title": "Lasanha à Bolonhesa",
    "price": 27.99,
    "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/lasagna.jpg"
  },
  {
    "id": 13,
    "title": "Sorvete de Morango",
    "price": 13.99,
    "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/strawberry-ice-cream.jpg"
  },
  {
    "id": 14,
    "title": "Temaki de Salmão",
    "price": 29.99,
    "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/temaki.jpg"
  },
  {
    "id": 15,
    "title": "Sanduíche de Frango",
    "price": 18.99,
    "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/chicken-sandwich.jpg"
  }
]
````
#### 9.4 Setting Up the RESTful API for HTTP Methods (Non-Idempotent):
- Endpoint: POST `/foods`: Creates a new Food.
#### 9.5 Example POST Request:
````json
http://localhost:8080/foods
Body -> raw -> JSON
````
````json
{
  "title": "Crepioca de Carne Seca",
  "price": 21.99,
  "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/crepioca.jpg"
}
````
#### 9.6 Example Response:
````json
{
  "id": 16,
  "title": "Crepioca de Carne Seca",
  "price": 21.99,
  "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/crepioca.jpg"
}
````
#### 9.7 Setting Up the RESTful API for HTTP Methods (Idempotent):
- Endpoint: GET `/foods/{id}`: Retrieves a specific Food item by its ID.

#### 9.8 Example GET Request:
````json
http://localhost:8080/foods/1
````        
#### 9.9 Example Response:
````json
{
  "id": 1,
  "title": "Pizza Margherita",
  "price": 29.99,
  "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/pizza.jpg"
}
````
#### 9.10 Setting Up the RESTful API for HTTP Methods (Idempotent):
- Endpoint: PUT `/foods/{id}`: Updates a specific Food item by its ID.
#### 9.11 Example PUT Request:
````json
http://localhost:8080/foods/16
Body -> raw -> JSON
````
````json
{
  "title": "Tapioca de Carne Seca",
  "price": 20.99,
  "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/tapioca.jpg"
}
````
#### 9.12 Example Response:
````json
{
  "id": 16,
  "title": "Tapioca de Carne Seca",
  "price": 20.99,
  "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/tapioca.jpg"
}
````
#### 9.13 Setting Up the RESTful API for HTTP Methods (Idempotent):
- Endpoint: DELETE `/foods/{id}`: Deletes a specific Food item by its ID.
#### 9.14 Example DELETE Request:
````json
http://localhost:8080/foods/1
````
#### 9.15 Example Response:
````json
HTTP Status 204 - No Content
````
#### 9.16 Idempotent and Non-Idempotent Methods:
- **Idempotent Method**: `GET`, `PUT`, and `DELETE` are idempotent, meaning multiple identical requests should have the same effect as a single request;
- **Non-Idempotent Method**: `POST` is non-idempotent, meaning multiple identical requests may have additional effects.
***
### 10. Exception Handling for `findById` Method:
#### 10.1 **NEW CLASS:** `services.exceptions.ResourceNotFoundException` (Custom Exception)`:
- This section introduces the implementation of custom exception handling for the `findById` method in the FoodService class, introducing custom exceptions and centralized error handling mechanisms.
- Create a custom exception named `ResourceNotFoundException`, extending `RuntimeException`;
- Constructor takes an `Object id` as a parameter to provide a specific resource ID in the error message;
- Provide a detailed message when an exception occurs: `"Resource Not Found! ID: [id]"`.
#### 10.2 **NEW CLASS:** `controller.exceptions.StandardError` (Entity for Standardized Error Messages):
- Create the `StandardError` class to represent error messages for RESTful APIs:
- Include the following attributes:
  - `Instant timestamp`: formatted with `@JsonFormat` to comply with standard time formats;
  - `Integer status`: the HTTP response status code;
  - `String error`: short description of the error;
  - `String message`: detailed error message;
  - `String path`: the URI that generated the error;
- Provide constructors, getters, and setters to support object manipulation;
- Implement `Serializable` for object serialization when needed.
#### 10.3 **NEW CLASS:** `controller.exceptions.ResourceExceptionHandler`:
- The `ResourceExceptionHandler` class is responsible for intercepting exceptions thrown during the execution of RESTful requests in the application and converting them into standardized HTTP error responses.
#### 10.3.1 **Key Features:**
- **Global Exception Handling:** The class is annotated with `@ControllerAdvice`, which enables centralized exception handling across all `@Controller` components;
- **Error Response Standardization:** Provides a mechanism to customize the error response by creating and returning `StandardError` objects with detailed error information.
#### 10.3.2 **Detailed Breakdown of the `resourceNotFound` Method:**
````java
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
    String error = "Resource not found with the specified identifier or criteria.";
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandardError err = new StandardError(
            Instant.now(),                   // Current timestamp of the error
            status.value(),                  // HTTP Status code (404)
            error,                           // Custom error message
            e.getMessage(),                  // Exception's detailed message
            request.getRequestURI()          // URI path where the error occurred
    );
    return ResponseEntity.status(status).body(err);
}
````
#### 10.3.3 Annotations and Parameters:
- `@ExceptionHandler`(ResourceNotFoundException.class): Maps the method to handle exceptions of type `ResourceNotFoundException`;
- `ResourceNotFoundException` e: The exception object containing the error details;
- `HttpServletRequest` request: Captures information about the HTTP request, such as the request URI.
#### 10.3.3 Response Construction:
- **Timestamp**: `Instant.now()` ensures the current time is recorded when the exception is handled;
- **Status Code**: `HttpStatus.NOT_FOUND.value()` returns the HTTP status code 404;
- **Error Message**: The variable `error` provides a concise description for the error;
- **Detailed Message**: `e.getMessage()` displays the custom error message from ResourceNotFoundException;
- **Request Path**: `request.getRequestURI()` specifies the URI that caused the exception.
#### 10.4 Update Method findById in FoodService:
- Modify the `findById` method to throw the custom `ResourceNotFoundException`:
````java
@Transactional(readOnly = true)
public FoodResponseDTO findById(Long id) {
    Food result = foodRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    return new FoodResponseDTO(result);
}
````
#### 10.5 Requesting and Responding Food Data via Spring Boot RESTful API:
#### 10.5.1 Setting Up the RESTful API for HTTP Methods (Idempotent):
- **Endpoint**: GET `/foods/{id}`;
- **Purpose**: Retrieves a specific Food item by its ID.
#### 10.5.2 Example GET Request:
- **Scenario**: The requested ID `17` does not exist, triggering the custom error response with a `404 Not Found` status code:
````json
http://localhost:8080/foods/17
````
#### 10.5.3 Example Error Response:
- Upon catching a `ResourceNotFoundException`, the method returns a structured JSON response in the following format:
````json
{
    "timestamp": "2025-01-31T18:27:56Z",
    "status": 404,
    "error": "Resource not found with the specified identifier or criteria.",
    "message": "Resource Not Found! ID: 17",
    "path": "/foods/17"
}
````
#### Key Attributes Explained:
- `timestamp`: Indicates when the error occurred;
- `status`: The HTTP status code (404 Not Found);
- `error`: Short description of the issue;
- `message`: Detailed information, including the resource identifier;
- `path`: The URI path of the failed request.
***
### 11. Exception Handling - DELETE:
This section covers the implementation of exception handling for the `delete` operation in the `FoodService` class, introducing custom exceptions and centralized error handling mechanisms.
#### 11.1 **NEW CLASS:** `services.exceptions.DatabaseException`:
- Custom exception created to handle database-related errors such as data integrity violations;
- **Constructor:**
```java
public DatabaseException(String message) {
    super(message);
}
````
#### 11.2 **UPDATE CLASS:** `controller.exceptions.ResourceExceptionHandler`:
Key Features:
- New Method: `handleDatabaseException`(DatabaseException e, HttpServletRequest request) handles database-related exceptions.
- Exception Handling Method:
````java
@ExceptionHandler(DatabaseException.class)
public ResponseEntity<StandardError> handleDatabaseException(DatabaseException e, HttpServletRequest request) {
    String error = "A database error occurred. Please check the provided data and try again.";
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandardError err = new StandardError(
            Instant.now(),                   // Current timestamp
            status.value(),                  // HTTP Status code (400)
            error,                           // Custom error message
            e.getMessage(),                  // Exception's detailed message
            request.getRequestURI()          // URI path where the error occurred
    );
    return ResponseEntity.status(status).body(err);
}
````
#### 11.3 Update Method delete in FoodService:
- Modify the `delete` method to throw the custom `ResourceNotFoundException` and `DatabaseException`:
````java
@DeleteMapping(value = "/{id}")
@Transactional
public void delete(Long id) {
    try {
        foodRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
        throw new ResourceNotFoundException(id);
    } catch (DataIntegrityViolationException e) {
        throw new DatabaseException(e.getMessage());
    }
}
````
#### 11.4 Requesting and Responding Food Data via Spring Boot RESTful API:
#### 11.4.1 Setting Up the RESTful API for HTTP Methods (Idempotent):
- **Endpoint**: DELETE `/foods/{id}`;
- **Purpose**: Deletes a specific Food item by its ID.
#### 11.4.2 Example DELETE Request (Resource Not Found):
- **Scenario**: The requested ID `17` does not exist, triggering the custom error response with a `404 Not Found` status code:
````json
DELETE http://localhost:8080/foods/17
````
#### 11.4.3 Example Error Response:
````json
{
    "timestamp": "2025-01-31T18:27:56Z",
    "status": 404,
    "error": "Resource not found with the specified identifier or criteria.",
    "message": "Resource Not Found! ID: 17",
    "path": "/foods/17"
}
````
#### 11.4.4 Example DELETE Request (Data Integrity Violation):
- **Scenario**: The requested ID `1` exists, but due to relationships with another entity, a `Database Constraint Violation` occurs, triggering the custom error response with a `400 Bad Request` status code:
````json
DELETE http://localhost:8080/foods/1
````
#### 11.4.5 Example Error Response:
````json
{
    "timestamp": "2025-01-31T18:35:02Z",
    "status": 400,
    "error": "A database error occurred. Please check the provided data and try again.",
    "message": "Could not delete food due to data integrity violation.",
    "path": "/foods/1"
}
````
#### Key Attributes Explained:
- `timestamp`: Indicates when the error occurred;
- `status`: The HTTP status code (either `404` for `Not Found` or `400` for `Bad Request` database errors );
- `error`: Short description of the issue;
- `message`: Detailed information, including the resource identifier;
- `path`: The URI path of the failed request.
***
### 12. Exception Handling - UPDATE:
This section covers the implementation of exception handling for the `update` operation in the `FoodService` class, introducing custom exceptions and centralized error handling mechanisms.
#### 12.1 Update Method update in FoodService:
- Modify the `update` method to throw the custom `ResourceNotFoundException`:
````java
    @Transactional
    public FoodResponseDTO update(Long id, FoodRequestDTO data) {
        try {
            Food entity = foodRepository.getReferenceById(id);
            updateData(entity, data);
            Food updated = foodRepository.save(entity);
            return new FoodResponseDTO(updated);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Food entity, FoodRequestDTO data) {
        entity.setTitle(data.title());
        entity.setPrice(data.price());
        entity.setImgUri(data.imgUri());
    }
````
#### 12.2 Requesting and Responding Food Data via Spring Boot RESTful API:
#### 12.2.1 Setting Up the RESTful API for HTTP Methods (Idempotent):
- **Endpoint**: PUT `/foods/{id}`;
- **Purpose**: Updates a specific Food item by its ID.
#### 12.2.2 Example PUT Request (Resource Not Found):
- **Scenario**: The requested ID `17` does not exist, triggering the custom error response with a `404 Not Found` status code:
````json
PUT http://localhost:8080/foods/17
Body -> raw -> JSON
````
````json
{
  "title": "Crepioca de Carne Seca",
  "price": 22.99,
  "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/crepioca.jpg"
}
````
#### 12.2.3 Example Error Response:
````json
{
    "timestamp": "2025-01-31T19:18:17Z",
    "status": 404,
    "error": "Resource not found with the specified identifier or criteria.",
    "message": "Resource Not Found! ID: 17",
    "path": "/foods/17"
}
````
#### Key Attributes Explained:
- `timestamp`: Indicates when the error occurred;
- `status`: The HTTP status code `404` for `Not Found`;
- `error`: Short description of the issue;
- `message`: Detailed information, including the resource identifier;
- `path`: The URI path of the failed request.
***
### 13. Exception Handling for `insert` and `findAll` Methods:
- This section covers the implementation of exception handling for the `insert` and `findAll` operations in the `FoodService` class, introducing custom exceptions and centralized error handling mechanisms.
#### 13.1 **NEW CLASS:** `services.exceptions.InvalidDataException`:
- Custom exception created to handle invalid data input during the `insert` operation;
- Constructor:
````java
public class InvalidDataException extends RuntimeException {
    public InvalidDataException() {
        super("Data integrity violation. Check the provided data.");
    }
}
````
#### 13.2 **NEW CLASS:** `services.exceptions.EmptyTableException`:
- Custom exception created to handle cases where the database table is empty or does not exist during the `findAll` operation;
- Constructor:
````java
public class EmptyTableException extends RuntimeException {
    public EmptyTableException() {
        super("The requested table is empty or does not exist.");
    }
}
````
#### 13.3 **NEW CLASS**: `services.exceptions.SQLGrammarException`:
- Custom exception created to handle `SQL` syntax errors during database operations;
- Constructor:
````java
public SQLGrammarException(String message) {
super(message);
}
````
#### 13.4 **NEW CLASS**: `services.exceptions.InvalidHttpMessageException`:
- Custom exception created to handle invalid `HTTP` message formats during the `insert` operation;
- Constructor:
````java
public InvalidHttpMessageException(String message) {
super(message);
}
````
#### 13.5 **UPDATE CLASS**: `controller.exceptions.ResourceExceptionHandler`:
**New Method:** handleBadRequestException(`InvalidDataException` e, HttpServletRequest request) handles invalid data input exceptions;
**New Method:** handleEmptyTableException(`EmptyTableException` e, HttpServletRequest request) handles empty table exceptions;
**New Method:** handleSQLGrammarException(`SQLGrammarException` e, HttpServletRequest request) handles SQL grammar exceptions;
**New Method:** handleHttpMessageNotReadable(`InvalidHttpMessageException` e, HttpServletRequest request) handles invalid HTTP message exceptions.
####  13.6 **UPDATE METHOD**: `insert` in `FoodService`:
- Modify the `insert` method to throw the custom `InvalidDataException` and `InvalidHttpMessageException`:
````java
@Transactional
public FoodResponseDTO insert(FoodRequestDTO data) {
  try {
    Food food = new Food(data);
    Food create = foodRepository.save(food);
    return new FoodResponseDTO(create);
  } catch (DataIntegrityViolationException e) {
    throw new InvalidDataException();
  } catch (InvalidHttpMessageException e) {
    throw new InvalidHttpMessageException(e.getMessage());
  }
}
````
#### 13.7 **UPDATE METHOD**: `findAll` in `FoodService`:
- Modify the `findAll` method to throw the custom `EmptyTableException`:
````java
@Transactional(readOnly = true)
public List<FoodResponseDTO> findAll() {
  try {
    List<Food> foods = foodRepository.findAll();
    if (foods.isEmpty()) {
      throw new EmptyTableException();
    }
    return foods.stream()
            .map(FoodResponseDTO::new)
            .toList();
  } catch (InvalidDataAccessResourceUsageException e) {
    throw new EmptyTableException();
  }
}
````
#### 13.8 Requesting and Responding Food Data via Spring Boot RESTful API:
#### 13.8.1 Setting Up the RESTful API for HTTP Methods (Idempotent):
- **Endpoint**: POST `/foods`;
- **Purpose**: Inserts a new Food item into the database.
#### 13.8.2 Example POST Request (Invalid Data):
-**Scenario**: The provided data is invalid, triggering the custom error response with a `400 Bad Request` status code:
````json
POST http://localhost:8080/foods
Body -> raw -> JSON
````
````json
{
  "title": "Tapioca de Frango",
  "price": -1.0,
  "imgUri": "https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/tapioca.jpg"
}
````
#### 13.8.3 Example Error Response:
````json
{
  "timestamp": "2025-01-31T22:15:53Z",
  "status": 400,
  "error": "Invalid data provided. Please check the input and try again.",
  "message": "Invalid data provided. Please check the input and try again.",
  "path": "/foods"
}
````
#### 13.8.4 Example POST Request (Invalid HTTP Message):
- **Scenario**: The HTTP message format is invalid, triggering the custom error response with a `400 Bad Request` status code:
````json
POST http://localhost:8080/foods
Body -> raw -> Text
Invalid JSON format
````
#### 13.8.5 Example Error Response:
````json
{
  "timestamp": "2025-01-31T19:18:17Z",
  "status": 400,
  "error": "Invalid input format. Please verify the request data.",
  "message": "Invalid input format. Please verify the request data.",
  "path": "/foods"
}
````
#### 13.8.6 Setting Up the RESTful API for HTTP Methods (Idempotent):
-**Endpoint** GET `/foods`;
-**Purpose**: Retrieves all Food items from the database.
#### 13.8.7 Example GET Request (Empty Table):
-**Scenario**: The database table is empty, triggering the custom error response with a `204 No Content` status code:
````json
GET http://localhost:8080/foods
````
#### 13.8.8 Example Error Response:
````json
{
  "timestamp": "2025-01-31T19:18:17Z",
  "status": 204,
  "error": "The requested table is empty or does not exist.",
  "message": "The requested table is empty or does not exist.",
  "path": "/foods"
}
````
#### 13.8.9 Example GET Request (SQL Grammar Error):
-**Scenario**: An SQL grammar error occurs, triggering the custom error response with a `500 Internal Server Error` status code:
````json
GET http://localhost:8080/foods
````
#### 13.8.10 Example Error Response:
````json
{
  "timestamp": "2025-01-31T19:18:17Z",
  "status": 500,
  "error": "SQL grammar error. Please check the database query syntax.",
  "message": "SQL grammar error. Please check the database query syntax.",
  "path": "/foods"
}
````
#### Key Attributes Explained:
- `timestamp`: Indicates when the error occurred;
- `status`: The HTTP status code (either `400` for `Bad Request`, `204` for `No Content`, or `500` for `Internal Server Error`);
- `error`: Short description of the issue;
- `message`: Detailed information, including the resource identifier;
- `path`: The URI path of the failed request.
***