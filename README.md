# Foro Hub (Alura Latam)

**Foro Hub** es un proyecto de ejemplo construido en Spring Boot para la presentación del reto de Alura Latam.

Se trata de una API REST que permite crear y gestionar usuarios, publicaciones y comentarios con autenticación JWT y un modelo de roles básico (USER/ADMIN).

---

## ✅ Tecnologías utilizadas

- Java 17
- Spring Boot (Web, Data JPA, Security)
- MySQL (base de datos relacional)
- JWT para autenticación sin estado
- Maven para gestión de dependencias y construcción

---

## 🚀 Características implementadas

### ✅ Autenticación y autorización
- Registro de usuarios (rol `USER` por defecto)
- Login con JWT y validación de token en cada petición
- Inicialización de datos al arrancar (roles `USER` y `ADMIN`, y dos cuentas preconfiguradas)

### ✅ Gestión de publicaciones (Posts)
- Crear publicación (requiere `authorId` válido)
- Listar todas las publicaciones
- Actualizar publicación por ID
- Eliminar publicación por ID

### ✅ Gestión de comentarios
- Crear comentario asociado a un post y un autor
- Listar comentarios por post
- Actualizar comentario por ID
- Eliminar comentario por ID

---

## 🧩 Estructura del proyecto (Clean Architecture)

El proyecto sigue un enfoque inspirado en **Clean Architecture / Hexagonal Architecture**, segregando claramente las capas para mantener el dominio independiente de detalles de infraestructura.

- `application/` - Casos de uso / servicios (lógica de negocio)
- `domain/` - Entidades de dominio y puertos (interfaces de repositorio)
- `infrastructure/` - Detalles de implementación (persistencia JPA, configuración, seguridad)
- `presentation/` - Adaptadores de entrada (controladores REST, DTOs)

### 📁 Organización de carpetas

```
src/main/java/com/foro_hub/foro_hub/
  ├─ application/          # Casos de uso / servicios
  │    └─ service/
  ├─ domain/               # Entidades y puertos (interfaces)
  │    ├─ model/
  │    └─ repository/
  ├─ infrastructure/       # Implementaciones: JPA, seguridad, configuración
  │    ├─ config/
  │    ├─ persistence/
  │    └─ security/
  └─ presentation/         # Controladores REST + DTOs
       ├─ controller/
       └─ dto/
```

---

## 🏁 Requisitos previos

1. Tener Java 17 instalado
2. Tener un servidor MySQL corriendo (local o remoto)
3. Crear la base de datos `foro_hub` (por defecto)

> 📌 *Nota:* la configuración de conexión está en `src/main/resources/application.properties`.

---

## ▶️ Cómo ejecutar el proyecto

```bash
mvn spring-boot:run
```

La API quedará disponible en: `http://localhost:8080`

---

## 🔐 Usuarios de prueba (seed)

| Usuario | Email | Password | Rol |
|--------|-------|----------|-----|
| admin  | admin@foro.com | admin123 | ADMIN |
| usuario | user@foro.com | user123 | USER |

---

## 🧪 Endpoints principales (API REST)

### Autenticación
- `POST /api/auth/register` – Registrar usuario (cuerpo JSON: username, email, password)
- `POST /api/auth/login` – Iniciar sesión y obtener token JWT (cuerpo JSON: email, password)

### Posts
> Requieren header `Authorization: Bearer <token>`

- `POST /api/posts/create` – Crear post (JSON: title, content, authorId)
- `GET /api/posts/all` – Listar todos los posts
- `PUT /api/posts/{id}` – Actualizar post por ID
- `DELETE /api/posts/{id}` – Eliminar post por ID

### Comentarios
> Requieren header `Authorization: Bearer <token>`

- `POST /api/comments/create` – Crear comentario (JSON: content, authorId, postId)
- `GET /api/comments/post/{postId}` – Listar comentarios de un post
- `PUT /api/comments/{id}` – Actualizar comentario por ID
- `DELETE /api/comments/{id}` – Eliminar comentario por ID

---

## 🧰 Ejemplos rápidos (curl)

### Login y obtener token

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"user@foro.com","password":"user123"}'
```

### Crear un post (con token)

```bash
curl -X POST http://localhost:8080/api/posts/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN>" \
  -d '{"title":"Mi primer post","content":"Contenido de ejemplo","authorId":2}'
```

---

## ✅ Buenas prácticas demostradas

- Separación por capas (Controller / Service / Repository / Domain)
- Uso de DTOs para desacoplar capa de presentación
- Seguridad básica con JWT y Spring Security
- Persistencia con Spring Data JPA y MySQL
- Inicialización de datos para facilitar pruebas

---

## 🧠 Próximos pasos sugeridos (mejoras)

- Validación más robusta (Bean Validation / DTOs)
- Roles y permisos más detallados (ADMIN vs USER)
- Paginación y filtros en listados
- Documentación OpenAPI / Swagger
- Frontend simple (React/Vue) consumiendo la API

---

## 📌 Notas del desafío

Este proyecto fue desarrollado como respuesta al reto de **Alura Latam** para construir un foro básico con autenticación y operaciones CRUD.
