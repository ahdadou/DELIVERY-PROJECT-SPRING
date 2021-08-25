#FROM openjdk:8-jdk-alpine
#WORKDIR /app
#COPY /target/gaming-0.0.1-SNAPSHOT.jar /app/gaming-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","gaming-0.0.1-SNAPSHOT.jar"]


FROM maven:4.0.0-jdk-8-alpine as BUILD
COPY pom.xml /app/
COPY src /app/src/
WORKDIR /app
RUN mvn clean package -DskipTests

FROM openjdk:8-jdk-alpine
COPY --from=BUILD /app/target/*.jar application.jar
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=dev","/application.jar"]
