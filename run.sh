#!/bin/bash
# Run the Spring Boot application using Maven Wrapper with a clean build

#values of PROFILE can be dev, test, prod
PROFILE=${1:-prod}
./mvnw clean spring-boot:run -Dspring-boot.run.profiles=$PROFILE
