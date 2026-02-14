FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/search-index-engine-1.0-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
