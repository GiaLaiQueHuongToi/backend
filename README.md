# Clothing Store Manager

## Description
A Spring Boot application that provides comprehensive management solutions for clothing retail operations, including inventory tracking, sales processing, and business analytics.

## Key Features
- Inventory management with categorization by clothing type, size, and brand
- Sales and order processing with receipt generation
- Customer relationship management and loyalty program tracking
- Employee scheduling and performance tracking
- Reporting and analytics dashboard for sales trends
- Discount and promotion management system
- Barcode integration for quick product lookups
- Secure user authentication and role-based access control

## Technologies
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring Security
- Maven
- MySQL/PostgreSQL database
- Hibernate ORM
- Lombok

## Prerequisites
- JDK 17 or newer
- Maven 3.6+
- MySQL/PostgreSQL database server

## Installation

Clone the repository:
```bash
git clone https://github.com/vuhoabinhthachhoa/clothing-store-manager.git
cd clothing-store-manager
```

Build the project:
```bash
mvn clean install
```

## Running the Application

```bash
mvn spring-boot:run
```

Or after building:
```bash
java -jar target/clothing-store-manager-0.0.1-SNAPSHOT.jar
```

## Configuration
Application configuration is in `src/main/resources/application.properties` or `application.yml`.

## API Documentation
API documentation is available at `http://localhost:8080/swagger-ui.html` when the application is running.

## Testing
```bash
mvn test
```

## Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Open a Pull Request

## License
MIT License
