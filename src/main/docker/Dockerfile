FROM openjdk:17-alpine
MAINTAINER Sergio Cortes "scortesv@soler.com"
WORKDIR /usr/local/bin/
COPY maven/grading-0.0.1-SNAPSHOT.jar grading.jar
CMD ["java","-Dspring.profiles.active=dev","-jar","grading.jar"]