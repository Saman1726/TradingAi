# TradingAI

## Overview
TradingAI is a Spring Boot application designed to provide a platform for trading-related functionalities. It utilizes OAuth2 for secure authentication and authorization, and it is backed by a PostgreSQL database.

## Features
- OAuth2 authentication and authorization
- RESTful API for trading operations
- User management with registration and authentication
- PostgreSQL database for data persistence

## Technologies Used
- Spring Boot
- OAuth2
- PostgreSQL
- Maven
- Java

## Project Structure
```
TradingAI
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── tradingai
│   │   │           ├── TradingAiApplication.java
│   │   │           ├── config
│   │   │           │   └── SecurityConfig.java
│   │   │           ├── controller
│   │   │           │   └── ApiController.java
│   │   │           ├── model
│   │   │           │   └── User.java
│   │   │           ├── repository
│   │   │           │   └── UserRepository.java
│   │   │           └── service
│   │   │               └── UserService.java
│   │   ├── resources
│   │   │   ├── application.properties
│   │   │   └── schema.sql
│   └── test
│       └── java
│           └── com
│               └── tradingai
│                   └── TradingAiApplicationTests.java
├── pom.xml
└── README.md
```

## Setup Instructions
1. Clone the repository:
   ```
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```
   cd TradingAI
   ```
3. Update the `application.properties` file with your PostgreSQL database credentials.
4. Run the application:
   ```
   mvn spring-boot:run
   ```

## Usage
- Access the API endpoints to interact with the trading functionalities.
- Use OAuth2 for secure access to the application.

## License
This project is licensed under the MIT License.