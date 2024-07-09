# Senegal Service Hub Backend

## Overview
Senegal Service Hub Backend is a Java Spring Boot application that serves as the backend for the Senegal Service Hub platform. It provides APIs for user management, authentication, and other backend functionalities.

## Prerequisites
- Java Development Kit (JDK) 11 or higher
- Apache Maven
- MySQL Server

## Getting Started
Follow these steps to set up and run the project locally:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/senegal-service-hub.git
   cd senegal-service-hub-backend
   ```

2. **Set up MySQL database:**
   - Create a MySQL database named `senegal_service_hub`.
   - Update `application.properties` file in `src/main/resources` with your database username and password.

3. **Build and run the application:**
   ```bash
   mvn clean install
   java -jar target/backend-0.0.1-SNAPSHOT.jar
   ```

4. **Access the application:**
   - The backend server will start at `http://localhost:8080`.
   - Use Postman or any REST client to interact with the APIs.

## Features
- User authentication and authorization with JWT
- CRUD operations for users and roles
- Integration with MySQL database
- Swagger UI for API documentation (accessible at `http://localhost:8080/swagger-ui.html`)

## API Endpoints
- **POST /api/auth/signup:** Register a new user.
- **POST /api/auth/signin:** Authenticate and generate a JWT token.
- **GET /api/users:** Retrieve all users with admin or super_admin role.
- **GET /api/users/me:** Retrieve a single user user.
- **PUT /api/users/{id}:** Update user information.
- **DELETE /api/users/{id}:** Delete user.

## Technologies Used
- Java Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- JSON Web Token (JWT)
- Lombok

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your improvements.

## License
This project is licensed under the MIT License - see the LICENSE file for details.
