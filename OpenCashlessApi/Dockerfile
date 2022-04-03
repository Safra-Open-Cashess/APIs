FROM openjdk:11
VOLUME /tmp
COPY target/cashless-api-0.0.1-SNAPSHOT.jar cashless-api-0.0.1-SNAPSHOT.jar
COPY src/main/resources/application.properties application.properties
ENTRYPOINT ["java","-jar","/cashless-api-0.0.1-SNAPSHOT.jar"]