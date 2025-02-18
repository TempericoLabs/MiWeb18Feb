# Build stage
FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app
# Aquí está el cambio: quitamos el .original
COPY --from=builder /app/target/MiWeb-0.0.1-SNAPSHOT.jar ./app.jar
RUN ls -la
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]