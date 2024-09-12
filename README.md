# Securing APIs with Spring Boot

This project demonstrates how to secure REST APIs using Spring Boot, JWT authentication, and role-based access control (RBAC). It includes user login, registration, and caching using Redis.

## Prerequisites

- Java 17
- Maven
- Redis (running locally on port `6379`)

## Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/securing-apis.git
   cd securing-apis
   ```

2. **Generate SSL certificate** (for HTTPS):
   ```bash
   keytool -genkeypair -alias securingapis -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650
   ```

3. **Configure `application.properties`** (set Redis connection details and SSL settings):
   ```properties
   spring.data.redis.host=localhost
   spring.data.redis.port=6379
   spring.data.redis.password=
   server.ssl.key-store=classpath:keystore.p12
   server.ssl.key-store-password=yourpassword
   ```

4. **Build the project**:
   ```bash
   mvn clean install
   ```

5. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

The server will be available at `https://localhost:8443`.

## Testing the APIs

### Authentication and User APIs

1. **Register a user**:
   - **Endpoint**: `/register`
   - **Method**: `POST`
   - **Payload**:
     ```json
     {
       "username": "newuser",
       "password": "password",
       "roles": ["USER"]
     }
     ```

2. **Login**:
   - **Endpoint**: `/login`
   - **Method**: `POST`
   - **Payload**:
     ```json
     {
       "username": "newuser",
       "password": "password"
     }
     ```
   - This will return a JWT token that you can use to authenticate further requests.

3. **Access protected APIs**:
   - **Endpoint**: `/details`
   - **Method**: `GET`
   - **Authorization**: Add the JWT token from login in the `Authorization` header:
     ```bash
     Authorization: Bearer <your-jwt-token>
     ```

### Running Tests

To run the unit and integration tests:

```bash
mvn test
```

## Key Features

- **JWT Authentication**: Secure APIs with JSON Web Tokens.
- **HTTPS Support**: Communication over HTTPS using a self-signed certificate.
- **CSRF Protection**: Enabled for forms and AJAX requests.
- **Redis Caching**: Cache user login and registration data using Redis.
- **Role-Based Access Control**: Restrict access to certain endpoints based on user roles (`USER`, `ADMIN`).

