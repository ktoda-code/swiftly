# Dockerfile for Swiftly application

# Stage 1: Build stage
FROM maven:3.8.4-openjdk-17 as build

# Copy pom.xml and install dependencies
COPY pom.xml /usr/src/app/
WORKDIR /usr/src/app
RUN mvn dependency:go-offline

# Copy source code
COPY src /usr/src/app/src

# Build the application
RUN mvn package -DskipTests

# Stage 2: Run stage
FROM openjdk:17

# Copy built JAR from the build stage
COPY --from=build /usr/src/app/target/swiftly-1.0.0-SNAPSHOT.jar /usr/app/swiftly-1.0.0-SNAPSHOT.jar

WORKDIR /usr/app

# Command to run the application
CMD ["java", "-jar", "swiftly-1.0.0-SNAPSHOT.jar"]
