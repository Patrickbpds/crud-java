# Spring Boot CRUD API

A robust REST API built with Spring Boot that provides complete user management functionality with JWT authentication, built by Patrick Batista.

<div align="left" style="position: relative;">
<p align="left">
	<img src="https://img.shields.io/badge/Docker-2496ED.svg?style=flat&logo=Docker&logoColor=white" alt="Docker">
	<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=openjdk&logoColor=white" alt="java">
</p>
</div>

## 🚀 Features

- **User Management**: Complete CRUD operations for user entities
- **JWT Authentication**: Secure token-based authentication system
- **Password Encryption**: BCrypt password hashing for security
- **Database Integration**: PostgreSQL with Flyway migrations
- **API Documentation**: Interactive Swagger/OpenAPI documentation
- **Docker Support**: Containerized application with Docker Compose
- **Input Validation**: Comprehensive request validation
- **Exception Handling**: Global exception handling with detailed error responses

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.5.0**
- **Spring Security** (JWT Authentication)
- **Spring Data JPA** (Database operations)
- **PostgreSQL** (Database)
- **Flyway** (Database migrations)
- **MapStruct** (Object mapping)
- **Swagger/OpenAPI 3** (API documentation)
- **Docker & Docker Compose** (Containerization)
- **Maven** (Build tool)

## 📋 Prerequisites

- Java 17
- Docker and Docker Compose
- Maven 3.6+

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone <https://github.com/Patrickbpds/crud-java.git>
```

### 2. Environment Setup
Create a `.env` file in the root directory with the following variables:

```env
# Database Configuration
POSTGRES_USER=your_db_user
POSTGRES_PASSWORD=your_db_password
POSTGRES_DB=crud_db

DB_URL=jdbc:postgresql://java_db:5432/crud_db
DB_USER=your_db_user
DB_PASSWORD=your_db_password

# JWT Configuration
JWT_SECRET=your-super-secret-jwt-key-here-make-it-long-and-secure
```

### 3. Build and Run
```bash
# Build the application
./mvnw clean package -DskipTests

# Run the application
docker-compose up --build -d
```

The application will be available at `http://localhost:8080`

### 4. Run with Docker Compose
```bash
docker-compose up -d
```

## 📚 API Documentation

Once the application is running, you can access the interactive API documentation at:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

## 🔐 Authentication

The API uses JWT (JSON Web Token) for authentication. Most endpoints require a valid JWT token in the Authorization header.

### Authentication Flow

1. **Register** a new user: `PUT /auth/sign-up`
2. **Login** to get JWT token: `POST /auth/sign-in`
3. **Use token** in subsequent requests: `Authorization: Bearer <your-jwt-token>`

### Public Endpoints (No Authentication Required)
- `POST /auth/sign-in` - User login
- `PUT /auth/sign-up` - User registration
- `PATCH /auth/update-authentication-data` - Update user credentials

### Protected Endpoints (Authentication Required)
- `GET /api/users` - Get all users
- `GET /api/users/{publicId}` - Get user by public ID
- `POST /api/users` - Create new user
- `PATCH /api/users/{publicId}` - Update user
- `DELETE /api/users/{email}` - Delete user

## 📊 Database Schema

The application uses PostgreSQL with the following main table:

```sql
CREATE TABLE crud_tables.crud_users (
    id SERIAL PRIMARY KEY,
    public_id VARCHAR(30) UNIQUE NOT NULL,
    name TEXT,
    email TEXT,
    password TEXT
);
```

### Database Features
- **Auto-generated Public IDs**: Each user gets a unique public ID for external references
- **Email Uniqueness**: Email addresses must be unique across users
- **Password Security**: All passwords are BCrypt hashed
- **Database Migrations**: Flyway handles schema versioning and migrations

## 📝 API Examples

### Register a New User
```bash
curl -X PUT http://localhost:8080/auth/sign-up \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Patrick Batista",
    "email": "patrick@example.com",
    "password": "SecurePass123"
  }'
```

### Login
```bash
curl -X POST http://localhost:8080/auth/sign-in \
  -H "Content-Type: application/json" \
  -d '{
    "email": "patrick@example.com",
    "password": "SecurePass123"
  }'
```

### Get All Users (with JWT)
```bash
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer <your-jwt-token>"
```

### Update User
```bash
curl -X PATCH http://localhost:8080/api/users/{publicId} \
  -H "Authorization: Bearer <your-jwt-token>" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Patrick Updated",
    "email": "patrick.updated@example.com"
  }'
```

### Delete User
```bash
curl -X DELETE http://localhost:8080/api/users/john@example.com \
  -H "Authorization: Bearer <your-jwt-token>" \
  -H "Content-Type: application/json" \
  -d '{
    "password": "SecurePass123",
    "confirmation": "DELETE"
  }'
```

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/com/patrick/crud/
│   │   ├── config/          # Configuration classes
│   │   ├── controller/      # REST controllers
│   │   ├── entity/          # JPA entities
│   │   ├── filter/          # Security filters
│   │   ├── mapper/          # MapStruct mappers
│   │   ├── models/          # DTOs and request/response models
│   │   ├── repository/      # JPA repositories
│   │   ├── service/         # Business logic
│   │   └── CrudApplication.java
│   └── resources/
│       ├── application.properties
│       └── db/migration/    # Flyway migration scripts
└── test/                    # Test classes
```

## 🔧 Configuration

### Application Properties
The application uses environment variables for configuration:

- `SPRING_DATASOURCE_URL`: Database connection URL
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password
- `JWT_SECRET`: Secret key for JWT token signing

### Docker Configuration
- **Application Port**: 8080
- **Database Port**: 5432
- **Health Check**: Configured for application monitoring

## 🧪 Testing

UNDER CONSTRUCTION.
```bash
# Run tests
```

## 🚀 Deployment

### Docker Deployment
The application includes Docker support with multi-stage builds:

1. Build the application: `./mvnw clean package`
2. Build Docker image: `docker build -t crud-api .`
3. Run with Docker Compose: `docker-compose up`

### Production Considerations
- Set strong JWT secret in production
- Configure proper database credentials
- Use HTTPS in production
- Set up proper logging and monitoring
- Configure database connection pooling
- Set up backup strategies for PostgreSQL

## 🛡️ Security Features

- **JWT Authentication**: Stateless authentication with configurable expiration
- **Password Encryption**: BCrypt with salt for password security
- **Input Validation**: Comprehensive validation for all endpoints
- **CORS Configuration**: Configurable cross-origin requests
- **Security Headers**: Proper security headers in responses
- **SQL Injection Protection**: JPA prevents SQL injection attacks

## 🐛 Error Handling

The API provides consistent error responses with the following structure:

```json
{
  "timestamp": "2025-01-20T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/users"
}
```

### Common HTTP Status Codes
- `200 OK`: Successful operation
- `201 Created`: Resource created successfully  
- `400 Bad Request`: Invalid request data
- `401 Unauthorized`: Authentication required
- `404 Not Found`: Resource not found
- `409 Conflict`: Resource already exists
- `500 Internal Server Error`: Server error

## 📞 Support

For questions or support, please contact:
- **Author**: Patrick Batista
- **Email**: patrickbpds@outlook.com
- **GitHub**: [Patrickbpds](https://github.com/Patrickbpds)

## 📄 License

This project is licensed under the GPL 3.0 License - see the [LICENSE](https://www.gnu.org/licenses/gpl-3.0) for details.

---