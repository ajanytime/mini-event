FROM openjdk:8
ADD build/libs/mini-event-api-0.0.1-SNAPSHOT.jar mini-event-api-0.0.1-SNAPSHOT.jar
RUN bash -c 'touch /mini-event-api-0.0.1-SNAPSHOT.jar'
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "mini-event-api-0.0.1-SNAPSHOT.jar"]