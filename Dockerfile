FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} baribari.jar

ENTRYPOINT ["java", "-jar", "baribari.jar"]