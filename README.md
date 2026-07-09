# Library Catalog API

A RESTful API built with Spring Boot for managing authors and books.

The project uses PostgreSQL as the database and Docker Compose to run the database container.

## Features

### Author Management

- Create an author
- Get all authors
- Get an author by ID
- Update an author
- Delete an author
- Prevent deletion of an author who has associated books

### Book Management

- Create a book
- Get all books
- Get a book by ID
- Update a book
- Delete a book
- Get books by author ID

### API Enhancements

- Request and response DTOs
- Entity-to-DTO mapping
- Request validation
- Global exception handling
- Standardized error responses
- Swagger/OpenAPI documentation
- `400 Bad Request` for validation errors
- `404 Not Found` for missing resources
- `409 Conflict` for business and data conflicts
- `500 Internal Server Error` for unexpected errors

## Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Docker
- Docker Compose
- Maven
- Bean Validation
- Springdoc OpenAPI
- Git
- GitHub

## Project Structure

```text
src/main/java/com/ejada/librarycatalog
├── config
├── controller
├── dto
├── entity
├── exception
├── mapper
├── repository
├── service
└── LibraryCatalogApplication.java
```

## Database Relationship

The application contains two main entities:

- `Author`
- `Book`

Each book belongs to one author.

```text
books.author_id -> authors.id
```

## Prerequisites

Before running the project, make sure you have:

- Java 17 or later
- Docker Desktop
- Git

The project includes the Maven Wrapper, so installing Maven separately is not required.

## Running the Project

### 1. Start PostgreSQL with Docker

Make sure Docker Desktop is running.

Then run:

```bash
docker compose up -d
```

Check that the container is running:

```bash
docker ps
```

### 2. Run the Spring Boot Application

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

On macOS or Linux:

```bash
./mvnw spring-boot:run
```

The application runs at:

```text
http://localhost:8080
```

### 3. Stop the Database Container

```bash
docker compose down
```

## API Endpoints

### Author Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/authors` | Create a new author |
| GET | `/api/authors` | Get all authors |
| GET | `/api/authors/{id}` | Get an author by ID |
| PUT | `/api/authors/{id}` | Update an author |
| DELETE | `/api/authors/{id}` | Delete an author |

### Book Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/books` | Create a new book |
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get a book by ID |
| PUT | `/api/books/{id}` | Update a book |
| DELETE | `/api/books/{id}` | Delete a book |
| GET | `/api/books/author/{authorId}` | Get books by author ID |

## API Documentation

Swagger/OpenAPI documentation is available through Springdoc.

After starting the application, open:

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

Swagger UI can be used to explore and test the Author and Book API endpoints.

## Example Requests

### Create an Author

```http
POST /api/authors
Content-Type: application/json
```

```json
{
  "name": "George Orwell",
  "email": "orwell@example.com",
  "nationality": "British"
}
```

### Create a Book

```http
POST /api/books
Content-Type: application/json
```

```json
{
  "title": "1984",
  "isbn": "9780451524935",
  "publicationYear": 1949,
  "authorId": 1
}
```

## Validation

Request data is validated before processing.

Examples of invalid input include:

- Blank required fields
- Invalid email format
- Invalid publication year
- Invalid author ID

Invalid requests return:

```text
400 Bad Request
```

## Exception Handling

The application uses global exception handling with `@RestControllerAdvice`.

### Resource Not Found

If an author or book does not exist:

```text
404 Not Found
```

Example response:

```json
{
  "timestamp": "2026-07-09T10:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Author not found with id: 999999",
  "path": "/api/authors/999999"
}
```

### Business Conflict

Deleting an author who still has associated books returns:

```text
409 Conflict
```

### Unexpected Errors

Unexpected server errors return:

```text
500 Internal Server Error
```

## Git Workflow

The project was developed incrementally using feature branches.

Main branches include:

```text
main
feature/author-management
feature/book-management
feature/api-enhancements
feature/api-documentation
```

The project was divided into the following phases:

- **Phase 1:** Author management
- **Phase 2:** Book management and Author-Book relationship
- **Phase 3:** DTOs, validation, mapping, and exception handling
- **API Documentation:** Swagger/OpenAPI documentation using Springdoc

Feature branches were integrated through pull requests and merge commits.

## Upcoming Work

- Complete and document the Git conflict resolution exercise

## Author

Salma Mohamed Hafez