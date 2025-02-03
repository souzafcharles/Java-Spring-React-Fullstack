![GitHub language count](https://img.shields.io/github/languages/count/souzafcharles/java-spring-react-fullstack)
![GitHub top language](https://img.shields.io/github/languages/top/souzafcharles/java-spring-react-fullstack)
![GitHub](https://img.shields.io/github/license/souzafcharles/java-spring-react-fullstack)
![GitHub last commit](https://img.shields.io/github/last-commit/souzafcharles/java-spring-react-fullstack)

# 💻 Building a Full-Stack Application with Java Spring and React
:triangular_flag_on_post: Project developed based on tutorials by **Fernanda Kipper** - [kipperDev](https://www.youtube.com/@kipperdev).
***
## Instructions:
<p align="justify">
This guide provides a structured approach to developing a full-stack application using <code>Java Spring</code>, <code>React</code>, and <code>PostgreSQL</code>. The process is divided into two key phases: backend development and frontend development.
</p>

### Part 1 - [Backend Development with Java Spring and PostgreSQL](https://github.com/souzafcharles/Java-Spring-React-Fullstack/tree/main/Backend/digitalMenu):
<p align="justify">
This section details the construction of the application's backend using the <code>Java Spring</code> framework. The initial step involves creating a <code>Spring Boot</code> project and configuring the <code>PostgreSQL</code> database. Subsequently, the guide covers the creation of data models and <code>RESTful</code> controllers to manage <code>CRUD</code> operations within the application. The utilisation of essential libraries, such as <code>Spring Data JPA</code>, will be explored to streamline data access and manipulation.
</p>

## Backend Project Stack:
| Technology        | Version   | Description                                 |
|-------------------|-----------|---------------------------------------------|
| ✒️ IntelliJ IDEA  | `2024.3`  | Integrated Development Environment (IDE)    |
| ☕ Java           | `21`      | Backend programming language                |
| 🍃 Spring Boot    | `v3.4.2`  | Framework for creating Spring applications  |
| 🐦 Maven          | `v3.9.9`  | Build automation and dependency management  |
| 🐘 PostgreSQL     | `17`      | Relational database management system       |
| 👩‍🚀 Postman        | `11.19`   | API testing and development tool            |

***

## Backend Dependencies:  
| Dependency              | Category        | Description                                                                                                                                  |
|-------------------------|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------|
| 🛠️ Spring Boot DevTools | Developer Tools | Provides fast application restarts, LiveReload, and configurations for enhanced development experience                                       |
| 🌐 Spring Web           | Web             | Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container                            |
| 💾 Spring Data JPA      | SQL             | Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate                                                         |
| 🐘 PostgreSQL Driver    | SQL             | A JDBC and R2DBC driver that allows Java programs to connect to a PostgreSQL database using standard, database-independent Java code         |
| 🗝️ dotenv-java          | Configuration   | Loads environment variables from a `.env` file into the application, aiding in secure configuration management                               |
| 🦅 Flyway Migration     | SQL             | Provides version control for your database, allowing seamless migrations from any version, including an empty database, to the latest schema |
| 🛢️ H2 Database Engine   | SQL             | Provides an in-memory and lightweight relational database for development and testing purposes                                               |

***

### Part 2 - [Frontend Development with React and TypeScript](https://github.com/souzafcharles/Java-Spring-React-Fullstack/tree/main/Frontend/digitalMenu):
<p align="justify">
This section focuses on developing the application's frontend using the <code>React</code> library and <code>TypeScript</code>. The process includes creating <code>React</code> components and leveraging <code>React Hooks</code> to implement the application's functionalities. The guide also explores the use of <code>TypeScript</code> to enhance code maintainability. Furthermore, it details the connection of the frontend to the backend (developed in Part 1) using <code>Axios</code> for <code>HTTP</code> requests and <code>React Query</code> for fetching, caching, synchronising, and updating server state within the application.
</p>

## Frontend Project Stack:  
| Technology            | Version    | Description                                     |
|-----------------------|------------|-------------------------------------------------|
| ✒️Visual Studio Code  | `1.96`     | Code editor for modern web and cloud projects   |
| ⚡ Vite               | `6.0.11`   | Next Generation Frontend Tooling                |
| ⚛️ React              | `18.3.1`   | Framework for building user interfaces          |
| 📜 TypeScript         | `5.6.2`    | Strongly typed programming language             |
| 📦 Node.js            | `18.x`     | JavaScript runtime environment                  |

***  

## Frontend Dependencies:  
| Dependency               | Category         | Description                                              |
|--------------------------|------------------|----------------------------------------------------------|
| ⚛️ React                 | Core             | Library for building user interfaces                     |
| ⚛️ React DOM             | Core             | Renders React components in the browser                  |
| 📜 TypeScript            | Core             | Enables type-checking and development with TypeScript    |
| ⚙️ Vite Plugin React     | Build Tools      | Plugin to support React in Vite projects                 |
| ✅ ESLint                | Development      | Linter tool to ensure code quality                       |
| 🎯 TypeScript ESLint     | Development      | Integrates TypeScript with ESLint                        |
| 🔍 @types/react          | Type Definitions | Provides TypeScript definitions for React                |
| 🔍 @types/react-dom      | Type Definitions | Provides TypeScript definitions for React DOM            |
| 🌍 Globals               | Development      | Provides global variables and functions for ESLint rules |
| 📐 React Hooks ESLint    | Development      | Ensures proper usage of React Hooks                      |
| 🔧 React Refresh ESLint  | Development      | Provides linting for Vite’s fast-refresh mechanism       |
| 🔄 @tanstack/react-query | API Management   | Data fetching and caching for React applications         |
| 🔗 Axios                 | API Management   | Promise-based HTTP client for making API requests        |

***

▶️ **Tutorial Video kipperDev**: 
- [Part 1](https://www.youtube.com/watch?v=lUVureR5GqI&ab_channel=FernandaKipper%7CDev): Backend Development with Java Spring and PostgreSQL;
- [Part 2](https://www.youtube.com/watch?v=WHruc3_2z68&ab_channel=FernandaKipper%7CDev): Frontend Development with React and TypeScript.

***
## Home Screen with Displayed Items: 
![Home Screen Updated](https://github.com/souzafcharles/Java-Spring-React-Fullstack/blob/main/Frontend/digitalMenu/public/home-inserted.png)

