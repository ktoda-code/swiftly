# Dockerfile for Swiftly application

# Stage 1: Build stage
FROM maven:3.9.5-amazoncorretto-17 as base

# Copy pom.xml and install dependencies
COPY pom.xml /usr/src/app/
WORKDIR /usr/src/app
RUN mvn dependency:go-offline

# Copy source code
COPY src /usr/src/app/src

# Build the application
RUN mvn package -DskipTests

# Stage 2: Test (size > 1000 MB) - test
FROM base as test
CMD [ "mvn", "test" ]

# Stage 3: Development (size > 1000 MB) - dev
FROM base as development
CMD [ "mvn", "spring-boot:run", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'" ]

# Stage 3: Production stage (size < 400 MB) - latest
FROM amazoncorretto:17-alpine as production
EXPOSE 8080

# Copy built JAR from the build stage
COPY --from=base /usr/src/app/target/swiftly-*.jar /usr/app/swiftly.jar

WORKDIR /usr/app

# Command to run the application
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "swiftly.jar"]
