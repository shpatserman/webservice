FROM alpine:3.15
RUN apk update
RUN apk add openjdk11-jre-headless
MAINTAINER netunix
WORKDIR /app
EXPOSE 8080
ENV SESSION=ru.netunix.TestSession
COPY ./target/webservice-0.0.1-SNAPSHOT.jar .
COPY ./entrypoint.sh .
RUN chmod +x entrypoint.sh

ENTRYPOINT  ["/app/entrypoint.sh"]