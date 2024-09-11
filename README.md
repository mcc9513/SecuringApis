# Securing APIs with JWT Authentication

## Overview
This project demonstrates securing RESTful APIs using **JWT (JSON Web Token)** for authentication and authorization in a Spring Boot application. It allows users to register, login, and access protected resources based on their roles (e.g., `USER`, `ADMIN`).

## Key Features
- **JWT Authentication**: Stateless authentication using JWT tokens, eliminating the need for server-side sessions.
- **Role-based Authorization**: Protects API endpoints based on user roles.
- **Custom Authentication Entry Points**: Handles unauthorized access with a custom error response.
- **User Registration and Login**: Allows new users to register and login to obtain JWT tokens for accessing protected APIs.

## Project Structure

- **Security Configuration**:
  - `SecurityConfig.java`: Configures Spring Security for JWT-based authentication, custom entry points, and stateless sessions.
  
- **Authentication & Authorization**:
  - `AuthController.java`: Exposes endpoints for user login (`/login`) and registration (`/register`).
  - `JwtAuthFilter.java`: A filter that checks for JWT tokens in HTTP requests.
  - `UserAuthEntryPoint.java`: Handles unauthorized requests by returning `401 Unauthorized` responses.
  
- **User Management**:
  - `User.java`: Represents the `User` entity with fields such as `username`, `password`, and `roles`.
  - `UserService.java`: Manages user registration, role assignment, and fetching user details.
  - `UserRepository.java`: Interacts with the database to fetch and store user information.
  
- **DTOs**:
  - `CredentialsDto.java`: Captures login details (username, password).
  - `RegistrationDto.java`: Captures registration details (username, password, roles).

- **Roles Management**:
  - `Roles.java`: Manages different user roles (e.g., `ROLE_USER`, `ROLE_ADMIN`).

## Dependencies

The following are the primary dependencies used in the project:
- **Spring Boot Starter Security**: For securing the application.
- **Spring Boot Starter Web**: For building REST APIs.
- **Auth0 Java JWT**: For generating and validating JWT tokens.
- **Spring Data JPA**: For database interaction and user persistence.
- **H2 Database** (or any other DB): For managing user data (can be configured in the `application.properties`).

## How to Run

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd SecuringApis
   ```

2. **Configure Database**:
   Update `application.properties` with the desired database configuration. Example configuration for an H2 database:

   ```properties
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=password
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   ```

3. **Build and Run**:
   Use Maven or Gradle to build the project:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the API**:
   The API can be accessed at `http://localhost:8080/`. You can register new users and authenticate them to receive a JWT token.

## API Endpoints

- **POST /register**: Register a new user.
- **POST /login**: Authenticate a user and receive a JWT token.
- **GET /user/details**: Retrieve authenticated user details (requires a valid JWT token).

### Sample Request for Registration:
```json
POST /register
{
  "username": "john_doe",
  "password": "secure_password",
  "roles": ["ROLE_USER"]
}
```

### Sample Request for Login:
```json
POST /login
{
  "username": "john_doe",
  "password": "secure_password"
}
```

### Authorization:
Include the JWT token in the `Authorization` header for accessing secured endpoints:
```
Authorization: Bearer <token>
```

## JWT Token Structure
The JWT tokens are generated and verified using the **Auth0 Java JWT** library. The token includes:
- **Subject (sub)**: The username.
- **Issued At (iat)**: Token creation timestamp.
- **Expiration (exp)**: Token expiration timestamp.
- **Roles**: The roles assigned to the user.

---

This README provides a concise guide to the structure, features, and usage of the project. Let me know if you need any modifications or further details!
