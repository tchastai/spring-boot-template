FROM openjdk:17-alpine

VOLUME /tmp
ADD /target/spring-stream-*.jar spring-stream.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/spring-stream.jar"]