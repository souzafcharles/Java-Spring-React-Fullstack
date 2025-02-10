## Part 1 - Project Requirements and Configurations:

### 1. Food Domain Entity:

![Food Domain Entity](https://github.com/souzafcharles/Java-Spring-React-Fullstack/blob/main/Backend/digitalMenu/src/main/resources/static/img/food-entity.png)
***

### 2. Logical Layers:

![Logical Layers](https://github.com/souzafcharles/Java-Spring-React-Fullstack/blob/main/Backend/digitalMenu/src/main/resources/static/img/logical-layers.png)
***

### 3. Directory Structure:

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
│   │   │   │   │   ├── DuplicateImageException.java
│   │   │   │   │   ├── EmptyTableException.java
│   │   │   │   │   ├── InvalidDataException.java
│   │   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   ├── FoodService.java
│   │   │   ├── DigitalMenuApplication.java
│   ├── resources/
│   │   ├── db/changelog/
│   │   │   ├── db.changelog-master.yaml
│   │   ├── static.img/
│   │   ├── templates/
│   │   ├── application.properties
│   │   ├── application-dev.properties
│   │   ├── application-test.properties
│   │   ├── changelog-master.yaml
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

### 4. Backend Requirements Specification:

- This section outlines the key dependencies and tools required for setting up the backend of the application, including
  configuration details and essential libraries for development.

#### 4.1. Dependencies and Tools:

- **Spring Initializr Configuration:**
    - **Build Tool:** Maven;
    - **Programming Language:** Java 21;
    - **Packaging Type:** JAR.

- **Required Dependencies:**
    - **Spring Web:** For building RESTful APIs and handling HTTP requests;
    - **Spring Data JPA:** For database access and ORM (Object Relational Mapping);
    - **H2 Database:** For in-memory database support during development and testing;
    - **PostgreSQL Driver:** For connecting to PostgreSQL databases;
    - **Liquibase Core:** For database schema migration management;
    - **Spring Boot DevTools:** For enhancing developer experience with automatic restarts and live reloads;
    - **dotenv-java:** For environment variable management.

***

### 5. Configuration of Profiles, Environment and Database Migrations:

- This section covers the setup and configuration of application profiles, environment-specific properties, environment
  variable management, and database migrations.

#### 5.1. Configuration of the `application.properties` File:

- This file defines the general configurations of the application, including the active profile and the application
  name:

```properties
spring.application.name=digitalMenu
spring.profiles.active=test
spring.jpa.open-in-view=true
```

#### 5.2. Configuration of the `application-test.properties` File:

- This file contains specific configurations for the `test` environment, utilising the in-memory `H2 Database Engine`
  and configuring `Liquibase` for database migrations:

```properties
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
# Liquibase Configuration
spring.liquibase.enabled=true
spring.liquibase.change-log=classpath:/db/changelog/db.changelog-master.yaml
# Database Dialect Configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=none
```

#### 5.3. Configuration of the `application-dev.properties` File:

- This file contains specific configurations for the development environment, utilising the `PostgreSQL` database and
  configuring `Liquibase` for database migrations:

```properties
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
# Liquibase Configuration
spring.liquibase.enabled=true
spring.liquibase.change-log=classpath:/db/changelog/db.changelog-master.yaml
```

#### 5.4. Creation of the `.env` File:

- At the root of the project, create a file named `.env` to declare the environment variables required for the
  `PostgreSQL` database connection:

```dotenv
DATABASE_URL=jdbc:postgresql://localhost:5432/your_database_name
DATABASE_USERNAME=your_database_username
DATABASE_PASSWORD=your_database_password
```

#### 5.5. Adding the Dotenv Dependency to `pom.xml`:

- To enable environment variable loading, add the following `Maven` dependency to the `pom.xml` file:

```xml

<dependency>
    <groupId>io.github.cdimascio</groupId>
    <artifactId>dotenv-java</artifactId>
    <version>3.0.0</version>
</dependency>
```

#### 5.6. Automatically Loading Environment Variables:

- Create a utility class to load the environment variables from the `.env` file:

```java
import io.github.cdimascio.dotenv.Dotenv;

public class LoadEnvironment {
    public static void loadEnv() {
        Dotenv dotenv = Dotenv.configure().load();
        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );
    }
}
```

#### 5.6.1. Entry Point Integration:

- Call the environment loader at the project's entry point to ensure environment variables are available at runtime:

```java
public static void main(String[] args) {
    LoadEnvironment.loadEnv();
    SpringApplication.run(YourApplicationClass.class, args);
}
```

#### 5.7. Protecting Sensitive Information in Git:

- To protect sensitive credentials, add the `.env` file to `.gitignore`:

```gitignore
.env
```

#### 5.8. Database Migrations:

- **Creation of the Main Changelog:**
    - Inside the folder `src/main/resources/db/changelog`, create the file `db.changelog-master.yaml`. This file serves
      as the main entry point for database migrations.

```yaml
databaseChangeLog:
  - changeSet:
      id: 1
      author: Charles
      changes:
        - createTable:
            tableName: tb_foods
            columns:
              - column:
                  name: id
                  type: SERIAL
                  autoIncrement: true
                  constraints:
                    primaryKey: true
              - column:
                  name: title
                  type: TEXT
                  constraints:
                    nullable: false
              - column:
                  name: price
                  type: DECIMAL(10, 2)
                  constraints:
                    nullable: false
              - column:
                  name: image
                  type: TEXT
                  constraints:
                    nullable: false
```

***

### 6. Database Seeding with Food Data in H2 Database and PostgreSQL:

- This section covers the initialization of the `tb_foods` table with sample food data in both `H2 Database` and
  `PostgreSQL`. It includes the necessary `SQL` commands to create the table and insert predefined food records,
  ensuring a consistent
  dataset for development and testing environments:

````sql
-- Drop the table if it exists
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
('Sushi', 59.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/sushi.webp'),
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

***

## Part 2 - Development Requirements and Specifications:

### 1. Entity, DTOs Pattern (Data Transfer Objects), Repository, and Controller Classes:

- This section provides detailed requirements and specifications for the implementation of the entity, DTOs, repository,
  service, and controller classes in the `Food` module.

#### 1.1. Requirements for Food Entity Class:

- **Entity Mapping:**
    - Create the `Food` class as an entity to represent a database table in the application;
    - Annotate the class with `@Entity` to define it as a persistent entity;
    - Use `@Table(name = "tb_foods")` to map it to the database table named `tb_foods`.

- **Attributes and Annotations:**
    - Define attributes `id`, `title`, `price`, and `image` to map to the respective columns in the database;
    - Annotate the `id` field with `@Id` and `@GeneratedValue(strategy = GenerationType.IDENTITY)` for automatic primary
      key generation.

- **Constructors:**
    - Create a no-argument constructor required by `JPA`;
    - Provide an all-arguments constructor for convenience;
    - Include a constructor that accepts a `FoodRequestDTO` for mapping `DTO` data to the entity.

- **Accessors and Mutators:**
    - Implement `getters` and `setters` for all attributes to allow data manipulation.

- **Equals and HashCode:**
    - Override the `equals()` method to compare entities based on the `id` and `image` attributes;
    - Override `hashCode()` to provide a consistent hash for `Food` objects, using `Objects.hashCode(id)` and
      `Objects.hashCode(image)`.

- **Serializable Interface:**
    - Implement the `Serializable` interface to support object serialization for the entity when necessary (e.g., when
      transferring objects between systems).

#### 1.2. Requirements DTO Pattern for FoodResponseDTO Record Class:

- **Record Declaration:**
    - Create the `FoodResponseDTO` as a `record` class to represent a simplified data structure for `responses`.

- **Attribute Definition:**
    - Define the attributes `id`, `title`, `price`, and `image` directly in the record's header to make them immutable
      and automatically provide accessor methods.

- **Entity-based Constructor:**
    - Implement a custom constructor that accepts a `Food` entity object as an argument;
    - Extract and map values from the `Food` entity to initialize the `FoodResponseDTO` attributes.

- **Purpose:**
    - Use this `record` for transferring food-related data from the backend service layer to the controller response,
      following RESTful API conventions.

#### 1.3. Requirements DTO Pattern for FoodRequestDTO Record Class:

- **Record Declaration:**
    - Create the `FoodRequestDTO` as a `record` class to represent the request payload for creating or updating food
      entries.

- **Attribute Definition:**
    - Define the attributes `String title`, `Double price`, and `String image` directly in the record's header, enabling
      immutability and automatic generation of accessor methods.

- **Purpose:**
    - Use this `record` for receiving and validating user input from client requests to create/insert or update `Food`
      entities within the application.

#### 1.4. Requirements for FoodRepository Interface:

- **Repository Creation:**
    - Create the `FoodRepository` interface to handle data access operations for the `Food` entity.

- **JpaRepository Extension:**
    - Extend `JpaRepository<Food, Long>` to inherit common CRUD operations and JPA-specific functionalities.

- **Entity Association:**
    - Specify `Food` as the associated entity and `Long` as the type for its primary key.

- **Handling Non-Unique Results:**
    - Use `Optional<Food> findByImage(String image);` only if the `image` attribute is expected to be unique. Otherwise,
      consider returning a list.

#### 1.5. Requirements for FoodService Class:

- **Service Component Annotation:**
    - Use the `@Service` annotation to define the class as a Spring service component.

- **Dependency Injection:**
    - Inject `FoodRepository` using `@Autowired` for dependency injection.

- **Retrieve All Food Entities:**
    - **Method:** `findAll`
    - **Purpose:** Fetches all entries from the database and maps them to `FoodResponseDTO` objects.
    - **Transaction Management:** Annotate with `@Transactional(readOnly = true)` to ensure the method runs within a
      read-only transaction for optimised database performance.
    - **Exception Handling:** Throws `EmptyTableException` if no data is found.

- **Insert New Food Entity:**
    - **Method:** `insert`
    - **Purpose:** Saves a new `Food` entity in the database.
    - **Transaction Management:** Annotate with `@Transactional` to ensure this method runs within a transactional
      context, enabling database operation rollbacks in case of exceptions.
    - **Validation:** Checks for null or invalid values for `title`, `price`, and `image`. Throws `InvalidDataException`
      if validation fails.
    - **Duplicate Image Check:** Uses `foodRepository.findByImage` to verify image uniqueness. Throws
      `DuplicateImageException` if a duplicate is found.
    - **Response:** Returns a `FoodResponseDTO` containing the created `Food` entity.

- **Retrieve Food Entity by ID:**
    - **Method:** `findById`
    - **Purpose:** Fetches a single `Food` entity by its identifier and maps it to a `FoodResponseDTO`.
    - **Transaction Management:** Annotate with `@Transactional(readOnly = true)` to ensure the method runs within a
      read-only transaction.
    - **Exception Handling:** Throws `ResourceNotFoundException` if the `Food` entity is not found.

- **Update Existing Food Entity:**
    - **Method:** `update`
    - **Purpose:** Updates an existing `Food` entity by its identifier.
    - **Transaction Management:** Annotate with `@Transactional` to ensure the method is executed in a transactional
      context for data consistency.
    - **Duplicate Image Check:** Verifies if the new image already exists for another entity. Throws
      `DuplicateImageException` if a duplicate is found.
    - **Data Update:** Calls the private helper method `updateData` to update the `Food` entity with validated data.
    - **Validation:** Ensures that `title`, `price`, and `image` are valid and non-null. Throws `InvalidDataException`
      if validation fails.
    - **Response:** Returns a `FoodResponseDTO` containing the updated `Food` entity.

- **Delete Food Entity:**
    - **Method:** `delete`
    - **Purpose:** Deletes a `Food` entity by its identifier.
    - **Transaction Management:** Annotate with `@Transactional` to ensure the operation is part of a transaction,
      allowing rollback in case of failure.
    - **Exception Handling:** Throws `ResourceNotFoundException` if the entity does not exist and `DatabaseException`
      for integrity violations.

#### 1.6. Requirements for FoodController Class:

- Create the `FoodController` class to manage RESTful endpoints for the `Food` resource;
- Use `@RestController` annotation to mark it as a REST controller for Spring;
- Map requests using the `@RequestMapping` annotation for the `/foods` endpoint;
- Inject `FoodService` using `@Autowired` for service dependency injection.
- Implement a method to handle `POST` requests:
    - `insert`: Handles the creation of a new `Food` resource;
    - Use `@PostMapping` annotation to map POST requests to `/foods`;
    - Parameters:
        - `@RequestBody FoodRequestDTO data`: Captures the details for the new food from the request body;
    - Response: Returns a `ResponseEntity<FoodResponseDTO>` with an HTTP 201 (Created) status and the created `Food`
      object.
- Implement a method to handle `GET` requests:
    - `findAll`: Retrieves all available `Food` resources;
    - Use `@GetMapping` annotation to map GET requests to `/foods`;
    - Response: Returns a `ResponseEntity<List<FoodResponseDTO>>` with an HTTP 200 (OK) status and the list of foods;
    - Handles empty lists and returns an HTTP 204 (No Content) status if no foods are found.
- Implement a method to handle `GET` requests by `id`:
    - `findById`: Fetches a specific `Food` by its identifier;
    - Use `@GetMapping(value = "/{id}")` annotation to map GET requests for a specific `Food`;
    - Parameters:
        - `@PathVariable Long id`: Captures the identifier of the food from the URI;
    - Response: Returns a `ResponseEntity<FoodResponseDTO>` with an HTTP 200 (OK) status and the corresponding `Food`
      object.
- Implement a method to handle `PUT` requests for updating a `Food` by `id`:
    - `update`: Updates an existing `Food` resource by its identifier;
    - Use `@PutMapping(value = "/{id}")` annotation to map PUT requests;
    - Parameters:
        - `@PathVariable Long id`: Captures the identifier of the food from the URI;
        - `@RequestBody FoodRequestDTO data`: Captures the updated food details from the request body;
    - Response: Returns a `ResponseEntity<FoodResponseDTO>` with an HTTP 200 (OK) status and the updated `Food` object.
- Implement a method to handle `DELETE` requests for deleting a `Food` by `id`:
    - `delete`: Removes an existing `Food` resource by its identifier;
    - Use `@DeleteMapping(value = "/{id}")` annotation to map DELETE requests;
    - Parameters:
        - `@PathVariable Long id`: Captures the identifier of the food to be deleted from the URI;
    - Response: Returns a `ResponseEntity<Void>` with an HTTP 204 (No Content) status after successful deletion.
- Use `@CrossOrigin(origins = "*", allowedHeaders = "*")` annotation to allow cross-origin requests from any origin;
- Ensure that the class implements the `Serializable` interface to support object serialization when needed.

***

### 2. Exception Handling for `findById` Method:

- This section introduces the implementation of custom exception handling for the `findById` method in the `FoodService`
  class, introducing custom exceptions and centralized error handling mechanisms.

#### 2.1. **NEW CLASS:** `services.exceptions.ResourceNotFoundException` (Custom Exception)`:

- Create a custom exception named `ResourceNotFoundException`, extending `RuntimeException`;
- Constructor takes an `Object id` as a parameter to provide a specific resource ID in the error message;
- Provide a detailed message when an exception occurs: `"Resource Not Found! ID: [id]"`.

#### 2.2. **NEW CLASS:** `controller.exceptions.StandardError` (Entity for Standardized Error Messages):

- Create the `StandardError` class to represent error messages for RESTful APIs:
- Include the following attributes:
    - `Instant timestamp`: formatted with `@JsonFormat` to comply with standard time formats;
    - `Integer status`: the HTTP response status code;
    - `String error`: short description of the error;
    - `String message`: detailed error message;
    - `String path`: the URI that generated the error;
- Provide constructors, getters, and setters to support object manipulation;
- Implement `Serializable` for object serialization when needed.

#### 2.3. **NEW CLASS:** `controller.exceptions.ResourceExceptionHandler` (Custom Exception):

- The `ResourceExceptionHandler` class is responsible for intercepting exceptions thrown during the execution of RESTful
  requests in the application and converting them into standardized HTTP error responses.

#### 2.3.1. **Key Features:**

- **Global Exception Handling:** The class is annotated with `@ControllerAdvice`, which enables centralized exception
  handling across all `@Controller` components;
- **Error Response Standardization:** Provides a mechanism to customize the error response by creating and returning
  `StandardError` objects with detailed error information.

#### 2.3.2. **NEW METHOD:** `handleResourceNotFoundException`:

- This method is responsible for handling exceptions of type `ResourceNotFoundException` and converting them into
  standardized HTTP error responses:

````java
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<StandardError> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
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

- **Annotations and Parameters**:
    - `@ExceptionHandler`(ResourceNotFoundException.class): Maps the method to handle exceptions of type
      `ResourceNotFoundException`;
    - It takes two parameters:
        - `ResourceNotFoundException` e: The exception object containing the error details;
        - `HttpServletRequest` request: Captures information about the HTTP request, such as the request URI.
    - The method constructs a `StandardError` object, which includes:
        - **Timestamp**: `Instant.now()` ensures the current time is recorded when the exception is handled;
        - **Status Code**: `HttpStatus.NOT_FOUND.value()` returns the HTTP status code 404;
        - **Error Message**: The variable `error` provides a concise description for the error;
        - **Detailed Message**: `e.getMessage()` displays the custom error message from ResourceNotFoundException;
        - **Request Path**: `request.getRequestURI()` specifies the URI that caused the exception.
    - The method returns a `ResponseEntity` containing the `StandardError` object and the appropriate HTTP status code.

***

### 3. Exception Handling for `insert` Method:

- This section introduces the implementation of custom exception handling for the `insert` method in the `FoodService`
  class, focusing on scenarios where a duplicate image is detected during `Food` registration.

#### 3.1. **NEW CLASS:** `services.exceptions.InvalidDataException` (Custom Exception):

- Create a custom exception named `InvalidDataException`, extending `RuntimeException`;
- Constructor provides a default error message: `"Mandatory fields must not be null or empty."`;
- This exception is thrown when mandatory fields (e.g., `title` or `price` or `image`) are null or empty during `Food`
  registration.

#### 3.2. **NEW METHOD:** `handleBadRequestException` on class `controller.exceptions.ResourceExceptionHandler`:

- This method is responsible for handling exceptions of type `InvalidDataException` and converting them into
  standardized HTTP error responses:

````java
@ExceptionHandler(InvalidDataException.class)
public ResponseEntity<StandardError> handleBadRequestException(InvalidDataException e, HttpServletRequest request) {
    String error = "Invalid data provided. Please check the input and try again.";
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandardError err = new StandardError(
            Instant.now(),                   // Current timestamp of the error
            status.value(),                  // HTTP Status code (400)
            error,                           // Custom error message
            e.getMessage(),                  // Exception's detailed message
            request.getRequestURI()          // URI path where the error occurred
    );
    return ResponseEntity.status(status).body(err);
}
````

- **Annotations and Parameters:**
    - `@ExceptionHandler(InvalidDataException.class)`: Maps the method to handle exceptions of type
      `InvalidDataException`;
    - It takes two parameters:
    - `InvalidDataException e`: The exception object containing the error details;
    - `HttpServletRequest request`: Captures information about the HTTP request, such as the request URI.
    - The method constructs a `StandardError` object, which includes:
        - **Timestamp**: `Instant.now()` ensures the current time is recorded when the exception is handled;
        - **Status Code**: `HttpStatus.BAD_REQUEST.value()` returns the HTTP status code 400;
        - **Error Message**: The variable `error` provides a concise description for the error;
        - **Detailed Message**: `e.getMessage()` displays the custom error message from `InvalidDataException`;
        - **Request Path**: `request.getRequestURI()` specifies the URI that caused the exception.
    - The method returns a `ResponseEntity` containing the `StandardError` object and the appropriate HTTP status code.

#### 3.3. **NEW CLASS:** `services.exceptions.DuplicateImageException` (Custom Exception):

- Create a custom exception named `DuplicateImageException`, extending `RuntimeException`;
- Constructor takes an `Object image` as a parameter to provide the specific image uri address in the error message;
- Provide a detailed message when an exception occurs:
  `"The image uri address '[image]' is already associated with an existing food."`;
- This exception is thrown when a duplicate image uri address is detected during `Food` registration, ensuring that each
  image uri is unique in the system.

#### 3.4. **NEW METHOD:** `handleDuplicateImageException` on class `controller.exceptions.ResourceExceptionHandler`:

- This method is responsible for handling exceptions of type `DuplicateImageException` and converting them into
  standardized HTTP error responses:

````java
@ExceptionHandler(DuplicateImageException.class)
public ResponseEntity<StandardError> handleDuplicateImageException(DuplicateImageException e, HttpServletRequest request) {
    String error = "Image uri address already in use.";
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandardError err = new StandardError(
            Instant.now(),                   // Current timestamp of the error
            status.value(),                  // HTTP Status code (400)
            error,                           // Custom error message
            e.getMessage(),                  // Exception's detailed message
            request.getRequestURI()          // URI path where the error occurred
    );
    return ResponseEntity.status(status).body(err);
}
````

- **Annotations and Parameters:**
    - `@ExceptionHandler(DuplicateImageException.class)`: Maps the method to handle exceptions of type
      `DuplicateImageException`;
    - It takes two parameters:
        - `DuplicateImageException e`: The exception object containing the error details;
        - `HttpServletRequest request`: Captures information about the HTTP request, such as the request URI.
    - The method constructs a `StandardError` object, which includes:
        - **Timestamp**: `Instant.now()` ensures the current time is recorded when the exception is handled;
        - **Status Code**: `HttpStatus.BAD_REQUEST.value()` returns the HTTP status code 400;
        - **Error Message**: The variable `error` provides a concise description for the error;
        - **Detailed Message**: `e.getMessage()` displays the custom error message from `DuplicateImageException`;
        - **Request Path**: `request.getRequestURI()` specifies the URI that caused the exception.
    - The method returns a `ResponseEntity` containing the `StandardError` object and the appropriate HTTP status code.

***

### 4. Exception Handling for `findAll` Method:

- This section covers the implementation of exception handling for the `findAll` operation in the `FoodService` class,
  introducing custom exception and centralized error handling mechanisms.

#### 4.1. **NEW CLASS:** `services.exceptions.EmptyTableException` (Custom Exception):

- Create a custom exception named `EmptyTableException`, extending `RuntimeException`;
- Constructor provides a default error message: `"The requested table is empty or does not exist."`;
- This exception is thrown when the `findAll` method is called on an empty table or a table that does not exist,
  ensuring proper error handling for such scenarios.

#### 4.2. **NEW METHOD:** `handleEmptyTableException` on class `controller.exceptions.ResourceExceptionHandler`:

- This method is responsible for handling exceptions of type `EmptyTableException` and converting them into standardized
  HTTP error responses:

```java
@ExceptionHandler(EmptyTableException.class)
public ResponseEntity<StandardError> handleEmptyTableException(EmptyTableException e, HttpServletRequest request) {
    String error = "No data found in the requested table. Please verify the table name or check if it contains records.";
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

- **Annotations and Parameters:**
    - `@ExceptionHandler(EmptyTableException.class)`: Maps the method to handle exceptions of type
      `EmptyTableException`;
    - It takes two parameters:
        - `EmptyTableException e`: The exception object containing the error details;
        - `HttpServletRequest request`: Captures information about the HTTP request, such as the request URI;
    - The method constructs a `StandardError` object, which includes:
        - **Timestamp**: `Instant.now()` ensures the current time is recorded when the exception is handled;
        - **Status Code**: `HttpStatus.NOT_FOUND.value()` returns the HTTP status code 404;
        - **Error Message**: The variable `error` provides a concise description for the error;
        - **Detailed Message**: `e.getMessage()` displays the custom error message from `EmptyTableException`;
        - **Request Path**: `request.getRequestURI()` specifies the URI that caused the exception.
    - The method returns a `ResponseEntity` containing the `StandardError` object and the appropriate HTTP status code.

***

### 5. Exception Handling for `delete` Method:

- This section introduces the implementation of custom exception handling for the `delete` method in the `FoodService`
  class, focusing on scenarios where database-related errors occur during `Food` deletion.

#### 5.1. **NEW CLASS:** `services.exceptions.DatabaseException` (Custom Exception):

- Create a custom exception named `DatabaseException`, extending `RuntimeException`;
- Constructor takes a `String message` as a parameter to provide detailed information about the database error;
- Provide a detailed message when an exception occurs:
  `"Database error detected: [message]. Please verify database constraints and configurations."`;
- This exception is thrown when a database integrity violation (e.g., foreign key constraint) is detected during the
  `delete` operation.

#### 5.2. **NEW METHOD:** `handleDatabaseException` on class `controller.exceptions.ResourceExceptionHandler`:

- This method is responsible for handling exceptions of type `DatabaseException` and converting them into standardized
  HTTP error responses:

````java
@ExceptionHandler(DatabaseException.class)
public ResponseEntity<StandardError> handleDatabaseException(DatabaseException e, HttpServletRequest request) {
    String error = "A database error occurred. Please check the provided data and try again.";
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandardError err = new StandardError(
            Instant.now(),                   // Current timestamp of the error
            status.value(),                  // HTTP Status code (400)
            error,                           // Custom error message
            e.getMessage(),                  // Exception's detailed message
            request.getRequestURI()          // URI path where the error occurred
    );
    return ResponseEntity.status(status).body(err);
}
````

- **Annotations and Parameters:**
    - `@ExceptionHandler(DatabaseException.class)`: Maps the method to handle exceptions of type `DatabaseException`;
    - It takes two parameters:
        - `DatabaseException e`: The exception object containing the error details;
        - `HttpServletRequest request`: Captures information about the HTTP request, such as the request URI;
    - The method constructs a `StandardError` object, which includes:
        - **Timestamp**: `Instant.now()` ensures the current time is recorded when the exception is handled;
        - **Status Code**: `HttpStatus.BAD_REQUEST.value()` returns the HTTP status code 400;
        - **Error Message**: The variable `error` provides a concise description for the error;
        - **Detailed Message**: `e.getMessage()` displays the custom error message from `DatabaseException`;
        - **Request Path**: `request.getRequestURI()` specifies the URI that caused the exception.
    - The method returns a `ResponseEntity` containing the `StandardError` object and the appropriate HTTP status code.

***

### 6. Exception Handling for `update` Method:

- This section introduces the implementation of custom exception handling for the `update` method in the `FoodService`
  class, focusing on scenarios where invalid data, duplicate images uri, or missing resources are detected during user
  updates.

#### 6.1. **Exception Scenarios:**

**ResourceNotFoundException:**

- **Triggered When:** The provided `id` does not match any existing `Food` in the database;
- **Action:** Throws a `ResourceNotFoundException` with a detailed message: `"Resource Not Found! ID: [id]"`.
  **DuplicateImageException:**
- **Triggered When:** The provided `image` already exists in the database for another `Food`;
- **Action:** Throws a `DuplicateImageException` with a detailed message:
  `"The image uri address '[image]' is already registered in the system."`.

- **InvalidDataException:**
    - **Triggered When:** The provided `title` or `price` or `image` fields are null or empty;
    - **Action:** Throws an `InvalidDataException` with a detailed message:
      `"Mandatory fields must not be null or empty."`.

#### 6.2. **Exception Handling Flow:**

- The `update` method first checks if the `Food` exists using `foodRepository.findById`. If not found, it throws a
  `ResourceNotFoundException`;
- If the `image` field is provided, it checks for duplicates using `foodRepository.findByImage`. If a duplicate is
  found, it throws a `DuplicateImageException`;
- The `updateData` method validates the `title`, `price`, and `image` fields. If they are null or empty, it throws an
  `InvalidDataException`;
- If all validations pass, the `Food` data is updated and saved to the database.

***

## Part 3 - Test Cases:

### 1. Successful Food Data Operations:

- This section details the successful interactions with the Food entity via the Spring Boot RESTful API. It covers the
  expected behaviour and responses for various HTTP methods, demonstrating correct data retrieval, creation, updating,
  and deletion;
- Idempotent and Non-Idempotent Methods:
    - **Idempotent Method**: `GET`, `PUT`, and `DELETE` are idempotent, meaning multiple identical requests should have
      the
      same effect as a single request;
    - **Non-Idempotent Method**: `POST` is non-idempotent, meaning multiple identical requests may have additional
      effects.

#### 1.1. `findAll` - Setting Up the RESTful API for HTTP Methods (`Idempotent`):

- **Endpoint:** `GET /foods`;
- **Purpose:** Retrieves a list of all Foods.

#### 1.2. Example GET Request:

- **Scenario:** Successfully retrieves a list of all Foods:

````markdown
GET http://localhost:8080/foods
````

#### 1.3. Example Response:

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
  }
]
````

#### 1.4. `findById` - Setting Up the RESTful API for HTTP Methods (`Idempotent`):

- **Endpoint:** `GET /foods/{id}`;
- **Purpose:** Retrieves a specific Food item by its ID.

#### 1.5. Example GET Request:

- **Scenario:** Successfully retrieves the requested Food by ID:

```markdown
GET http://localhost:8080/foods/10
```

#### 1.6. Example Response:

```json
{
  "id": 10,
  "title": "Pizza de Calabresa",
  "price": 49.99,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/pizza.jpg"
}
```

#### 1.7. `insert` - Setting Up the RESTful API for HTTP Methods (`Non-Idempotent`):

- **Endpoint:** `POST /foods`;
- **Purpose:** Creates a new Food.

#### 1.8. Example POST Request:

- **Scenario:** Successfully creates a new Food:

- **Request Data:**

```markdown
POST http://localhost:8080/foods
Body -> raw -> JSON
```

```json
{
  "title": "Tapioca de Carne Seca",
  "price": 21.99,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/tapioca.webp"
}
```

#### 1.9. Example Response:

```json
{
  "id": 16,
  "title": "Tapioca de Carne Seca",
  "price": 21.99,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/tapioca.webp"
}
```

#### 1.10. `update` - Setting Up the RESTful API for HTTP Methods (`Idempotent`):

- **Endpoint:** `PUT /foods/{id}`;
- **Purpose:** Updates a specific Food item by its ID.

#### 1.11. Example PUT Request:

- **Scenario:** Successfully updates the requested Food by ID:
- **Data for Update:**

````json
{
  "id": 16,
  "title": "Tapioca de Carne Seca",
  "price": 12.99,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/tapioca.webp"
}
````

- **Request Data:**

```markdown
PUT http://localhost:8080/foods/16
Body -> raw -> JSON
```

```json
{
  "title": "Temaki de Salmão",
  "price": 34.99,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/temaki.webp"
}
```

#### 1.12. Example Response:

```json
{
  "id": 16,
  "title": "Temaki de Salmão",
  "price": 20.99,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/temaki.jpg"
}
```

#### 1.13. `delete` - Setting Up the RESTful API for HTTP Methods (`Idempotent`):

- **Endpoint:** `DELETE /foods/{id}`;
- **Purpose:** Deletes a specific Food item by its ID.

#### 5.14. Example DELETE Request:

- **Scenario:** Successfully deletes the requested Food by ID:

```markdown
DELETE http://localhost:8080/foods/1
```

#### 5.15. Example Response:

```markdown
HTTP Status 204 - No Content
```

***

### 2. Handling Invalid Food Data Requests:

- This section outlines the error scenarios encountered when interacting with the Food entity through the Spring Boot
  RESTful API. It specifically addresses cases where requests contain invalid or malformed data, and details the
  expected error responses and HTTP status codes returned by the API. This ensures robust error handling and clear
  communication of issues to the client.

#### 2.1. `findAll` - Setting Up the RESTful API for HTTP Methods (`Idempotent`):

- **Endpoint** `GET /foods`;
- **Purpose**: Retrieves all Food items from the database.

#### 2.2. Example GET Request (`Empty Table`):

- **Scenario**: The database table is empty, triggering the custom error response with a `404 Not Found` status code:

````markdown
GET http://localhost:8080/foods
````

#### 2.3. Example Error Response:

- Upon catching a `DatabaseException`, the method returns a structured JSON response in the following format:

````json
{
  "timestamp": "2025-02-08T21:09:02Z",
  "status": 404,
  "error": "No data found in the requested table. Please verify the table name or check if it contains records.",
  "message": "The requested table is empty or does not exist.",
  "path": "/foods"
}
````

#### 2.4. `findById` - Setting Up the RESTful API for HTTP Methods (`Idempotent`):

- **Endpoint**: `GET /foods/{id}`;
- **Purpose**: Retrieves a specific Food item by its ID.

#### 2.5. Example GET Request (`Resource Not Found`):

- **Scenario**: The requested ID does not exist, triggering the custom error response with a `404 Not Found` status
  code:

````markdown
GET http://localhost:8080/foods/99
````

#### 2.6. Example Error Response:

- Upon catching a `ResourceNotFoundException`, the method returns a structured JSON response in the following format:

````json
{
  "timestamp": "2025-02-31T18:27:56Z",
  "status": 404,
  "error": "Resource not found with the specified identifier or criteria.",
  "message": "Resource Not Found! ID: 99",
  "path": "/foods/99"
}
````

#### 2.7. `insert` - Setting Up the RESTful API for HTTP Methods (`Non-Idempotent`):

- **Endpoint**: `POST /foods`;
- **Purpose**: Inserts a new Food item into the database.

#### 2.8. Example POST Request (`Null or Empty Data`):

- **Scenario**: `Food` registration fails with an exception if required fields like `title` or `price` or `image` are
  missing (
  `null` or `empty`).
- **Request Data:**

````markdown
POST http://localhost:8080/foods
Body -> raw -> JSON
````

````json
{
  "title": "",
  "price": null,
  "image": ""
}
````

#### 2.9. Example Error Response:

- **Error Handling**: Upon catching a `InvalidDataException`, the method returns a structured JSON response in the
  following format:

````json
{
  "timestamp": "2025-02-09T01:57:13Z",
  "status": 400,
  "error": "Invalid data provided. Please check the input and try again.",
  "message": "Mandatory fields must not be null or empty.",
  "path": "/foods"
}
````

#### 2.10. Example POST Request (`Duplicate Image URI Address`):

- **Scenario**: `Food` registration fails with an exception if a `duplicate image uri` address is provided, enforcing
  image uniqueness.

- **Request Data:**

````markdown
POST http://localhost:8080/foods
Body -> raw -> JSON
````

````json
{
  "title": "Torta de Frango",
  "price": 29.00,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/beef-coxinha.webp"
}
````

#### 2.11. Example Error Response:

- **Error Handling**: Upon catching a `DuplicateImageException`, the method returns a structured JSON response in the
  following format:

````json
{
  "timestamp": "2025-02-09T02:00:53Z",
  "status": 400,
  "error": "Image URI address already in use.",
  "message": "The image uri address 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/beef-coxinha.webp' is already associated with an existing food.",
  "path": "/foods"
}
````

#### 2.12. `delete` - Setting Up the RESTful API for HTTP Methods (`Idempotent`):

- **Endpoint**: `DELETE /foods/{id}`;
- **Purpose**: Deletes a specific Food item by its ID.

#### 2.13. Example DELETE Request (`Resource Not Found`):

- **Scenario**: The requested ID does not exist, triggering the custom error response with a `404 Not Found` status
  code:

````markdown
DELETE http://localhost:8080/foods/99
````

#### 2.14. Example Error Response:

- Upon catching a `ResourceNotFoundException`, the method returns a structured JSON response in the following format:

````json
{
  "timestamp": "2025-02-09T02:07:01Z",
  "status": 404,
  "error": "Resource not found with the specified identifier or criteria.",
  "message": "Resource Not Found! ID: 99",
  "path": "/foods/99"
}
````

#### 2.15. Example DELETE Request (`Data Integrity Violation`):

- **Scenario**: The requested ID exists, but due to relationships with another entity, a
  `Database Constraint Violation` occurs, triggering the custom error response with a `400 Bad Request` status code:

````markdown
DELETE http://localhost:8080/foods/1
````

#### 2.16. Example Error Response:

- **Error Handling** - Upon catching a `DatabaseException`, the method returns a structured JSON response in the
  following format:

````json
{
  "timestamp": "2025-02-09T02:08:45Z",
  "status": 400,
  "error": "A database error occurred. Please check the provided data and try again.",
  "message": "Could not delete food due to data integrity violation.",
  "path": "/foods/1"
}
````

#### 2.17. `update` - Setting Up the RESTful API for HTTP Methods (`Idempotent`):

- **Endpoint**: PUT `/foods/{id}`;
- **Purpose**: Updates a specific Food item by its ID.

#### 2.18 Example PUT Request (`Resource Not Found`):

- **Scenario**: The requested ID does not exist, triggering the custom error response with a `404 Not Found` status
  code.

- **Data for Update:**

````json
{
  "id": 16,
  "title": "Tapioca de Carne Seca",
  "price": 12.99,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/tapioca.webp"
}
````

````markdown
PUT http://localhost:8080/foods/99
Body -> raw -> JSON
````

#### 2.19. Example Error Response:

- Upon catching a `ResourceNotFoundException`, the method returns a structured JSON response in the following format:

````json
{
  "timestamp": "2025-02-09T02:19:27Z",
  "status": 404,
  "error": "Resource not found with the specified identifier or criteria.",
  "message": "Resource Not Found! ID: 99",
  "path": "/foods/99"
}
````

#### 2.20. Example PUT Request (`Duplicate Image URI Address`):

- **Scenario**: `Food` registration fails with an exception if a `duplicate image uri` address is provided, enforcing
  image uniqueness.

- **Data for Update:**

````json
{
  "id": 3,
  "title": "Esfirra de Frango",
  "price": 23.99,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/chicken-esfiha.webp"
}
````

- **Request Data:**

````markdown
PUT http://localhost:8080/foods/3
Body -> raw -> JSON
````

````json
{
  "title": "Empada de Frango",
  "price": 19.00,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/beef-coxinha.webp"
}
````

#### 2.21. Example Error Response:

- **Error Handling**: Upon catching a `DuplicateImageException`, the method returns a structured JSON response in the
  following format:

````json
{
  "timestamp": "2025-02-09T02:29:44Z",
  "status": 400,
  "error": "Image URI address already in use.",
  "message": "The image uri address 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/strawberry-ice-cream.webp' is already associated with an existing food.",
  "path": "/foods/3"
}
````

#### 2.22. Example PUT Request (`Null or Empty Data`):

- **Scenario**: `Food` updates fails with an exception if required fields like `title` or `price` or `image` are
  missing (
  `null` or `empty`).
- **Data for Update:**

````json
{
  "id": 3,
  "title": "Esfirra de Frango",
  "price": 23.99,
  "image": "https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/chicken-esfiha.webp"
}
````

- **Request Data:**

````markdown
PUT http://localhost:8080/foods/3
Body -> raw -> JSON
````

````json
{
  "title": "",
  "price": null,
  "image": ""
}
````

#### 2.23. Example Error Response:

- **Error Handling** - Upon catching a `InvalidDataException`, the method returns a structured JSON response in the
  following format:

````json
{
  "timestamp": "2025-02-09T02:33:56Z",
  "status": 400,
  "error": "Invalid data provided. Please check the input and try again.",
  "message": "Mandatory fields must not be null or empty.",
  "path": "/foods/3"
}
````

***

## Project Checklist:

## Part 1 - Project Requirements and Configurations:

### **Backend Requirements and Configurations:**

- [X] Configure Spring Initializr with Maven, Java 21, JAR packaging, and required dependencies (Spring Web, Spring Data
  JPA, PostgreSQL Driver, Spring Boot DevTools, dotenv-java, Liquibase Core);
- [X] Set up `application.properties` with general configurations, including active profile and application name;
- [X] Configure `application-test.properties` for H2 in-memory database with enabled H2 console, SQL logging, and
  Liquibase for database migrations;
- [X] Configure `application-dev.properties` for PostgreSQL with environment variables for database connection and
  Liquibase for database migrations;
- [X] Create `.env` file for PostgreSQL credentials and add it to `.gitignore`;
- [X] Add `dotenv-java` dependency to `pom.xml` and implement `LoadEnvironment` utility class to load environment
  variables;
- [X] Call `LoadEnvironment.loadEnv()` in the main application class to ensure environment variables are loaded at
  runtime;
- [X] Create `db.changelog-master.yaml` in `src/main/resources/db/changelog` for database migrations using Liquibase;
- [X] Define initial database schema changes (e.g., creating tables) within the changelog file.

### **Database Seeding:**

- [X] Create SQL script to drop and create `tb_foods` table;
- [X] Insert sample data into `tb_foods` for testing.

---

## Part 2 - Development Requirements and Specifications:

### **Entity, DTOs Pattern, Repository, Service, and Controller Classes:**

- [X] Create `Food` entity class with `@Entity`, `@Table`, and necessary attributes (id, title, price, image);
- [X] Implement constructors, getters, setters, `equals`, `hashCode`, and `Serializable` in `Food` entity;
- [X] Create `FoodResponseDTO` record for response data, including a constructor to map `Food` entity;
- [X] Create `FoodRequestDTO` record for request payload with attributes (title, price, image);
- [X] Create `FoodRepository` interface extending `JpaRepository<Food, Long>`;
- [X] Implement `FoodService` class with `@Service` and methods for CRUD operations (`findAll`, `findById`, `insert`,
  `update`, `delete`);
- [X] Annotate service methods with `@Transactional` or `@Transactional(readOnly = true)` as needed;
- [X] Create `FoodController` class with `@RestController` and `@RequestMapping("/foods")`;
- [X] Implement methods for handling HTTP requests (`GET`, `POST`, `PUT`, `DELETE`) with appropriate annotations (
  `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`);
- [X] Use `@CrossOrigin(origins = "*", allowedHeaders = "*")` to enable CORS;
- [X] Return `ResponseEntity` with appropriate HTTP status codes (200, 201, 204, 400, 404).

### **Exception Handling:**

- [X] Create custom exceptions: `ResourceNotFoundException`, `DatabaseException`, `DatabaseException`,
  EmptyTableException`, and
  `InvalidDataException`;
- [X] Implement `StandardError` class for standardized error responses;
- [X] Create `ResourceExceptionHandler` class with `@ControllerAdvice` to handle exceptions globally;
- [X] Implement methods in `ResourceExceptionHandler` to handle specific exceptions (`handleResourceNotFoundException`,
  `handleDatabaseException`, `handleEmptyTableException`, `handleBadRequestException`, `handleDuplicateImageException`).

---

## Part 3 - Test Cases:

### **Validation of Success and Error Scenarios:**

- [X] **Validate the `GET /foods` endpoint** to retrieve all available food items, ensuring successful retrieval and
  handling of an empty database scenario (`404 Not Found`);
- [X] **Validate the `POST /foods` endpoint** to insert a new food item, covering successful insertion and failure
  cases, including invalid or missing data (`400 Bad Request`) and duplicate image URI (`400 Bad Request`);
- [X] **Validate the `GET /foods/{id}` endpoint** to retrieve a specific food item, ensuring a correct response for
  valid IDs and handling cases where the requested resource is not found (`404 Not Found`);
- [X] **Validate the `PUT /foods/{id}` endpoint** to update an existing food item, verifying successful updates and
  handling of errors such as resource not found (`404 Not Found`), invalid or missing data (`400 Bad Request`), and
  duplicate image URI (`400 Bad Request`);
- [X] **Validate the `DELETE /foods/{id}` endpoint** to remove a food item, ensuring successful deletion and handling of
  errors such as resource not found (`404 Not Found`) and data integrity violation (`400 Bad Request`).