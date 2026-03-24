# ---- build stage ----
# ---- frontend ----
FROM node:22 AS frontend-build
WORKDIR /app

COPY frontend frontend

WORKDIR /app/frontend

RUN npm install
RUN npm run build

# ---- backend ----
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src src
COPY --from=frontend-build /app/frontend/dist/frontend/browser src/main/resources/static

RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar -x test

# ---- run stage ----
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
