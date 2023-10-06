FROM openjdk:17
EXPOSE 8080
WORKDIR /app
COPY target/todo-0.0.1-SNAPSHOT.jar todo-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "todo-0.0.1-SNAPSHOT.jar"]