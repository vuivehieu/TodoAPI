FROM openjdk:11
ADD target/TodoAPI-0.0.1-SNAPSHOT.jar TodoAPI-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "TodoAPI-0.0.1-SNAPSHOT.jar"]