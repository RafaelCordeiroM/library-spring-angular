# === BUILD ===
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copia pom e baixa dependências (cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia código e compila
COPY src ./src
RUN mvn clean package -DskipTests

# === RUN ===
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia QUALQUER JAR gerado (não importa o nome exato)
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]