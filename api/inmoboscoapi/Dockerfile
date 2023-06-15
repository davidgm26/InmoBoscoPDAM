FROM eclipse-temurin:18-jre-jammy
COPY target/inmoboscoapi-1.0.jar app.jar
COPY ./uploads ./uploads
COPY ./src ./src
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
