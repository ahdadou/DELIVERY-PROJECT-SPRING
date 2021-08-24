FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY ./pom.xml ./pom.xml
COPY /target/gaming-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","gaming-0.0.1-SNAPSHOT.jar"]
CMD ["-start"]
#
## Stage1 - execute build process
#FROM openjdk:8-jdk-alpine as build_process
#WORKDIR /app
#COPY . .
## RUN ./gradlew build -x test
#
#
## Stage2 - boot app with the build output above
#FROM openjdk:8-jdk-alpine
#EXPOSE 80
#WORKDIR /app
#COPY target/*.jar .
#CMD java -jar gaming-0.0.1-SNAPSHOT.jar