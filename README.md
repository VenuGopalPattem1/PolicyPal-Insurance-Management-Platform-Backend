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
  - ## Tech Stack
----------------

- **Maven:**  
  A powerful project management tool that helps in building and managing Java-based projects. It simplifies dependency management, project structure, and builds process.

- **Database:**  
  **MySQL**  
  An open-source relational database management system. It provides reliable storage, efficient data retrieval, and is widely used for web applications.

- **Spring REST:**  
  A part of the Spring framework that simplifies the development of RESTful web services. It allows for easy integration of various HTTP methods and makes it straightforward to handle requests and responses.

- **Swagger:**  
  A framework for API documentation that provides a user-friendly interface for testing API endpoints. It generates interactive documentation and helps developers understand the API's capabilities.

- **Postman:**  
  An API client used for testing APIs by sending requests and analyzing responses. It allows developers to quickly prototype and test their endpoints during development.

- **JWT (JSON Web Tokens):**  
  A compact, URL-safe means of representing claims to be transferred between two parties. It allows for secure transmission of information and is used for authentication and authorization in your application.

- **OAuth2 Resource Server:**  
  A framework that provides a way to secure your API endpoints. It validates incoming JWT tokens to ensure that only authenticated users can access protected resources.

- **Spring Data JPA:**  
  Part of the Spring ecosystem that provides a simplified approach to data access. It offers powerful data manipulation capabilities, including dynamic table generation and object-relational mapping.

- **Spring Security:**  
  A comprehensive security framework for Java applications that provides authentication and authorization features. It allows for the implementation of complex security requirements in a declarative manner.

- **Java Mail Sender:**  
  A part of the Spring framework that simplifies email sending in Java applications. It allows for the dynamic composition and sending of emails to users, which is essential for notifying them about application status.

- **OpenPDF:**  
  A Java library for creating and editing PDF documents. It allows your application to generate PDF reports based on user data dynamically, facilitating easier communication of important information.

- **Scheduling:**  
  A feature of Spring that allows for the execution of tasks at specific times or intervals. It is useful for automating routine processes, such as sending reports or notifications.

- **Batch Processing & iText API:**  
  These technologies are used to process large volumes of data and create Excel sheets based on database information. They enhance performance and efficiency when generating reports or exporting data.

- **Eureka Server:**  
  A service registry for microservices that enables service registration and discovery. It allows your application to locate other services in a dynamic environment, ensuring better scalability and resilience.

- **API Gateway:**  
  Serves as a single entry point for all microservices, handling incoming requests and routing them to the appropriate service. It also provides a layer of security and can enforce policies, such as rate limiting and authentication.

- **Config Server:**  
  Centralizes the management of configuration properties for your microservices. It allows for externalized configuration, making it easier to manage and change configurations without redeploying services.

- **Admin Server:**  
  Provides a web-based interface for monitoring and managing all registered microservices. It allows you to view the status of services, manage their health, and access various metrics for performance monitoring.

- **Spring Cloud:**  
  A collection of tools for microservices architecture that helps with service discovery, configuration management, and circuit breakers. It simplifies building distributed systems.


## Conclusion
PolicyPal is designed to streamline the insurance registration process while ensuring that only eligible users can access health insurance plans. Its modular structure and use of microservices provide a robust, scalable solution for managing health insurance applications.
