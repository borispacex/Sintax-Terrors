FROM maven:3.9.6-eclipse-temurin-17-alpine as builder
WORKDIR /usr/src/app
COPY ./core/gateway-service/pom.xml ./pom.xml
RUN mvn dependency:go-offline
COPY ./core/gateway-service/ /usr/src/app
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /usr/src/app
COPY --from=builder /usr/src/app/target/gateway-service-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]