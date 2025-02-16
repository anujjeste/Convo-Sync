# Chat Application

## Overview

This is a real-time chat application built using a **Spring Boot backend**, **PostgreSQL database**, and a **React (Vite) frontend**. The application allows users to send and receive messages via WebSockets. Future updates will include chatbot functionality. This project is work in progress.

## **Technologies Used**

### **Backend:**

- Spring Boot (v3.4.2)
- Spring Security
- WebSockets (STOMP protocol)
- PostgreSQL (v17.3)
- Hibernate (JPA)
- Maven (build tool)

### **Frontend:**

- React (Vite-based project)
- @stomp/stompjs (WebSocket handling)
- SockJS-client
- Tailwind CSS (for styling)

### **DevOps & Deployment:**

- Docker & Docker Compose
- Nginx (serving frontend)
- Git & GitHub for version control

---

## **Setup & Installation**

### **Pre-requisites:**

Ensure you have the following installed:

- Docker & Docker Compose
- Node.js (v18+ for frontend development)
- Java 17 (for Spring Boot backend)
- PostgreSQL (if running locally without Docker)

### **1. Clone the Repository**

```bash
 git clone https://github.com/anujjeste/Java-chat-bot.git
 cd Java-chat-bot
```

### **2. Build & Run with Docker Compose**

```bash
 docker-compose up --build
```

This will start:

- PostgreSQL database
- Backend service (Spring Boot)
- Frontend service (React)

> Ensure ports `3000`, `8080`, and `5432` are free.

### **3. Running Without Docker**

#### **Run PostgreSQL (Manually)**

If you prefer running PostgreSQL manually, create a database:

```sql
 CREATE DATABASE chatapp_db;
 CREATE USER chatapp_admin WITH PASSWORD 'chatapp_password';
 ALTER ROLE chatapp_admin SET client_encoding TO 'utf8';
 ALTER ROLE chatapp_admin SET default_transaction_isolation TO 'read committed';
 ALTER ROLE chatapp_admin SET timezone TO 'UTC';
 GRANT ALL PRIVILEGES ON DATABASE chatapp_db TO chatapp_admin;
```

#### **Start Backend (Spring Boot)**

```bash
 cd backend
 mvn clean package
 java -jar target/demo-0.0.1-SNAPSHOT.jar
```

#### **Start Frontend (React-Vite)**

```bash
 cd frontend
 npm install
 npm run dev
```

---

## **Project Structure**

```
Java-chat-bot/
│── backend/                   # Spring Boot Backend
│   ├── src/main/java/
│   │   ├── com/example/chatbot/config/   # WebSocket & Security Configurations
│   │   ├── com/example/chatbot/controller/ # API Controllers
│   │   ├── com/example/chatbot/service/   # Business Logic
│   │   ├── com/example/chatbot/model/     # Entities & DTOs
│   │   ├── com/example/chatbot/repository/ # JPA Repository
│   ├── application.properties  # Database and App Configurations
│   ├── pom.xml                 # Maven Build File
│
│── frontend/                  # React Frontend
│   ├── src/
│   │   ├── components/
│   │   ├── hooks/useChatWebSocket.js  # WebSocket Hook
│   │   ├── App.jsx
│   │   ├── main.jsx
│   ├── package.json            # Node Dependencies
│   ├── vite.config.js          # Vite Configuration
│
│── docker-compose.yml          # Docker Orchestration
│── Dockerfile (Backend)
│── chatbot-ui/Dockerfile (Frontend)
```

---

## **API Endpoints**

| Method | Endpoint     | Description         |
| ------ | ------------ | ------------------- |
| GET    | `/messages`  | Fetch all messages  |
| POST   | `/messages`  | Send a new message  |
| GET    | `/chat/{id}` | Get chat by ID      |
| POST   | `/register`  | Register a new user |

---

## **Common Issues & Fixes**

1. **Backend fails due to PostgreSQL authentication**

   - Ensure PostgreSQL is running and credentials match.
   - Run `docker-compose down -v` to clear old volumes.

2. **Frontend WebSocket connection issue**

   - Ensure `VITE_WS_URL` is correctly pointing to the backend.
   - Check browser console logs for WebSocket errors.

3. **Vite build failure (Missing dependencies)**

   - Run `rm -rf node_modules package-lock.json && npm install` inside `frontend/`

---

## **Future Updates**

- Chatbot service integration (AI-powered responses)
- User authentication & JWT security
- Dockerized production deployment

---

## **Contributing**

Pull requests are welcome! Open an issue first to discuss major changes.

**Author:** Anuj Jeste\
**GitHub:** [anujjeste](https://github.com/anujjeste)


