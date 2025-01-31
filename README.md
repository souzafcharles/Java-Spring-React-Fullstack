![GitHub language count](https://img.shields.io/github/languages/count/souzafcharles/java-spring-react-fullstack)
![GitHub top language](https://img.shields.io/github/languages/top/souzafcharles/java-spring-react-fullstack)
![GitHub](https://img.shields.io/github/license/souzafcharles/java-spring-react-fullstack)
![GitHub last commit](https://img.shields.io/github/last-commit/souzafcharles/java-spring-react-fullstack)

# 💻 Building a Full-Stack Application with Java Spring and React
:triangular_flag_on_post: Project developed based on tutorials by **Fernanda Kipper** - [kipperdev](https://www.youtube.com/@kipperdev).
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

### Part 1: Backend Development with Java Spring:
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
### Food Domain Model Entity:
![Food Domain Model Entity](https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/food-entity.png)
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
- Create the `Food` Entity Class;
- Annotate the class with `@Entity` and `@Table`(name = "tb_food") to map it to the database table;
- Basic Attributes;
- Annotate `@Id` and `@GeneratedValue` for the primary key, and `@Column(name = "imguri")` for `imgUri` attribute;
- Constructors;
- Getters & Setters;
- hashCode & equals;
- Serializable.
#### 7.2 Requirements for FoodResponseDTO Record Class:
- Create the `FoodResponseDTO` Record Class;
- Define the attributes: `id`, `title`, `price`, `imgUri` directly in the record declaration. This is done by specifying them as parameters in the record's header;
- Implement a constructor that accepts a `Food` entity object to initialize its attributes;
- Ensure the constructor extracts values from the `Food` object and sets the values for `id`, `title`, `price`, and `imgUri`.
#### 7.3 Requirements for FoodRequestDTO Record Class:
- Create the `FoodRequestDTO` Record Class;
- Define the attributes: `String title`, `Double price`, `String imgUri` directly in the record declaration. This is done by specifying them as parameters in the record's header.
#### 7.4 Requirements for FoodRepository Class:
- Create an Interface that extends `JpaRepository` for the `Food` entity (`extends JpaRepository<Food, Long>`).
#### 7.5 Requirements for FoodController Class:
- Create the `FoodController` Class;
- Use `@RestController` annotation;
- Map requests (`@RequestMapping`) to the `/foods` endpoint;
- Inject `FoodRepository` using `@Autowired`;
- Implement a method to handle `GET` requests and return all foods (`@GetMapping`);
- Implement a method to handle `POST` requests to insert a new `Food` (`@PostMapping`);
- Use `@CrossOrigin` annotation to allow cross-origin requests from any origin;
- Implement serialization by making the class implement `Serializable`.
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