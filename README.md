# Spring AI + PGVector RAG Application

This project is a **Spring Boot Retrieval-Augmented Generation (RAG)** system using **Spring AI**, **PGVector**, and **OpenAI**.  
It allows dynamic prompt management, document ingestion (e.g., Indian Constitution), and semantic search with vector embeddings.

---

## Features

- Vector store with **PGVector + HNSW index**
- **Spring AI integration** with OpenAI LLMs
- **Multi-Prompt support** via `prompts.json`
- PDF document processing using `PagePdfDocumentReader`
- REST API for document-based Q&A

---

## Technology Stack

| Technology            | Version   |
|----------------------|-----------|
| Spring Boot           | 3.5.3     |
| Spring AI             | 1.0.0-M3  |
| PostgreSQL + PGVector | pg16      |
| Java                  | 21        |
| Docker / Docker Compose | latest |

---

## Setup Guide

### 1️⃣ Prerequisites

- Java 21
- Docker + Docker Compose
- Maven 3.9+
- OpenAI API Key (export as `OPENAI_API_KEY`)

---

### 2️⃣ Clone and Build

```bash
git clone git@github.com:zeeshanzsh/spring-ai-pgVector.git
cd Spring-PGVector
mvn clean install
````
Run Postgres + PGVector
```
docker-compose up -d
docker exec -it <container_id> psql -U myuser -d mydatabase
```
### 3️⃣ API Usage

### 🟢 Ask a Question API

**Endpoint:**
POST /v2/ask
Content-Type: application/json

**Request Body Example:**

```json
{
  "promptName": "IndianConstitutionPrompt",
  "input": "What is Article 21?"
}
```
Response Example:
```
{
  "content": "Article 21 guarantees the right to life and personal liberty..."
}
```
