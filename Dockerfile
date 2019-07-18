
ARG VCS_REF
ARG BUILD_DATE

# -----------------------------
FROM gradle:jdk11 as builder
USER root
COPY . /home/gradle/project
WORKDIR /home/gradle/project

RUN gradle build

# -----------------------------
FROM openjdk:11-jre as app
ARG VCS_REF
ARG BUILD_DATE


WORKDIR /app
COPY --from=builder  /home/gradle/project/build/libs/orderapp-all.jar /app/orderapp.jar
COPY --from=builder  /home/gradle/project/src/main/resources/* /app/input/
RUN mkdir ./reports

LABEL org.label-schema.build-date=${BUILD_DATE} \
      org.label-schema.vcs-ref=${VCS_REF} \
      org.label-schema.vcs-url="https://github.com/Sieciechu/orderapp"


CMD java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar /app/orderapp.jar /app/input/orders.csv /app/input/orders.xml
