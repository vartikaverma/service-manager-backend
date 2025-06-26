# Webapp: Service Management API

This is a full-stack web application backend built with Spring Boot and MongoDB. 
It models and manages a `Service` entity, which contains a list of `Resource` objects, and each `Resource` contains a list of `Owner` objects. 
The backend exposes a REST API for full CRUD operations and supports advanced queries and nested updates.

## Features
- CRUD operations for `Service` (with nested `Resource` and `Owner`)
- Query services by resource ID or owner ID
- Delete a resource or owner from a service by their IDs
- MongoDB integration (local or Atlas)
- Multiple Spring profiles: dev, qa, prod
- Thread-safe in-memory cache and dummy endpoints for demonstration
- Swagger UI for API documentation
- Bash script (`run.sh`) for easy startup

## Requirements
- Java 17 or later
- Maven
- MongoDB (local or Atlas)

## Getting Started

### 1. Clone the repository
```
git clone <your-repo-url>
cd webapp
```

### 2. Configure MongoDB
Edit `src/main/resources/application.properties` or the appropriate profile file:
```
spring.data.mongodb.uri=mongodb://localhost:27017/webapp
# Or for Atlas:
# spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster-url>/webapp?retryWrites=true&w=majority
```

### 3. Select a Spring profile (optional)
Set the active profile in `application.properties` or via environment variable:
```
spring.profiles.active=prod
```
Available profiles: `dev`, `qa`, `prod`

### 4. Build and Run
Use the provided Bash script:
```
bash run.sh
```
Or run manually:
```
./mvnw clean spring-boot:run -Dspring-boot.run.profiles=dev
```
Or build the JAR:
```
./mvnw clean package
java -jar target/webapp-0.0.1-SNAPSHOT.jar
```

### 5. Access the API
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
- Dummy endpoints (thread-safe cache demo):
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

## License
MIT
