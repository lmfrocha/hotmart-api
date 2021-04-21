FROM openjdk:8-jdk-alpine
MAINTAINER Lucas Rocha <lucas.marcelino@outlook.com>
# Expose Web Port
EXPOSE 8080
COPY target/hotmart-marketplace-api-0.0.1-SNAPSHOT.jar hotmart-marketplace-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/hotmart-marketplace-api-0.0.1-SNAPSHOT.jar"]