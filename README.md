# Clinic Management System Backend

**Clinic Management System** is a backend application designed to assist doctors and patients in managing medical centers and documents. This Spring Boot application provides a robust backend for handling various functionalities, including database interactions and API services (Note: Project is outdated. Dependencies need to be reconfigured in order for the application to run again).

## Features

- **Spring Boot Framework**: Utilizes Spring Boot for rapid development and deployment.
- **JPA ORM**: Manages database operations using Java Persistence API (JPA).
- **Database Support**: Integrates with MySQL and Microsoft SQL Server databases.
- **Firebase Integration**: Provides Firebase admin functionalities for authentication and data management.
- **PDF Generation**: Uses iTextPDF for creating and manipulating PDF documents.
- **Secure Communications**: Incorporates Bouncy Castle for cryptographic operations.

## Getting Started

### Prerequisites

- **Java 11**: Ensure you have JDK 11 installed on your machine.
- **Maven**: You need Apache Maven to manage the project's dependencies and build process. Install Maven from [Apache Maven](https://maven.apache.org/).

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/saiedoc/MedicalCenterBackendApp.git
   ```
2. Navigate to the project directory:
   ```bash
   cd MedicalCenterBackendApp
   ```
3. Install the necessary dependencies:
   ```bash
   mvn clean install
   ```

### Running the Application

To run the Spring Boot application, use:

```bash
mvn spring-boot:run
```

This will start the Spring Boot application on the default port (8080). You can configure the port and other settings in the `application.properties` file.

### Project Structure

- **src/main/java**: Contains the source code for the application.
- **src/main/resources**: Contains application configuration files and static resources.
- **src/test/java**: Contains unit and integration tests.
- **pom.xml**: Maven configuration file with project dependencies and build settings.

## Dependencies

The project is built using the following key libraries and frameworks:

- **[Spring Boot](https://spring.io/projects/spring-boot)**: For building the application.
- **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)**: For data access and ORM.
- **[MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)**: For MySQL database connectivity.
- **[Microsoft JDBC Driver for SQL Server](https://learn.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server)**: For SQL Server database connectivity.
- **[Firebase Admin SDK](https://firebase.google.com/docs/admin/setup)**: For Firebase services.
- **[iTextPDF](https://itextpdf.com/en/resources)**: For PDF generation.
- **[Bouncy Castle](https://www.bouncycastle.org/)**: For cryptographic operations.
- **[Lombok](https://projectlombok.org/)**: For reducing boilerplate code.

## Development

- **REST APIs**: Implemented for interaction between frontend and backend.
- **Singletons & OOP**: Utilized design patterns and object-oriented programming principles.
- **Database Architecture**: Includes MySQL and SQL Server database schemas.


## Author

**Saied Aussi**
**Eyad Al Sayed**
**Caesar Farah**
**Abd Al Raheem Khoulani**
**Salem Al Aushoor**

## License

This project is licensed under the ISC License.
