FROM registry.access.redhat.com/ubi9/openjdk-21:latest AS build
USER root
COPY . .
RUN cp ./build/libs/foodapi-*-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","-jar","/app.jar"]