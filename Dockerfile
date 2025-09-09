FROM maven:3.9.5-eclipse-temurin-17

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .

# Keep the container running so you can see it in Docker Desktop
CMD ["tail", "-f", "/dev/null"]
