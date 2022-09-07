FROM openjdk:17-alpine
MAINTAINER openpix.com
COPY target/openpix-0.0.1-SNAPSHOT.jar openpix-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/openpix-0.0.1-SNAPSHOT.jar"]
