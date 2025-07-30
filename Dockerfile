FROM openjdk:21
WORKDIR /app
COPY target/LibraryManagement-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]