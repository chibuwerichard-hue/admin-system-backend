FROM maven:3.8.1-openjdk-17 as builder 
WORKDIR /app 
COPY pom.xml . 
RUN mvn dependency:go-offline 
COPY src ./src 
RUN mvn clean install -DskipTests 
 
FROM openjdk:17-jdk-slim 
WORKDIR /app 
COPY --from=builder /app/target/admin-system-0.0.1-SNAPSHOT.jar app.jar 
EXPOSE 8080 
CMD ["java", "-jar", "app.jar"] 
