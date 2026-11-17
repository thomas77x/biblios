# --- Compilar y empaquetar el WAR con Maven ---
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -B dependency:go-offline
COPY src ./src
RUN mvn -q -B package -DskipTests

# --- Ejecutar en Tomcat 11 (Jakarta EE 11, requerido por Jersey 4) ---
FROM tomcat:11.0-jdk21-temurin
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Render indica el puerto en la variable PORT (por defecto 10000)
ENV PORT=10000
EXPOSE 10000
CMD ["sh", "-c", "sed -i \"s/port=\\\"8080\\\"/port=\\\"${PORT}\\\"/\" /usr/local/tomcat/conf/server.xml && exec catalina.sh run"]
