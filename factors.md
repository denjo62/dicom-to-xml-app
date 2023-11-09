# Twelve-Factor Principles Implemented in dicom-to-xml Microservice

## 1. Codebase

- **Goal**: Maintain a single codebase.
- **Implementation**: The dicom-to-xml microservice maintains a single code repository on [GitHub](https://github.com/denjo62/dicom-to-xml-app). All code related to the microservice is stored in this centralized repository.

## 2. Dependencies

- **Goal**: Explicitly declare and isolate dependencies.
- **Implementation**: The microservice uses dependency management tools such as Maven for Java to explicitly declare and manage project dependencies. Dependencies are isolated within the Docker image to ensure consistent and reproducible builds.

## 3. Config

- **Goal**: Store configuration in the environment.
- **Implementation**: Configuration parameters, such as database connection details, are externalized as environment variables. This allows for easy configuration changes without modifying the codebase. An example is the usage of environment variables like `SPRING_DATASOURCE_URL` and others.

## 4. Backing Services

- **Goal**: Treat MySQL as a backing service.
- **Implementation**: MySQL is treated as a backing service. Connection details and credentials are configured through environment variables. The microservice depends on MySQL, and health checks ensure that the MySQL service is healthy before starting the dicom-to-xml microservice.

## 5. Build-Release-Run

- **Goal**: Separate build, release, and run stages.
- **Implementation**: The build, release, and run stages are separated. Docker is used for containerization, allowing for consistent releases and runtime environments. Build scripts, Dockerfiles, and runtime configurations are distinct.

## 6. Processes

- **Goal**: Execute the app as one or more stateless processes.
- **Implementation**: The dicom-to-xml microservice is designed as a stateless Spring Boot application. It can be horizontally scaled by running multiple instances, each independently handling requests without shared state.

## 7. Port Binding

- **Goal**: Export services via port binding.
- **Implementation**: The microservice exports its services through port binding. For example, the application is bound to port 8080, allowing external access to the DICOM to XML conversion service.

## 8. Concurrency

- **Goal**: Implement stateless and scalable services.
- **Implementation**: The microservice is designed to be stateless, allowing for easy scalability. The stateless design enables the microservice to handle multiple concurrent requests, supporting scalability and responsiveness.

