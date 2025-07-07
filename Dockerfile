FROM ubuntu:latest AS build

RUN apt-get update && \
    apt-get install -y openjdk-21-jdk maven

WORKDIR /app
COPY . .

RUN mvn clean package

FROM openjdk:21-jdk-slim

WORKDIR /app
EXPOSE 8080

COPY --from=build /app/target/vacancy_management-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

