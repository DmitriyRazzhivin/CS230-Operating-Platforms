# Draw It or Lose It: Cross-Platform Systems Architecture & RESTful Backend

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Dropwizard](https://img.shields.io/badge/Dropwizard-2C3E50?style=for-the-badge&logo=apache&logoColor=white)](https://www.dropwizard.io/)
[![Linux](https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black)](https://www.kernel.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

An enterprise-grade systems architecture and software design case study transitioning an isolated mobile game (*Draw It or Lose It*) into a distributed, multi-tenant web application. Developed as the primary portfolio artifact for **CS 230: Operating Platforms** at Southern New Hampshire University.

---

## 📌 Executive Summary

*The Gaming Room* required modernizing its standalone Android game, *Draw It or Lose It*, to expand its market reach across traditional desktop operating systems (Windows, macOS, Linux) and modern mobile platforms (iOS and Android). 

The legacy implementation lacked centralized state synchronization, exhibited platform lock-in, and was vulnerable to client-side manipulation. This project delivers a decoupled, multi-tier client-server architectural specification backed by a Java Dropwizard RESTful backend, addressing cross-platform compatibility, kernel-level OS interactions, storage durability, memory optimization, and zero-trust security.

---

## 🏛 Architectural Highlights & Design Patterns

### 1. Object-Oriented Domain Architecture
The domain model enforces strong object-oriented design principles to maximize maintainability and eliminate redundant state representation:
* **Inheritance & Abstraction:** A foundational abstract `Entity` encapsulates shared identity fields (`id`, `name`) and uniform accessors, extended by `Game`, `Team`, and `Player`.
* **Polymorphism:** Runtime polymorphism via dynamic method dispatch allows specialized implementations of `toString()` across subclasses, providing structured textual inspection without explicit type checking.
* **Encapsulation & Aggregation:** Strict whole-part relationships are maintained. A centralized session manager aggregates zero or more `Game` instances, which in turn aggregate `Team` collections and `Player` rosters with private visibility.

### 2. Software Design Patterns
* **Singleton Pattern:** Enforced via `GameService` using a private static instance, a private constructor, and a thread-safe `getInstance()` accessor to guarantee a single authoritative memory registry for active games.
* **Iterator Pattern:** Integrated into entity creation routines (`addGame`, `addTeam`, `addPlayer`) to traverse active in-memory collections and validate unique naming constraints before instantiation.

### 3. REST Security & Authorization Pipeline
The Dropwizard service isolates authentication and role evaluation from core business logic:
* **Principal Identification:** The `GameUser` model implements `java.security.Principal`, encapsulating authenticated credentials and an immutable `Set<String>` of assigned
