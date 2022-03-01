FROM openjdk:11
ARG JAR_FILE=build/libs/kiosk-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} kiosk.jar
ENTRYPOINT ["java","-jar","/kiosk.jar"]
EXPOSE 8080
