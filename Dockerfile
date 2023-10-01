FROM eclipse-temurin:17-jre

WORKDIR /app

COPY ["./user-service-v2-server/target/user-service-v2-server-0.0.1-SNAPSHOT.jar", "."]

EXPOSE 8092

CMD java -jar user-service-v2-server-0.0.1-SNAPSHOT.jar