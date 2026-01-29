# Online Shopping – Microservices Architecture (Ongoing)

## Overview

This repository contains an **e-commerce backend system** designed using **modern microservices architecture**.

The focus is on **real-world backend concerns** such as:
- Service boundaries
- Resilience and fault tolerance
- Event-driven communication

---

## Tech Stack

- **Java:** 17  
- **Frameworks:** Spring Boot 3, Spring Cloud  
- **Architecture:** Microservices, REST APIs  
- **Messaging:** Apache Kafka  
- **Persistence:** Spring Data JPA / Hibernate  
- **Containerization:** Docker  
- **Database:** MySQL (RDBMS) & MongoDB (NoSQL)

---

## Current Architecture

- Product Service  
- Order Service  
- Inventory Service  
- API Gateway (centralized routing and entry point)  
- Resilience4j (Circuit Breaker, Retry)

---

## Key Design Highlights

- Clear service ownership and isolation  
- API Gateway–based routing and failure handling  
- Event-driven communication using Kafka (in progress)  
- Focus on **eventual consistency** and **fault tolerance**

---

## In Progress

- Enhanced Kafka workflows (retry topics, DLQ concepts)  
- Improved observability and centralized error handling  
- Additional hardening for production-grade scenarios  

---

> **Note**  
> This project is actively evolving and reflects hands-on exploration of **production-grade microservices patterns** using Java 17 and Spring Boot 3.
