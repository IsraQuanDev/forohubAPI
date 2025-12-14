# ForoHub - API REST

ForoHub es una API REST desarrollada con **Spring Boot** que permite la gestión de tópicos de un foro.
Incluye autenticación segura con **JWT**, persistencia en base de datos relacional y arquitectura en capas.

Este proyecto fue desarrollado como parte de un desafío técnico, aplicando buenas prácticas de desarrollo backend.

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot 3
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA (Hibernate)
- MySQL 8
- Maven
- Lombok
- Swagger / OpenAPI

---

## 🧱 Arquitectura

El proyecto sigue una arquitectura en capas:

- **Controller** → Manejo de endpoints REST
- **Service** → Lógica de negocio
- **Repository** → Acceso a datos (JPA)
- **DTO** → Transferencia de datos
- **Entity** → Modelo de base de datos
- **Security** → JWT, filtros y configuración
- **Exception** → Manejo global de errores

---

## 🔐 Autenticación

La API utiliza autenticación basada en **JWT**.

### Login
```http
Body

POST /auth/login
{
  "username": "admin",
  "password": "admin123"
}

Respuesta
{
  "token": "Bearer eyJhbGciOiJIUzI1NiJ9..."
}
El token debe enviarse en cada solicitud protegida:
Authorization: Bearer <TOKEN>
