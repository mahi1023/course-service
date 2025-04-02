# # Use an official JDK 17 image as the base
# FROM openjdk:17-jdk-slim

# # Set the working directory inside the container
# WORKDIR /app

# # Copy the built JAR file into the container
# COPY target/course-service.jar course-service.jar

# # Expose the application port (Spring Boot default is 8080)
# EXPOSE 8080

# # Command to run the application
# ENTRYPOINT ["java", "-jar", "course-service.jar"]
FROM maven as build
WORKDIR /app
COPY . /app
RUN mvn -T 16C package -Dmaven.test.skip=true

FROM openjdk:21
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
