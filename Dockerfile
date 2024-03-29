FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml . 

RUN mvn -B -DskipTests=true dependency:go-offline

COPY src ./src

RUN mvn -B -DskipTests=true package

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]