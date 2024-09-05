# Use an official OpenJDK runtime as a parent image
FROM gradle:latest AS builder
WORKDIR /workspace
COPY . /workspace
RUN gradle build -x test

FROM eclipse-temurin:17-jre-focal
ENV SPRING_PROFILES_ACTIVE=container
ENV MONOGDB_URI mongodb://localhost:27017/kratos
ENV MONGODB_USERNAME MONGO
ENV MONGODB_PASSWORD MONGO

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the GitHub Action artifact
COPY --from=builder /workspace/build/libs/bifrost.jar /app/bifrost.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/bifrost.jar"]