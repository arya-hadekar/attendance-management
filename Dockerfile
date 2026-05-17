FROM eclipse-temurin:17

WORKDIR /app

COPY target/*.jar attendance.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","attendance.jar"]