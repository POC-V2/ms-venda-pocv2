FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /build

COPY . .

RUN mvn -f pom.xml clean package 

# Release Image
FROM openjdk:11-jre AS release
COPY --from=build /build/target/*.jar /app.jar

COPY docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]
