# Library Management System

A basic Library Management System built using:

- Java 17
- Spring Boot
- MongoDB
- Maven
- Docker
- REST APIs
---

##  Features

### 👤 User
- Signup
- Login
- Borrow book
- Return book
- View borrowed books

###  Admin
- Add books

### Book Rules
- Each book can be borrowed only once
- Some books auto-return after expiry time
- Some books auto-return at 10PM (Library Read Policy)

---

## Project Structure
src/main/java/com/library
│
├── controller
├── service
├── repository
├── model
├── security
├── scheduler

---

##  Tech Stack

- Spring Boot
- Spring Security (JWT)
- MongoDB
- Docker Compose
- Prometheus + Grafana (Observability)
- gRPC

---

##  Run with Docker

```bash
docker compose up --build

Access:

App → http://localhost:8080
MongoDB → localhost:27017
Prometheus → http://localhost:9090
Grafana → http://localhost:3000
