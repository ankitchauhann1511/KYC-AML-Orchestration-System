# KYC/AML Orchestration System

> Enterprise-grade KYC/AML Orchestration System built using **Java, Spring Boot, MySQL, Apache Kafka, and REST APIs**.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8-blue)
![Kafka](https://img.shields.io/badge/Apache-Kafka-black)
![Maven](https://img.shields.io/badge/Maven-Build-red)

---

# Project Overview

The **KYC/AML Orchestration System** automates the customer onboarding and compliance process used in financial institutions.

The application provides an end-to-end workflow for:

- Customer Onboarding
- KYC Verification
- AML Screening
- Risk Scoring
- Case Management
- Regulatory Dashboard
- Kafka Event Processing

The system follows an event-driven architecture using **Apache Kafka** while storing data in **MySQL**.

---

# Features

## Customer Management

- Customer Registration
- UUID Generation
- Customer Status Tracking

---

## KYC Verification

- PAN Validation
- Aadhaar Validation
- KYC Status API
- Automatic Customer Status Update
- Kafka Event Publishing

---

## AML Screening

- Sanctions Screening
- PEP Screening
- AML Risk Classification
- AML Database Storage

---

## Risk Scoring

Risk Score Calculation based on

- Customer KYC Status
- AML Risk
- Country Risk

Risk Levels

- LOW
- MEDIUM
- HIGH

---

## Case Management

- Automatic Case Creation
- Compliance Officer Assignment
- Case Status Update

---

## Regulatory Dashboard

Dashboard provides

- Total Customers
- Verified KYC
- AML Screened Customers
- High Risk Customers
- Medium Risk Customers
- Low Risk Customers
- Open Cases
- Closed Cases

---

# Technology Stack

| Technology | Version |
|------------|----------|
| Java | 17 |
| Spring Boot | 3.x |
| Spring Data JPA | Latest |
| Hibernate | ORM |
| MySQL | 8.x |
| Apache Kafka | 4.x |
| Maven | Latest |
| Lombok | Latest |
| Postman | API Testing |
| Git | Version Control |
| GitHub | Repository |

---

# Project Architecture

```
                        Client (Postman)

                               │
                               ▼

                    Spring Boot REST APIs

        ┌─────────────┬──────────────┬──────────────┐
        │             │              │              │
        ▼             ▼              ▼              ▼

 Customer       KYC Module      AML Module    Dashboard

        │             │              │
        └─────────────┴──────────────┘
                      │
                      ▼

              Risk Scoring Engine

                      │
                      ▼

             Case Management Module

                      │
                      ▼

               Apache Kafka Topics

                      │
                      ▼

                 MySQL Database
```

---

# Database Schema

Database

```
kycaml_db
```

Tables

```
customers

kyc_documents

aml_screening

risk_scores

case_record
```

---

# Kafka Topics

```
customer-onboarded-topic

kyc-verified-topic

aml-screening-topic

risk-score-topic

case-created-topic
```

---

# REST APIs

## Customer APIs

| Method | Endpoint |
|---------|----------|
| POST | /api/customers/onboard |

---

## KYC APIs

| Method | Endpoint |
|---------|----------|
| POST | /api/kyc/upload |
| GET | /api/kyc/status/{customerId} |

---

## AML APIs

| Method | Endpoint |
|---------|----------|
| POST | /api/aml/screen |
| GET | /api/aml/{customerId} |

---

## Risk APIs

| Method | Endpoint |
|---------|----------|
| POST | /api/risk/calculate |
| GET | /api/risk/{customerId} |

---

## Case APIs

| Method | Endpoint |
|---------|----------|
| POST | /api/cases/create |
| GET | /api/cases/{customerId} |
| PUT | /api/cases/{caseId}/status |

---

## Dashboard APIs

| Method | Endpoint |
|---------|----------|
| GET | /api/dashboard/summary |
| GET | /api/dashboard/customers |
| GET | /api/dashboard/kyc |
| GET | /api/dashboard/aml |
| GET | /api/dashboard/risk |
| GET | /api/dashboard/cases |

---

# Sample API

## Customer Onboarding

### Request

```json
{
  "fullName":"Rahul Sharma",
  "email":"rahul@gmail.com",
  "phone":"9876543210",
  "panNumber":"ABCDE1234F",
  "aadhaarNumber":"123456789012"
}
```

### Response

```json
{
  "customerId":"UUID",
  "fullName":"Rahul Sharma",
  "status":"PENDING_KYC"
}
```

---

# Project Workflow

```
Customer Onboarding
        │
        ▼
KYC Verification
        │
        ▼
AML Screening
        │
        ▼
Risk Scoring
        │
        ▼
Case Management
        │
        ▼
Dashboard Reporting
        │
        ▼
Kafka Event Processing
        │
        ▼
MySQL Database
```

---

# Project Structure

```
KYC-AML-Orchestration-System

├── src
├── database
│     └── kycaml_db.sql
├── postman
│     └── KYC_AML_Orchestration.postman_collection.json
├── screenshots
├── pom.xml
├── README.md
└── mvnw
```



# How to Run

## Clone Repository

```bash
git clone https://github.com/ankitchauhann1511/KYC-AML-Orchestration-System.git
```

## Configure MySQL

Create database

```sql
CREATE DATABASE kycaml_db;
```

Import

```
kycaml_db.sql
```



## Configure application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/kycaml_db

spring.datasource.username=root

spring.datasource.password=your_password
```



## Start Kafka

```bash
kafka-storage.bat format

kafka-server-start.bat config/server.properties
```



## Run Application

```bash
mvn spring-boot:run
```



# Testing

The project has been tested using

- Postman
- MySQL Workbench
- Apache Kafka
- Eclipse IDE

Validation includes

- Customer Onboarding
- PAN Verification
- Aadhaar Verification
- AML Screening
- Risk Scoring
- Case Creation
- Dashboard APIs
- Kafka Producer & Consumer
- Database Validation



# Future Enhancements

- JWT Authentication
- Spring Security
- Swagger/OpenAPI Documentation
- Docker
- Kubernetes Deployment
- Redis Cache
- Email/SMS Notifications
- CI/CD Pipeline
- Unit & Integration Testing

---

# Author

Ankit Chauhan
Java Backend Developer

GitHub:
https://github.com/ankitchauhann1511

---

# Acknowledgement

Developed as part of the **Java Backend Internship Program** at **Zetheta Algorithms Pvt. Ltd.**
