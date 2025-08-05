# Company Employee Management API

This is a Spring Boot 3 application for managing employees and divisions within a company. It provides RESTful APIs for CRUD operations on employees and divisions, with features like pagination, searching, and filtering.

## Technologies Used

*   Spring Boot 3
*   Spring Data JPA
*   Lombok
*   PostgreSQL
*   Swagger/OpenAPI 3 for API documentation

## Setup and Running Locally

### Prerequisites

*   Java 17 or higher
*   Maven
*   Docker (optional, for PostgreSQL)

### 1. Clone the repository

```bash
git clone https://github.com/koswara-dev/company.git
cd company
```

### 2. Database Configuration

The application can uses an H2 in-memory database by default for development. For a persistent database, you can configure PostgreSQL.


**Using PostgreSQL with Docker Compose:**
You can use the provided `docker-compose-postgres.yml` to run a PostgreSQL database.

```bash
docker-compose -f docker-compose-postgres.yml up -d
```

Update `src/main/resources/application.properties` to use PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/companydb
spring.datasource.username=user
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 3. Build and Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Documentation

The API documentation is available via Swagger UI. Once the application is running, navigate to:
`http://localhost:8080/swagger-ui.html`

### Employee Endpoints (`/api/v1/employees`)

| Method | Path | Description | Request Body | Response Body |
| :----- | :--- | :---------- | :----------- | :------------ |
| `POST` | `/` | Create a new employee | `EmployeeRequest` | `Response<EmployeeResponse>` |
| `GET` | `/{id}` | Get an employee by its ID | None | `Response<EmployeeResponse>` |
| `GET` | `/` | Get all employees (paginated, searchable by name, filterable by division) | None | `PaginatedResponse<EmployeeResponse>` |
| `PUT` | `/{id}` | Update an existing employee | `EmployeeRequest` | `Response<EmployeeResponse>` |
| `DELETE` | `/{id}` | Delete an employee by its ID | None | `Response<Void>` |

### Division Endpoints (`/api/v1/divisions`)

| Method | Path | Description | Request Body | Response Body |
| :----- | :--- | :---------- | :----------- | :------------ |
| `POST` | `/` | Create a new division | `DivisionRequest` | `Response<DivisionResponse>` |
| `GET` | `/{id}` | Get a division by its ID | None | `Response<DivisionResponse>` |
| `GET` | `/` | Get all divisions | None | `Response<List<DivisionResponse>>` |
| `PUT` | `/{id}` | Update an existing division | `DivisionRequest` | `Response<DivisionResponse>` |
| `DELETE` | `/{id}` | Delete a division by its ID | None | `Response<Void>` |

## DTOs (Data Transfer Objects)

### `EmployeeRequest`

```java
public class EmployeeRequest {
    private String name;
    private String position;
    private Long divisionId;
    // Getters and Setters
}
```

### `EmployeeResponse`

```java
public class EmployeeResponse {
    private Long id;
    private String name;
    private String position;
    private DivisionResponse division;
    // Getters and Setters
}
```

### `DivisionRequest`

```java
public class DivisionRequest {
    private String name;
    // Getters and Setters
}
```

### `DivisionResponse`

```java
public class DivisionResponse {
    private Long id;
    private String name;
    // Getters and Setters
}
```

### `Response` (Generic API Response Wrapper)

```java
public class Response<T> {
    private boolean success;
    private String message;
    private T data;
    // Getters and Setters
}
```

### `PaginatedResponse` (For Paginated Results)

```java
public class PaginatedResponse<T> {
    private boolean success;
    private String message;
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;
    // Getters and Setters
}
```

## Buy me a coffe

If you like this project and want to support its further development, buy me a coffee!

[![Buy Me a Coffee](https://www.buymeacoffee.com/assets/img/guidelines/download-assets-sm-1.svg)](https://www.buymeacoffee.com/kudajengke404)
