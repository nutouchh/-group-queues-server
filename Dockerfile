FROM openjdk:17-alpine
FROM maven:3.8.5-openjdk-17-slim

WORKDIR /server

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8080

# development
# CMD ["mvn", "spring-boot:run"]

# production
CMD ["java", "-jar", "/server/target/group-queues-server-0.1.0.jar"]