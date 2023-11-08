# Use the official Corretto 17 base image
FROM amazoncorretto:17

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file (build your project as a JAR) to the container
COPY target/DicomToXML-1.0-SNAPSHOT.jar /app/DicomToXML-1.0-SNAPSHOT.jar

# Specify the command to run your microservice when the container starts
CMD ["java", "-jar", "DicomToXML-1.0-SNAPSHOT.jar"]

# Expose the port your service will run on (you can change this to your service's port)
EXPOSE 8080