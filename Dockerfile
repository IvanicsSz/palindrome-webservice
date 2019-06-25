FROM openjdk:8
COPY target/junior-java-backend-assignment-1.0-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "/app.jar"]
EXPOSE 8080
