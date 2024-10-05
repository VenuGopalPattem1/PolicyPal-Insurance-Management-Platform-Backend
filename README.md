# PolicyPal - Insurance Management Application
---------------------------------------------------------

## Overview
- **Description:**  
  PolicyPal is a health insurance management system where users can register for available insurance plans.
  
- **Architecture:**  
  Developed using microservices architecture, the application consists of different modules, each handling specific services.
  
- **Modules:**
  - Application Registration Module
  - Data Collection Module
  - Eligibility Determination Module
  - Correspondence Module
  - Benefit Issuance Module
  - Reports Module
  - Admin Module
  
  Additionally, it includes supporting microservices such as Eureka Server, Config Server, Admin Server, API Gateway, JWT Auth Service, and a Mock Project.
  
- **Access:**  
  This is an intranet-based application. Users must visit the nearest Meesava or any other office to apply for the insurance policies.

## Modules

### Admin Module
- **Responsibilities:**  
  - Plan creation, updating, and deletion.
  - User data management (creation, updating, and deletion).
  - Change the status of users and plans (active/inactive).

### Application Registration Module
- **Functionality:**  
  - Users provide their data to apply for policies.
  - Determines user eligibility based on their Aadhar number.
  - Only users belonging to the specific state government can apply.

### Data Collection Module
- **Functionality:**  
  - After successful registration, users receive an Application Registration ID to generate a Case Number.
  - Users submit detailed information to verify eligibility for policies.
  - Allows users to review and cross-check their submitted details based on the Case Number.

### Eligibility Determination Module
- **Functionality:**  
  - Checks user eligibility based on the data collected in the Data Collection Module.
  - Logic is implemented to validate user data against policy conditions.
  - If all conditions are met, users are deemed eligible for the policy.

### Correspondence Module
- **Functionality:**  
  - Sends emails with PDF attachments to users based on approval or denial from the Eligibility Determination Module.
  - Utilizes multithreading for efficient email and report generation.
  - Implements the Executable framework for multi-threading.

### Benefit Issuance Module
- **Functionality:**  
  - Creates an Excel sheet with data of citizens approved for policies.
  - Utilizes batch processing to generate the Excel sheet.
  - The sheet is sent to banks for disbursement of benefits to citizens.

### Reports Module
- **Functionality:**  
  - Displays all applications for the plans with filtering options.

## Microservice Projects

### Eureka Server
- **Description:**  
  Created using Netflix Eureka Server dependency, it serves as a registration and discovery server for microservices.

### Config Server
- **Description:**  
  Utilizes Spring Cloud Config Server to manage common application properties in a centralized GitLab repository.

### Admin Server
- **Description:**  
  Created using Spring Cloud Admin Server dependency to monitor and access the actuators of all microservices.

### API Gateway
- **Description:**  
  Acts as a single entry and exit point for all microservices.
  - Configures URLs for accessing various services.
  - Implements JWT Token security for request authentication.

### Auth Server
- **Description:**  
  Handles user authentication.
  - Generates a JWT token upon successful authentication.
  - Provides user registration functionality.
  - 
## Features

- **Modular Architecture**: Built with microservices to ensure scalability and ease of maintenance.
- **Secure Authentication**: Utilizes JWT and OAuth2 for secure user authentication and authorization.
- **Interactive API Documentation**: Swagger integration for easy API exploration and testing.
- **Dynamic Data Handling**: Uses Spring Data JPA for dynamic table generation and database interactions.
- **Centralized Configuration and Monitoring**: Implements Eureka Server, API Gateway, Config Server, and Admin Server for efficient service management.
- **Scheduled Tasks and Batch Processing**: Automates routine tasks and handles large data processing efficiently.
- **Comprehensive Testing Tools**: Integrates Postman for endpoint testing.
- **Dynamic Content Generation**: Generates PDFs and Excel sheets based on database data.
- **Email Notifications**: Sends dynamic emails to users using Java Mail Sender.

## Tech Stack

### Build Tool
- **Maven**: Manages project structure and dependencies.

### Backend
- **Java**: Core programming language.
- **Spring Boot**: Framework for building microservices.
- **Spring REST**: Facilitates creating RESTful endpoints.
- **Spring Security**: Implements security features.
- **Spring Data JPA**: Handles database interactions and dynamic table generation.
- **Spring Batch**: Manages batch processing tasks.
- **Spring Scheduling**: Schedules tasks to run at specific times.

### Security
- **JWT (JSON Web Tokens)**: Creates tokens for authenticated users.
- **OAuth2 Resource Server**: Validates JWT tokens for each request.

### Database
- **MySQL**: Relational database management system.

### API Documentation & Testing
- **Swagger**: Provides interactive API documentation and endpoint testing.
- **Postman**: Used for endpoint testing.

### Other Tools & Libraries
- **Java Mail Sender**: Sends dynamic emails to users.
- **iText API**: Generates PDFs based on dynamic data.
- **OpenPDF**: Alternative for PDF creation.
- **Apache POI**: Creates Excel sheets from database data.

### Microservices Management
- **Eureka Server**: Service registration and discovery.
- **API Gateway**: Acts as a single entry point for all endpoints.
- **Config Server**: Centralizes common properties in `application.properties`.
- **Admin Server**: Centralized monitoring of all microservices.


## Conclusion
PolicyPal is designed to streamline the insurance registration process while ensuring that only eligible users can access health insurance plans. Its modular structure and use of microservices provide a robust, scalable solution for managing health insurance applications.
