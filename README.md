# Webapp: Swisscom Service Management

A backend web application built with Spring Boot and MongoDB for managing services, resources, and owners. The backend exposes a REST API for full CRUD operations, advanced queries, and nested updates.

---

## Table of Contents
- [Features](#features)
- [Requirements](#requirements)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Data Model Example](#data-model-example)
- [Testing](#testing)
- [Design Decisions & Highlights](#design-decisions--highlights)
- [Possible Improvements](#possible-improvements)
- [License](#license)

---

## Features
- CRUD operations for `Service` (with nested `Resource` and `Owner`)
- Query services by resource ID or owner ID
- Delete a resource or owner from a service by their IDs
- MongoDB integration (local or Atlas)
- Multiple Spring profiles: dev, qa, prod
- Thread-safe in-memory cache and dummy endpoints for demonstration (Caffeine cache)
- Swagger UI for API documentation
- Bash script (`run.sh`) for easy startup

## Requirements
- Java 17 or later
- Maven
- MongoDB (local or Atlas)

## Getting Started

### 1. Clone the repository
```sh
git clone https://github.com/vartikaverma/service-manager-backend.git
cd service-manager-backend
```

### 2. Configure MongoDB
Edit `src/main/resources/application.properties` or the appropriate profile file:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/webapp
# Or for Atlas:
# spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster-url>/webapp?retryWrites=true&w=majority
```

### 3. Select a Spring profile (optional)
Set the active profile in `application.properties` or via environment variable:
```properties
spring.profiles.active=prod
```
Available profiles: `dev`, `qa`, `prod`

### 4. Build and Run
Use the provided Bash script:
```sh
bash run.sh
```
Or run manually:
```sh
./mvnw clean spring-boot:run -Dspring-boot.run.profiles=dev
```
Or build the JAR:
```sh
./mvnw clean package
java -jar target/webapp-0.0.1-SNAPSHOT.jar
```

## Project Structure
```
service-manager-backend/
├── src/
│   ├── main/
│   │   ├── java/com/vverma/webapp/
│   │   │   ├── WebappApplication.java         # Main Spring Boot entry point
│   │   │   ├── WebappController.java          # Welcome endpoint
│   │   │   ├── controller/
│   │   │   │   ├── ServiceController.java     # REST API for Service
│   │   │   │   └── DummyController.java       # Dummy endpoints (Caffeine cache demo)
│   │   │   ├── model/
│   │   │   │   ├── Service.java
│   │   │   │   ├── Resource.java
│   │   │   │   ├── Owner.java
│   │   │   │   └── Dummy.java
│   │   │   └── repository/
│   │   │       └── ServiceRepository.java     # MongoDB repository
│   │   └── resources/
│   │       └── application-*.properties       # Configurations for profiles
│   └── test/
│       └── java/com/vverma/webapp/
│           └── WebappApplicationTests.java    # Basic context test
├── run.sh                                    # Startup script
├── pom.xml                                   # Maven config
└── README.md
```

## API Endpoints
- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- Example endpoints:
  - `GET /services` — List all services
  - `POST /services` — Create a new service
  - `GET /services/{id}` — Get a service by ID
  - `PUT /services/{id}` — Update a service
  - `DELETE /services/{id}` — Delete a service
  - `GET /services/by-resource/{resourceId}` — Find services by resource ID
  - `GET /services/by-owner/{ownerId}` — Find services by owner ID
  - `DELETE /services/{serviceId}/resource/{resourceId}` — Delete a resource from a service
  - `DELETE /services/{serviceId}/resource/{resourceId}/owner/{ownerId}` — Delete an owner from a resource in a service
- Dummy endpoints (Caffeine cache demo):
  - `POST /dummy` — Create dummy object
  - `PUT /dummy/{id}` — Update dummy object
  - `GET /dummy/{id}` — Get dummy object

## Data Model Example
```json
{
  "id": "service_id_1",
  "resources": [
    {
      "id": "resource_id_1",
      "owners": [
        { "id": "owner_id_1_1", "name": "Owner 1", "accountNumber": "AC001", "level": 1 },
        { "id": "owner_id_1_2", "name": "Owner 2", "accountNumber": "AC002", "level": 2 }
      ]
    },
    {
      "id": "resource_id_2",
      "owners": [
        { "id": "owner_id_2_1", "name": "Owner 3", "accountNumber": "AC003", "level": 1 },
        { "id": "owner_id_2_2", "name": "Owner 4", "accountNumber": "AC004", "level": 2 }
      ]
    }
  ]
}
```

## Testing
Run all tests with:
```sh
./mvnw test
```

## Design Decisions & Highlights
- **Spring Boot & MongoDB**: Chosen for rapid development and flexible data modeling.
- **Profile-based Config**: Easily switch between dev, qa, and prod environments.
- **Thread-safe Cache**: Demonstrates concurrency handling using `Caffeine`.
- **Validation**: Uses Jakarta validation annotations for data integrity.
- **Swagger/OpenAPI**: Integrated for easy API exploration and documentation.
- **Separation of Concerns**: Clear separation between controllers, models, and repository.

## Possible Improvements
- Add more comprehensive unit and integration tests.
- Use DTOs to decouple API from internal models.
- Implement global exception handling for better error responses.
- Add authentication and authorization (e.g., Spring Security).
- Add logging for better traceability.
- Use Lombok to reduce boilerplate in models.
- Add CI/CD pipeline for automated testing and deployment.

## License
MIT
