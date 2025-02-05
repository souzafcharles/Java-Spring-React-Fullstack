## Part 1 - Project Requirements and Configurations:
### Food Domain Entity:
![Food Domain Entity](https://github.com/souzafcharles/Java-Spring-React-Fullstack/blob/main/Backend/digitalMenu/src/main/resources/static/img/food-entity.png)
***
### Logical Layers:
![Logical Layers](https://github.com/souzafcharles/Java-Spring-React-Fullstack/blob/main/Backend/digitalMenu/src/main/resources/static/img/logical-layers.png)
***
### Directory Structure:
````plaintext
digitalMenu-backend/
├── src/
│   ├── main/
│   │   ├── java/com/souza/charles/digitalMenu/
│   │   │   ├── controller/
│   │   │   │   ├── exceptions/
│   │   │   │   │   ├── ResourceExceptionHandler.java
│   │   │   │   │   ├── StandardError.java
│   │   │   │   ├── FoodController.java
│   │   │   ├── dto/
│   │   │   │   ├── FoodRequestDTO.java
│   │   │   │   ├── FoodResponseDTO.java
│   │   │   ├── entities/
│   │   │   │   ├── Food.java
│   │   │   ├── environment/
│   │   │   │   ├── LoadEnvironment.java
│   │   │   ├── repository/
│   │   │   │   ├── FoodRepository.java
│   │   │   ├── service/
│   │   │   │   ├── exceptions/
│   │   │   │   │   ├── DatabaseException.java
│   │   │   │   │   ├── EmptyResultException.java
│   │   │   │   │   ├── EmptyTableException.java
│   │   │   │   │   ├── InvalidDataException.java
│   │   │   │   │   ├── InvalidHttpMessageException.java
│   │   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   │   ├── SQLGrammarException.java
│   │   │   │   ├── FoodService.java
│   │   │   ├── DigitalMenuApplication.java
│   ├── resources/
│   │   ├── db/migrations/
│   │   ├── static.img/
│   │   ├── templates/
│   │   ├── application.properties
│   │   ├── application-dev.properties
│   │   ├── application-test.properties
├── test/
├── target/
├── .env
├── .gitattributes
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
````
***
### 1. Backend Requirements Specification:
#### 1.1. Dependencies and Tools:
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
### 2. Configuration of Profiles and Environment:
#### 2.1. Configuration of the `application.properties` File:
- This file defines the general configurations of the application, including the active profile and the application name:
```properties
spring.application.name=digitalMenu
spring.profiles.active=test
spring.jpa.open-in-view=true
```
#### 2.2. Configuration of the `application-test.properties` File:
- This file contains specific configurations for the `test` environment, utilising the in-memory `H2 Database Engine`:
````properties
# H2 Connection
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# H2 Client
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Show SQL
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
````
#### 2.3. Configuration of the `application-dev.properties` File:
- This file contains specific configurations for the `development` environment, utilising the `PostgreSQL` database:
````properties
# PostgreSQL Connection
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}

# Database Dialect Configuration
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

# Schema Management
spring.jpa.hibernate.ddl-auto=none

# Show SQL
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
````
#### 2.4. Creation of the `.env` File:
- At the root of the project, create a file named `.env` to declare the environment variables required for the `PostgreSQL` database connection:
````dotenv
DATABASE_URL=jdbc:postgresql://localhost:5432/your_database_name
DATABASE_USERNAME=your_database_username
DATABASE_PASSWORD=your_database_password
````
#### 2.5. Adding the Dotenv Dependency to pom.xml:
- To enable environment variable loading, add the following `Maven` dependency to the `pom.xml` file:
````xml
<dependency>
    <groupId>io.github.cdimascio</groupId>
    <artifactId>dotenv-java</artifactId>
    <version>3.0.0</version>
</dependency>
````
#### 2.6. Automatically Loading Environment Variables:
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
#### 2.6.1. Entry Point Integration:
- Call the environment loader at the project's entry point to ensure environment variables are available at runtime:
````java
public static void main(String[] args) {
    LoadEnvironment.loadEnv();
    SpringApplication.run(YourApplicationClass.class, args);
}
````
#### 2.7. Protecting Sensitive Information in Git:
- To protect sensitive credentials, add the `.env` file to `.gitignore`:
````gitignore
.env
````
***
### 3. Entity, DTOs, Repository, and Controller Classes:
#### 3.1. Requirements for Food Entity Class:
- **Entity Mapping:**
    - Create the `Food` class as an entity to represent a database table in the application;
    - Annotate the class with `@Entity` to define it as a persistent entity;
    - Use `@Table(name = "tb_foods")` to map it to the database table named `tb_foods`;
- **Attributes and Annotations:**
    - Define attributes `id`, `title`, `price`, and `image` to map to the respective columns in the database;
    - Annotate the `id` field with `@Id` and `@GeneratedValue(strategy = GenerationType.IDENTITY)` for automatic primary key generation;
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
#### 3.2. Requirements for FoodResponseDTO Record Class:
- **Record Declaration:**
    - Create the `FoodResponseDTO` as a `record` class to represent a simplified data structure for `responses`;
- **Attribute Definition:**
    - Define the attributes `id`, `title`, `price`, and `image` directly in the record's header to make them immutable and automatically provide accessor methods;
- **Entity-based Constructor:**
    - Implement a custom constructor that accepts a `Food` entity object as an argument;
    - Extract and map values from the `Food` entity to initialise the `FoodResponseDTO` attributes.
- **Purpose:**
    - Use this `record` for `transferring` food-related data from the `backend service layer` to the `controller response`, following RESTful API conventions.
#### 3.3. Requirements for FoodRequestDTO Record Class:
- **Record Declaration:**
    - Create the `FoodRequestDTO` as a `record` class to represent the request payload for creating or updating food entries.
- **Attribute Definition:**
    - Define the attributes `String title`, `Double price`, and `String image` directly in the record's header, enabling immutability and automatic generation of accessor methods.
- **Purpose:**
    - Use this `record` for `receiving` and validating user input from `client requests` to `create/insert` or `update` `Food` entities within the application.
#### 3.4. Requirements for FoodRepository Interface:
- **Repository Creation:**
    - Create the `FoodRepository` interface to handle data access operations for the `Food` entity;
- **JpaRepository Extension:**
    - Extend `JpaRepository<Food, Long>` to inherit common CRUD operations and JPA-specific functionalities;
- **Entity Association:**
    - Specify `Food` as the associated entity and `Long` as the type for its primary key;
#### 3.5. Requirements for FoodService Class:
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
#### 3.6. Requirements for FoodController Class:
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
### 4. Database Seeding with Food Data in H2 Database and PostgreSQL:
````sql
-- Drop the table if it exists-- Drop the table if it exists
DROP TABLE IF EXISTS tb_foods;

-- Create the table
CREATE TABLE tb_foods (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    image TEXT NOT NULL
);

-- Insert data into the table
INSERT INTO tb_foods (title, price, image) VALUES
('Batata Rústica', 24.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/rustic-fries.webp'),
('Coxinha de Carne Seca', 9.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/beef-coxinha.webp'),
('Esfirra de Frango', 23.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/chicken-esfiha.webp'),
('Feijoada', 34.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/feijoada.webp'),
('Hambúrguer', 29.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/burger.webp'),
('Hambúrguer Vegetariano', 24.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/veggie-burger.webp'),
('Lasanha à Bolonhesa', 45.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/lasagna.webp'),
('Macarronada à Bolonhesa', 39.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/spaghetti.webp'),
('Omelete', 14.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/omelette.webp'),
('Pizza de Calabresa', 49.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/pizza.webp'),
('Prato Feito', 29.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/set-meal.webp'),
('Sanduíche Natural', 25.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/sandwich.webp'),
('Sorvete de Chocolate', 19.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/ice-cream.webp'),
('Suco de Morango', 14.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/strawberry-juice.webp'),
('Sushi', 59.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/sushi.webp');
````
***
### 5. Requesting and Responding Food Data via Spring Boot RESTful API:
#### 5.1. Setting Up the RESTful API for HTTP Methods (Idempotent):
- **Endpoint**: GET `/foods`: Retrieves a list of all Food.
#### 5.2. Example GET Request:
````json
http://localhost:8080/foods
````
#### 5.3. Example Response:
````json
[
  {
    "id": 1,
    "title": "Batata Rústica",
    "price": 24.99,
    "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/rustic-fries.webp"
  },
  {
    "id": 2,
    "title": "Coxinha de Carne Seca",
    "price": 9.99,
    "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/beef-coxinha.webp"
  },
  {
    "id": 3,
    "title": "Esfirra de Frango",
    "price": 23.99,
    "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/chicken-esfiha.webp"
  },
  {
    "id": 4,
    "title": "Feijoada",
    "price": 34.99,
    "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/feijoada.webp"
  },
  {
    "id": 5,
    "title": "Hambúrguer",
    "price": 29.99,
    "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/burger.webp"
  },
  {
    "id": 6,
    "title": "Hambúrguer Vegetariano",
    "price": 24.99,
    "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/veggie-burger.webp"
  },
  {
    "id": 7,
    "title": "Lasanha à Bolonhesa",
    "price": 45.99,
    "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/lasagna.webp"
  },
  {
    "id": 8,
    "title": "Macarronada à Bolonhesa",
    "price": 39.99,
    "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/spaghetti.webp"
  },
  {
    "id": 9,
    "title": "Omelete",
    "price": 14.99,
    "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/omelette.webp"
  },
  {
    "id": 10,
    "title": "Pizza de Calabresa",
    "price": 49.99,
    "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/pizza.webp"
  },
  {
    "id": 11,
    "title": "Prato Feito",
    "price": 29.99,
    "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/set-meal.webp"
  },
  {
    "id": 12,
    "title": "Sanduíche Natural",
    "price": 25.99,
    "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/sandwich.webp"
  },
  {
    "id": 13,
    "title": "Sorvete de Chocolate",
    "price": 19.99,
    "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/ice-cream.webp"
  },
  {
    "id": 14,
    "title": "Suco de Morango",
    "price": 14.99,
    "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/strawberry-juice.webp"
  },
  {
    "id": 15,
    "title": "Sushi",
    "price": 59.99,
    "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/sushi.webp"
  }
]
````
#### 5.4. Setting Up the RESTful API for HTTP Methods (Non-Idempotent):
- **Endpoint**: POST `/foods`: Creates a new Food.
#### 5.5. Example POST Request:
````json
http://localhost:8080/foods
Body -> raw -> JSON
````
````json
{
  "title": "Tapioca de Carne Seca",
  "price": 21.99,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/tapioca.webp"
}
````
#### 5.6. Example Response:
````json
{
  "id": 16,
  "title": "Tapioca de Carne Seca",
  "price": 21.99,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/tapioca.webp"
}
````
#### 5.7. Setting Up the RESTful API for HTTP Methods (Idempotent):
- **Endpoint**: GET `/foods/{id}`: Retrieves a specific Food item by its ID.

#### 5.8. Example GET Request:
````json
http://localhost:8080/foods/10
````        
#### 5.9. Example Response:
````json
{
  "id": 10,
  "title": "Pizza de Calabresa",
  "price": 49.99,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/pizza.jpg"
}
````
#### 5.10. Setting Up the RESTful API for HTTP Methods (Idempotent):
- **Endpoint**: PUT `/foods/{id}`: Updates a specific Food item by its ID.
#### 5.11. Example PUT Request:
````json
http://localhost:8080/foods/16
Body -> raw -> JSON
````
````json
{
  "title": "Temaki de Salmão",
  "price": 34.99,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/temaki.webp"
}
````
#### 5.12. Example Response:
````json
{
  "id": 16,
  "title": "Temaki de Salmão",
  "price": 20.99,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/temaki.jpg"
}
````
#### 5.13. Setting Up the RESTful API for HTTP Methods (Idempotent):
- **Endpoint**: DELETE `/foods/{id}`: Deletes a specific Food item by its ID.
#### 5.14. Example DELETE Request:
````json
http://localhost:8080/foods/1
````
#### 5.15. Example Response:
````json
HTTP Status 204 - No Content
````
#### 5.16. Idempotent and Non-Idempotent Methods:
- **Idempotent Method**: `GET`, `PUT`, and `DELETE` are idempotent, meaning multiple identical requests should have the same effect as a single request;
- **Non-Idempotent Method**: `POST` is non-idempotent, meaning multiple identical requests may have additional effects.
***
### 6. Exception Handling for `findById` Method:
#### 6.1. **NEW CLASS:** `services.exceptions.ResourceNotFoundException` (Custom Exception)`:
- This section introduces the implementation of custom exception handling for the `findById` method in the FoodService class, introducing custom exceptions and centralized error handling mechanisms.
- Create a custom exception named `ResourceNotFoundException`, extending `RuntimeException`;
- Constructor takes an `Object id` as a parameter to provide a specific resource ID in the error message;
- Provide a detailed message when an exception occurs: `"Resource Not Found! ID: [id]"`.
#### 6.2. **NEW CLASS:** `controller.exceptions.StandardError` (Entity for Standardized Error Messages):
- Create the `StandardError` class to represent error messages for RESTful APIs:
- Include the following attributes:
  - `Instant timestamp`: formatted with `@JsonFormat` to comply with standard time formats;
  - `Integer status`: the HTTP response status code;
  - `String error`: short description of the error;
  - `String message`: detailed error message;
  - `String path`: the URI that generated the error;
- Provide constructors, getters, and setters to support object manipulation;
- Implement `Serializable` for object serialization when needed.
#### 6.3. **NEW CLASS:** `controller.exceptions.ResourceExceptionHandler`:
- The `ResourceExceptionHandler` class is responsible for intercepting exceptions thrown during the execution of RESTful requests in the application and converting them into standardized HTTP error responses.
#### 6.3.1. **Key Features:**
- **Global Exception Handling:** The class is annotated with `@ControllerAdvice`, which enables centralized exception handling across all `@Controller` components;
- **Error Response Standardization:** Provides a mechanism to customize the error response by creating and returning `StandardError` objects with detailed error information.
#### 6.3.2. **Detailed Breakdown of the `handleResourceNotFound` Method:**
````java
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<StandardError> handleResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
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
#### 6.3.3. Annotations and Parameters:
- `@ExceptionHandler`(ResourceNotFoundException.class): Maps the method to handle exceptions of type `ResourceNotFoundException`;
- `ResourceNotFoundException` e: The exception object containing the error details;
- `HttpServletRequest` request: Captures information about the HTTP request, such as the request URI.
#### 6.3.4. Response Construction:
- **Timestamp**: `Instant.now()` ensures the current time is recorded when the exception is handled;
- **Status Code**: `HttpStatus.NOT_FOUND.value()` returns the HTTP status code 404;
- **Error Message**: The variable `error` provides a concise description for the error;
- **Detailed Message**: `e.getMessage()` displays the custom error message from ResourceNotFoundException;
- **Request Path**: `request.getRequestURI()` specifies the URI that caused the exception.
#### 6.4. Update Method findById in FoodService:
- Modify the `findById` method to throw the custom `ResourceNotFoundException`:
````java
@Transactional(readOnly = true)
public FoodResponseDTO findById(Long id) {
    Food result = foodRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    return new FoodResponseDTO(result);
}
````
#### 6.5. Requesting and Responding Food Data via Spring Boot RESTful API:
#### 6.5.1. Setting Up the RESTful API for HTTP Methods (Idempotent):
- **Endpoint**: GET `/foods/{id}`;
- **Purpose**: Retrieves a specific Food item by its ID.
#### 6.5.2. Example GET Request:
- **Scenario**: The requested ID `17` does not exist, triggering the custom error response with a `404 Not Found` status code:
````json
http://localhost:8080/foods/17
````
#### 6.5.3. Example Error Response:
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
### 7. Exception Handling - DELETE:
This section covers the implementation of exception handling for the `delete` operation in the `FoodService` class, introducing custom exceptions and centralized error handling mechanisms.
#### 7.1. **NEW CLASS:** `services.exceptions.DatabaseException`:
- Custom exception created to handle database-related errors such as data integrity violations;
- **Constructor:**
```java
public DatabaseException(String message) {
    super(message);
}
````
#### 7.2. **UPDATE CLASS:** `controller.exceptions.ResourceExceptionHandler`:
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
#### 7.3. Update Method delete in FoodService:
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
#### 7.4. Requesting and Responding Food Data via Spring Boot RESTful API:
#### 7.4.1. Setting Up the RESTful API for HTTP Methods (Idempotent):
- **Endpoint**: DELETE `/foods/{id}`;
- **Purpose**: Deletes a specific Food item by its ID.
#### 7.4.2. Example DELETE Request (Resource Not Found):
- **Scenario**: The requested ID `17` does not exist, triggering the custom error response with a `404 Not Found` status code:
````json
DELETE http://localhost:8080/foods/17
````
#### 7.4.3. Example Error Response:
````json
{
    "timestamp": "2025-01-31T18:27:56Z",
    "status": 404,
    "error": "Resource not found with the specified identifier or criteria.",
    "message": "Resource Not Found! ID: 17",
    "path": "/foods/17"
}
````
#### 7.4.4. Example DELETE Request (Data Integrity Violation):
- **Scenario**: The requested ID `1` exists, but due to relationships with another entity, a `Database Constraint Violation` occurs, triggering the custom error response with a `400 Bad Request` status code:
````json
DELETE http://localhost:8080/foods/1
````
#### 7.4.5. Example Error Response:
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
### 8. Exception Handling - UPDATE:
This section covers the implementation of exception handling for the `update` operation in the `FoodService` class, introducing custom exceptions and centralized error handling mechanisms.
#### 8.1. Update Method update in FoodService:
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
        entity.setImage(data.image());
    }
````
#### 8.2. Requesting and Responding Food Data via Spring Boot RESTful API:
#### 8.2.1. Setting Up the RESTful API for HTTP Methods (Idempotent):
- **Endpoint**: PUT `/foods/{id}`;
- **Purpose**: Updates a specific Food item by its ID.
#### 8.2.2. Example PUT Request (Resource Not Found):
- **Scenario**: The requested ID `17` does not exist, triggering the custom error response with a `404 Not Found` status code:
````json
PUT http://localhost:8080/foods/17
Body -> raw -> JSON
````
````json
{
  "title": "Crepioca de Carne Seca",
  "price": 22.99,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/crepioca.jpg"
}
````
#### 8.2.3. Example Error Response:
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
### 9. Exception Handling for `insert` and `findAll` Methods:
- This section covers the implementation of exception handling for the `insert` and `findAll` operations in the `FoodService` class, introducing custom exceptions and centralized error handling mechanisms.
#### 9.1. **NEW CLASS:** `services.exceptions.InvalidDataException`:
- Custom exception created to handle invalid data input during the `insert` operation;
- Constructor:
````java
public class InvalidDataException extends RuntimeException {
    public InvalidDataException() {
        super("Data integrity violation. Check the provided data.");
    }
}
````
#### 9.2. **NEW CLASS:** `services.exceptions.EmptyTableException`:
- Custom exception created to handle cases where the database table is empty or does not exist during the `findAll` operation;
- Constructor:
````java
public class EmptyTableException extends RuntimeException {
    public EmptyTableException() {
        super("The requested table is empty or does not exist.");
    }
}
````
#### 9.3. **NEW CLASS**: `services.exceptions.SQLGrammarException`:
- Custom exception created to handle `SQL` syntax errors during database operations;
- Constructor:
````java
public SQLGrammarException(String message) {
super(message);
}
````
#### 9.4. **NEW CLASS**: `services.exceptions.InvalidHttpMessageException`:
- Custom exception created to handle invalid `HTTP` message formats during the `insert` operation;
- Constructor:
````java
public InvalidHttpMessageException(String message) {
super(message);
}
````
#### 9.5. **UPDATE CLASS**: `controller.exceptions.ResourceExceptionHandler`:
**New Method:** handleBadRequestException(`InvalidDataException` e, HttpServletRequest request) handles invalid data input exceptions;
**New Method:** handleEmptyTableException(`EmptyTableException` e, HttpServletRequest request) handles empty table exceptions;
**New Method:** handleSQLGrammarException(`SQLGrammarException` e, HttpServletRequest request) handles SQL grammar exceptions;
**New Method:** handleHttpMessageNotReadable(`InvalidHttpMessageException` e, HttpServletRequest request) handles invalid HTTP message exceptions.
####  9.6. **UPDATE METHOD**: `insert` in `FoodService`:
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
  } catch (HttpMessageNotReadableException e) {
    throw new InvalidHttpMessageException(e.getMessage());
  }
}
````
#### 9.7. **UPDATE METHOD**: `findAll` in `FoodService`:
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
#### 9.8. Requesting and Responding Food Data via Spring Boot RESTful API:
#### 9.8.1. Setting Up the RESTful API for HTTP Methods (Idempotent):
- **Endpoint**: POST `/foods`;
- **Purpose**: Inserts a new Food item into the database.
#### 9.8.2. Example POST Request (Invalid Data):
-**Scenario**: The provided data is invalid, triggering the custom error response with a `400 Bad Request` status code:
````json
POST http://localhost:8080/foods
Body -> raw -> JSON
````
````json
{
  "title": "Tapioca de Frango",
  "price": -1.0,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/tapioca.jpg"
}
````
#### 9.8.3. Example Error Response:
````json
{
  "timestamp": "2025-01-31T22:15:53Z",
  "status": 400,
  "error": "Invalid data provided. Please check the input and try again.",
  "message": "Invalid data provided. Please check the input and try again.",
  "path": "/foods"
}
````
#### 9.8.4. Example POST Request (Invalid HTTP Message):
- **Scenario**: The HTTP message format is invalid, triggering the custom error response with a `400 Bad Request` status code:
````json
POST http://localhost:8080/foods
Body -> raw -> Text
Invalid JSON format
````
#### 9.8.5. Example Error Response:
````json
{
  "timestamp": "2025-01-31T19:18:17Z",
  "status": 400,
  "error": "Invalid input format. Please verify the request data.",
  "message": "Invalid input format. Please verify the request data.",
  "path": "/foods"
}
````
#### 9.8.6. Setting Up the RESTful API for HTTP Methods (Idempotent):
-**Endpoint** GET `/foods`;
-**Purpose**: Retrieves all Food items from the database.
#### 9.8.7. Example GET Request (Empty Table):
-**Scenario**: The database table is empty, triggering the custom error response with a `204 No Content` status code:
````json
GET http://localhost:8080/foods
````
#### 9.8.8. Example Error Response:
````json
{
  "timestamp": "2025-01-31T19:18:17Z",
  "status": 204,
  "error": "The requested table is empty or does not exist.",
  "message": "The requested table is empty or does not exist.",
  "path": "/foods"
}
````
#### 9.8.9. Example GET Request (SQL Grammar Error):
-**Scenario**: An SQL grammar error occurs, triggering the custom error response with a `500 Internal Server Error` status code:
````json
GET http://localhost:8080/foods
````
#### 9.8.10. Example Error Response:
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

### 10. Seeding the Database with Additional Food Items in H2 and PostgreSQL:
````sql
INSERT INTO tb_foods (title, price, image) VALUES
('Tapioca de Carne Seca', 12.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/tapioca.webp'),
('Temaki de Salmão', 34.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/temaki.webp'),
('Strogonoff de Frango', 29.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/stroganoff.webp'),
('Batata Frita Palito', 19.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/fries.webp'),
('Vinho Tinto', 49.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/wine.webp'),
('Água sem Gás', 4.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/bottle-water.webp'),
('Pudim', 14.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/pudding.webp'),
('Suco de Laranja', 9.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/orange-juice.webp'),
('Sorvete de Morango', 17.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/strawberry-ice-cream.webp'),
('Sanduíche de Frango', 21.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/chicken-sandwich.webp'),
('Pizza Margherita', 44.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/margherita-pizza.webp');
````
