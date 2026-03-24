# Text Editor API

## Overview

This project is a backend API for a text editor built with **Java and Spring Boot**.  
It exposes REST endpoints for creating and editing documents, persists data in **PostgreSQL**, and uses **Flyway** for database migrations.

The project focuses on backend design concepts such as clean architecture, persistence, testing, and containerized development. It was developed as a portfolio project to strengthen practical experience with modern Java backend technologies.

---

## Motivation

The goal of this project was to build a structured backend application that demonstrates backend engineering practices commonly used in production systems.

The project focuses on:

- Designing RESTful APIs
- Structuring a maintainable backend architecture
- Persisting application state with relational databases
- Managing schema evolution with database migrations
- Writing automated tests for both core logic and integration scenarios
- Running services in a containerized environment

It also serves as a foundation for future expansion with a frontend client.

---

## Features

- Create and retrieve documents through a REST API
- Modify document content through editing operations
- Persistent storage using PostgreSQL
- Database schema versioning with Flyway migrations
- Clean separation between API, service, and persistence layers
- Unit tests for core logic
- Integration tests for API and database interactions
- Docker-based development environment

---

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA / Hibernate

### Database
- PostgreSQL
- Flyway

### Testing
- JUnit
- Testcontainers

### Infrastructure
- Docker
- Docker Compose
- Gradle

---

## Architecture Overview

The project follows a layered backend structure that separates responsibilities between the API layer, business logic, and persistence.

### API Layer

The `api` package contains REST controllers responsible for handling HTTP requests and mapping them to service operations.

Responsibilities include:

- Request validation
- HTTP response handling
- Translating HTTP requests into calls to the application's business logic.

### Service Layer

The `service` package contains the application’s business logic.  
Services coordinate operations between the domain model and the persistence layer.

Responsibilities include:

- Managing the creation, retrieval, modification, and deletion of documents
- Coordinating editing operations
- Persisting document changes

### Persistence Layer

The `persistence` package handles database interaction using **Spring Data JPA**.

Responsibilities include:

- Entity definitions
- Repository interfaces
- Mapping domain state to database tables

### Editing Model

The core editing logic is implemented through a command-based approach.

The `commands` package contains operations representing text editing actions. These commands modify the internal state of the text buffer while keeping editing logic encapsulated and testable.

The `TextBuffer` class acts as the core editing model responsible for managing document content and applying editing operations.

---

## Project Structure
