FROM openjdk:11
MAINTAINER netunix
WORKDIR /app
COPY ./target/webservice-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT  ["java","-jar","/app/webservice-0.0.1-SNAPSHOT.jar"]
