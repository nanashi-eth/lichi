FROM openjdk:17-jdk-alpine

COPY target/lichi-0.0.1-SNAPSHOT.jar lichi-1.0.jar

ENTRYPOINT [ "java", "-jar", "lichi-1.0.jar" ]