# 🛡️ **PolicyPal - Insurance Management Application**
---------------------------------------------------------

## 📝 **Overview**
- **Description:**  
  PolicyPal is a health insurance management system where users can register and apply for available insurance plans.

- **Architecture:**  
  Developed using microservices architecture, the application consists of different modules, each handling specific services to ensure scalability and maintainability.

---

## 🌟 **Features**

- **⚙️ Modular Architecture:** Built with microservices to ensure scalability and ease of maintenance.
- **🔒 Secure Authentication:** Utilizes JWT and OAuth2 for secure user authentication and authorization.
- **📄 Interactive API Documentation:** Swagger integration for easy API exploration and testing.
- **💾 Dynamic Data Handling:** Uses Spring Data JPA for dynamic table generation and database interactions.
- **🌐 Centralized Configuration and Monitoring:** Implements Eureka Server, API Gateway, Config Server, and Admin Server for efficient service management.
- **⏰ Scheduled Tasks and Batch Processing:** Automates routine tasks and handles large data processing efficiently.
- **🛠️ Comprehensive Testing Tools:** Integrates Postman for endpoint testing.
- **📝 Dynamic Content Generation:** Generates PDFs and Excel sheets based on database data.
- **✉️ Email Notifications:** Sends dynamic emails to users using Java Mail Sender.

---

## 🏛️ **Modules**
1. **📝 Application Registration Module:**  
   Allows users to register and apply for health insurance policies using personal data such as Aadhar numbers. Eligible users are verified before proceeding with the application process.

2. **📊 Data Collection Module:**  
   After successful registration, users receive an Application Registration ID to generate a Case Number. This Case Number allows users to submit detailed information to verify their eligibility for specific policies.

3. **✅ Eligibility Determination Module:**  
   This module validates the collected user data against policy conditions. If all conditions are met, the user is deemed eligible for the selected health insurance policy.

4. **📨 Correspondence Module:**  
   Sends automated email notifications to users regarding policy approval or denial. Emails include PDF attachments generated dynamically based on the Eligibility Determination results. Multithreading is used for efficient email and report generation.

5. **💰 Benefit Issuance Module:**  
   For eligible users, an Excel sheet is generated containing the details of approved citizens. This data is then sent to banks for disbursement of benefits using batch processing.

6. **📈 Reports Module:**  
   Displays detailed reports on policy applications with various filtering options for admin users.

7. **🛠️ Admin Module:**  
   Handles plan creation, updating, and deletion, as well as user data management. Admins can activate or deactivate users and policies.

---

## ⚙️ **Additional Microservices**
- **🔗 Eureka Server:**  
  Manages service registration and discovery across all microservices.
  
- **🌐 API Gateway:**  
  Acts as a single entry point for all microservices, managing routing and request authentication using JWT.
  
- **📦 Config Server:**  
  Centralizes configuration management across all microservices using a Git repository to store configuration properties.
  
- **📊 Admin Server:**  
  Provides a centralized dashboard for monitoring all microservices and accessing actuator endpoints.

- **🔑 Auth Server:**  
  Manages user authentication and issues JWT tokens for secure communication between microservices.

---

## 🛠️ **Tech Stack**

### 🔨 **Build Tool**
- **Maven:** Manages project structure and dependencies.

### 💻 **Backend**
- **Java:** Core programming language.
- **Spring Boot:** Framework for building microservices.
- **Spring REST:** Facilitates creating RESTful endpoints.
- **Spring Security:** Implements authentication and authorization features.
- **Spring Data JPA:** Handles database interactions and dynamic table generation.
- **Spring Batch:** Manages batch processing tasks for large-scale data operations.
- **Spring Scheduling:** Schedules tasks to run at specific times.

### 🔐 **Security**
- **JWT (JSON Web Tokens):** Creates tokens for authenticated users.
- **OAuth2 Resource Server:** Validates JWT tokens for every incoming request.

### 🗄️ **Database**
- **MySQL:** Relational database management system.

### 📜 **API Documentation & Testing**
- **Swagger:** Provides interactive API documentation and endpoint testing.
- **Postman:** Used for manual endpoint testing.

### 🔧 **Other Tools & Libraries**
- **Java Mail Sender:** Sends dynamic emails to users.
- **iText API:** Generates PDFs based on dynamic data.
- **OpenPDF:** Alternative library for PDF creation.
- **Apache POI:** Creates Excel sheets from database data for batch processing tasks.

---

## ⚙️ **Microservices Management**
- **🔗 Eureka Server:** Manages service registration and discovery.
- **🌐 API Gateway:** Acts as a centralized entry point for requests and enforces JWT-based security.
- **📦 Config Server:** Centralizes configuration across services using a common Git repository.
- **📊 Admin Server:** Monitors all microservices and their health status.

---

## 🏁 **Getting Started**

### 🛠️ **Prerequisites**
- JDK 11 or higher
- Maven
- MySQL

### 🚀 **Installation**
1. Clone the repository:
   ```bash
   git clone https://github.com/VenuGopalPattem1/PolicyPal-Insurance-SpringBoot-Backend.git



## Architecture Diagram
```mermaid
graph LR
    A[Client Layer] -->|REST Calls| B[API Gateway]
    B -->|Service Discovery| C[Eureka Server]
    B -->|Config Management| D[Config Server]
    B -->|Monitoring| E[Admin Server]
    B -->|Authentication| F[Auth Server]
    
    C --> G[Application Registration Service]
    C --> H[Data Collection Service]
    C --> I[Eligibility Determination Service]
    C --> J[Correspondence Service]
    C --> K[Benefit Issuance Service]
    C --> L[Reports Service]
    C --> M[Admin Service]

    G -->|CRUD Operations| N[MySQL Database]
    H -->|CRUD Operations| N
    I -->|CRUD Operations| N
    J -->|CRUD Operations| N
    K -->|CRUD Operations| N
    L -->|CRUD Operations| N
    M -->|CRUD Operations| N
    
    subgraph "Support Tools"
        O[Swagger]
        P[Postman]
        Q[Java Mail Sender]
        R[OpenPDF]
        S[Apache POI]
    end
    
    B -->|API Docs| O
    B -->|API Testing| P
    G -->|Email Notifications| Q
    I -->|PDF Generation| R
    K -->|Excel Generation| S
