FROM maven:3.9.6-eclipse-temurin-17-alpine as builder
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /usr/src/app
COPY --from=builder /usr/src/app/target/config-service-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8888
CMD ["java", "-jar", "app.jar"]