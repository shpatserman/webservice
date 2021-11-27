FROM openjdk:11
MAINTAINER netunix
COPY ./target/webservice-0.0.1-SNAPSHOT.jar /app/webservice-0.0.1-SNAPSHOT.jar
CMD  ["java","-jar","/app/webservice-0.0.1-SNAPSHOT.jar"]
