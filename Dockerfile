FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/chatbot-0.0.1-SNAPSHOT.jar chatbot.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "chatbot.jar"]
