#
# Build stage
#
FROM maven:3.8.6-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM azul/zulu-openjdk:11.0.14.1-11.54.25
COPY --from=build /home/app/target/ie21a-class-championship-wd.jar /opt/ie21a-class-championship/ie21a-class-championship.jar
EXPOSE 7777
ENTRYPOINT ["java","-jar","/opt/ie21a-class-championship/ie21a-class-championship.jar"]
