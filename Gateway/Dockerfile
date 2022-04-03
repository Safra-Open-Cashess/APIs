FROM openjdk:11
VOLUME /tmp
COPY target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar
COPY src/main/resources/application.properties application.properties
ENTRYPOINT ["java","-jar","/demo-0.0.1-SNAPSHOT.jar","--spring.config.location=./application.properties"]