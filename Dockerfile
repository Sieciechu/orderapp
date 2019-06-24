FROM gradle:jdk11 as builder
USER root
COPY . /home/gradle/project
WORKDIR /home/gradle/project

RUN gradle build

FROM openjdk:11-jre
COPY --from=builder  /home/gradle/project/build/libs/orderapp.jar /app/orderapp.jar
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "/app/orderapp.jar"]
