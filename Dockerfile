FROM alpine:edge
RUN apk add --no-cache openjdk17
COPY target/News-site-1.jar News-site-1.jar
ENTRYPOINT ["java","-jar","News-site-1.jar"]
EXPOSE 8080
