# Microservice Application using Spring Boot

This microservice application is designed and implemented using Java Spring Boot. It comprises several services for currency exchange and conversion, along with components like a naming server, custom API gateway, and distributed tracing using Zipkin.

## Features
- **Currency Exchange Service:** Retrieves information about the exchange rate between two currencies.
- **Currency Conversion Service:** Accesses the Currency Exchange Service to provide the final currency conversion result.
- **Naming Server:** Eureka Naming Server for dynamic scaling and service registration.
- **API Gateway:** Custom-built API Gateway for routing requests to appropriate microservices.


## Docker Support

The application provides Docker support, and you can use the provided `docker-compose.yaml` file to run the microservices in Docker containers.

## Getting Started

To get started with this microservice application, follow these steps:

1. Clone the repository: `git clone https://github.com/ShahafErez/microservices.git`
2. Navigate to each service/module and build using Maven.
3. Run each service/module independently or utilize the provided Docker image.


## License

This project is licensed under the [MIT License](LICENSE.md).
