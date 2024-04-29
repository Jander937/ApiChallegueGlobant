# Utiliza una imagen base con Java y Maven
FROM maven:sapmachine AS build

# Copia el código fuente y el archivo POM
COPY pom.xml /home/app/pom.xml
COPY src /home/app/src

# Establece el directorio de trabajo
WORKDIR /home/app

# Compila el proyecto
RUN mvn clean package -DskipTests

# Etapa de construcción final
FROM openjdk:17-jdk-alpine3.14

# Copia el jar compilado desde la etapa de compilación anterior
COPY --from=build /home/app/target/marketplace-0.0.1-SNAPSHOT.jar /usr/local/lib/marketplace.jar

# Expone el puerto en el que se ejecuta la aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot cuando se inicia el contenedor
CMD ["java", "-jar", "/local/lib/marketplace.jar"]

