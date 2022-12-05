FROM openjdk:11
ADD target/repuve-system.jar repuve-system.jar
ENTRYPOINT ["java", "-jar","repuve-system.jar"]
