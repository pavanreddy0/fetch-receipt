# Build the application
#FROM maven:3.4.1-openjdk-17 AS build
#WORKDIR /app
#
## Copy the project files
#COPY pom.xml .
#RUN mvn dependency:go-offline -B
#COPY src ./src
#
## Build the application
#RUN mvn clean package -DskipTests
#
## Create the runtime image
#FROM openjdk:17-jdk
## Add a volume pointing to /tmp
#WORKDIR /app
#VOLUME /app
#
## Make port 8080 available to the world outside this container
#EXPOSE 8080
#
## The application's jar file
#ARG JAR_FILE=target/receipt-processor-0.0.1-SNAPSHOT.jar
#
## Add the application's jar to the container
#ADD ${JAR_FILE} app.jar
#
## Run the jar file
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

FROM maven:3.8.4-openjdk-17 AS builder
COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package

FROM openjdk:17-jdk
COPY --from=builder /app/target/*.jar /app/msg-server.jar
ENTRYPOINT ["java", "-jar", "/app/msg-server.jar"]