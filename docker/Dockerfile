# Build stage
FROM eclipse-temurin:21-jdk AS build

ENV GRADLE_OPTS="-Dorg.gradle.vfs.watch=false"

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY src src
COPY build.gradle .
COPY checkstyle.xml .

RUN ./gradlew build -x test --no-daemon



# Runtime stage (puede ser Alpine si quieres optimizar)
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
