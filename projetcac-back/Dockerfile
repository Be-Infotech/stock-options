# Utilisez une image de base Java 11
FROM openjdk:17
# Définissez le répertoire de travail
WORKDIR /app

# Copiez le fichier JAR de votre application dans le conteneur
COPY target/projetcac-back-0.0.1-SNAPSHOT.jar app.jar

# Exposez le port sur lequel l'application écoute (par défaut 8080)
EXPOSE 8080

# Commande pour exécuter l'application
CMD ["java", "-jar", "app.jar"]
