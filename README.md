## Overview

The dicom-to-xml microservice converts DICOM files to XML format for interoperability.

## Prerequisites

Ensure that you have the following installed:

- Docker runtime

## Building the Docker Image

To build the Docker image for the dicom-to-xml microservice, run the following command:

    docker build -t denjo62/dicom-to-xml .

Replace dicom-to-xml with your preferred image name.
Running the Microservice

To run the dicom-to-xml microservice as a Docker container, execute the following command:

    docker run -p 8080:8080 denjo62/dicom-to-xml

To run mysql as a Docker container, execute the following command:

    docker run -d --name mysqldb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=dicomdatabase -p 3306:3306 mysql:8


Adjust the port mapping as needed.

The microservice can be configured using environment variables. Here are the configurable variables:

    SPRING_DATASOURCE_URL: JDBC URL for the MySQL database.
    SPRING_DATASOURCE_USERNAME: MySQL username.
    SPRING_DATASOURCE_PASSWORD: MySQL password.

Example:

    docker run -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/dicomdatabase -e SPRING_DATASOURCE_USERNAME=root -e SPRING_DATASOURCE_PASSWORD=root dicom-to-xml

##Docker Compose

You can use Docker Compose to orchestrate the dicom-to-xml microservice and MySQL. 

To start both services using Docker Compose, run:

    docker-compose up --build