FROM openjdk:11
WORKDIR /app
COPY target/student-tasks-0.0.1-SNAPSHOT.jar student-tasks-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "student-tasks-0.0.1-SNAPSHOT.jar"]