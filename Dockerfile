FROM maven:3.9.16-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy
COPY --from=build /target/asn2-0.0.1-SNAPSHOT.jar asn2.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "asn2.jar"]