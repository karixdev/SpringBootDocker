#FROM eclipse-temurin:17-jdk-alpine as build
#WORKDIR /app
#
#COPY mvnw .
#COPY .mvn .mvn
#COPY pom.xml .
#COPY src src
#
#RUN ./mvnw install -DskipTests
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
#
#FROM eclipse-temurin:17-jdk-alpine
#VOLUME /tmp
#ARG DEPENDENCY=/app/target/dependency
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","hello.Application"]

#FROM openjdk:17 as build
#WORKDIR /app

#COPY mvnw .
#COPY .mvn .mvn
#COPY pom.xml .
#COPY src src
#
#RUN ./mvnw package -DskipTests
#COPY target/*.jar app.jar

#FROM openjdk:17
#WORKDIR /app
#
#COPY --from=build /app/app.jar .
#
#COPY target/*.jar app.jar
#
#ENTRYPOINT ["java", "-jar", "app.jar"]

FROM maven:3.8.7-eclipse-temurin-17 AS builder

COPY pom.xml /app/
COPY src /app/src
RUN --mount=type=cache,target=/root/.m2 mvn -f /app/pom.xml clean package -DskipTests -Pprod

FROM eclipse-temurin:17.0.6_10-jre-jammy
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]