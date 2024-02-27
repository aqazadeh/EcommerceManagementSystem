FROM openjdk:17-jdk-alpine
MAINTAINER aqazadeh.com
COPY target/ecommerce-1.0.0.jar ecommerce-1.0.0.jar
ENTRYPOINT ["java","-jar","/ecommerce-1.0.0.jar"]