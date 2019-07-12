FROM gradle:jdk11 as builder
USER root
COPY . /home/gradle/project
WORKDIR /home/gradle/project

RUN gradle build && gradle shadowJar

FROM openjdk:11-jre
WORKDIR /app
COPY --from=builder  /home/gradle/project/build/libs/orderapp-all.jar /app/orderapp.jar
COPY --from=builder  /home/gradle/project/src/main/resources/* /app/input/
CMD java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar /app/orderapp.jar /app/input/orders.csv /app/input/orders.xml
