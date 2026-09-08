# backend-nttdata

Backend desarrollado en Java con Spring Boot y Spring Data JPA para la consulta de personas almacenadas en una base de datos MySQL.

Actualmente la API permite buscar una persona mediante su RUT.

## Tecnologías

- Java 25
- Spring Boot 4.0.8
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL
- MySQL Connector/J
- Lombok
- Maven
- Maven Wrapper

## Estructura del proyecto

```text
backend-nttdata/
├── src/
│   ├── main/
│   │   ├── java/cl/nttdata/personas/
│   │   │   ├── PersonasApplication.java
│   │   │   ├── controller/
│   │   │   │   └── PersonaController.java
│   │   │   ├── model/
│   │   │   │   └── Persona.java
│   │   │   ├── repository/
│   │   │   │   └── PersonaRepository.java
│   │   │   └── service/
│   │   │       └── PersonaService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application.yaml
│   └── test/
│       └── java/cl/nttdata/personas/
│           └── PersonasApplicationTests.java
├── .gitignore
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## Arquitectura

El proyecto utiliza una estructura simple por capas:

```text
HTTP Request
     ↓
Controller
     ↓
Service
     ↓
Repository
     ↓
Spring Data JPA / Hibernate
     ↓
MySQL
```

### Model

`Persona` representa la entidad persistida en la tabla `personas`.

Actualmente contiene los siguientes atributos:

- `rut`
- `nombre`
- `apellido`
- `fechaNas`

El RUT corresponde a la clave primaria de la entidad.

### Repository

`PersonaRepository` extiende:

```java
JpaRepository<Persona, String>
```

Esto permite utilizar las operaciones proporcionadas por Spring Data JPA para acceder a los datos de `Persona`.

### Service

`PersonaService` contiene la lógica utilizada actualmente para consultar una persona mediante su RUT.

### Controller

`PersonaController` expone el endpoint HTTP de consulta de personas.

## Base de datos

El proyecto utiliza MySQL y espera una base de datos llamada:

```text
personas_db
```

La configuración actual utiliza por defecto:

```text
Host: localhost
Puerto: 3306
Usuario: root
Base de datos: personas_db
```

### Crear la base de datos

Antes de ejecutar la aplicación se debe crear la base de datos en MySQL:

```sql
CREATE DATABASE personas_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

Durante el desarrollo, Hibernate está configurado con:

```properties
spring.jpa.hibernate.ddl-auto=update
```

Esto permite que Hibernate cree o actualice la tabla correspondiente a la entidad `Persona` al iniciar la aplicación.

## Configuración de MySQL

La configuración de conexión se encuentra en:

```text
src/main/resources/application.properties
```

Actualmente contiene:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/personas_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Antes de ejecutar el proyecto localmente, cada integrante debe configurar la contraseña correspondiente a su instalación de MySQL.

> **Importante:** no se deben subir credenciales reales al repositorio. Si se modifica localmente `application.properties` para ejecutar el proyecto, se debe evitar incluir la contraseña personal en un commit.

## Ejecutar el proyecto

### Requisitos

- Java 25
- MySQL
- Git

No es necesario instalar Maven globalmente, ya que el proyecto incluye Maven Wrapper.

### 1. Clonar el repositorio

```bash
git clone https://github.com/SusanaFa/backend-nttdata.git
cd backend-nttdata
```

### 2. Crear la base de datos

Ejecutar en MySQL Workbench o desde la consola de MySQL:

```sql
CREATE DATABASE personas_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

### 3. Configurar la conexión

Editar localmente:

```text
src/main/resources/application.properties
```

y reemplazar `TU_PASSWORD` por la contraseña correspondiente al usuario local de MySQL.

### 4. Ejecutar la aplicación

En Windows:

```bash
mvnw.cmd spring-boot:run
```

En Linux o macOS:

```bash
./mvnw spring-boot:run
```

Por defecto, Spring Boot inicia la aplicación en:

```text
http://localhost:8080
```

## API

### Buscar persona por RUT

```http
GET /persona/v1/find
```

El RUT se envía como query parameter mediante `rut`.

Ejemplo:

```http
GET http://localhost:8080/persona/v1/find?rut=12345678-9
```

Si existe una persona con el RUT solicitado, la API retorna sus datos.

Ejemplo de estructura de respuesta:

```json
{
  "rut": "12345678-9",
  "nombre": "Ana",
  "apellido": "Pérez",
  "fechaNas": "1990-05-15"
}
```

Si no existe una persona con ese RUT, la implementación actual retorna una respuesta sin una persona asociada.

## Probar con Postman

Crear una petición con:

```text
Método: GET
URL: http://localhost:8080/persona/v1/find
```

Agregar el siguiente Query Param:

```text
KEY: rut
VALUE: RUT_DE_LA_PERSONA
```

Ejemplo:

```text
http://localhost:8080/persona/v1/find?rut=12345678-9
```

Para obtener un resultado, debe existir previamente una persona registrada en la tabla `personas` de la base de datos.

## Tests

Actualmente el proyecto incluye una prueba básica que verifica la carga del contexto de Spring Boot.

Los tests pueden ejecutarse con:

### Windows

```bash
mvnw.cmd test
```

### Linux / macOS

```bash
./mvnw test
```

## Estado actual

Actualmente el backend implementa:

- Conexión con MySQL.
- Persistencia mediante Spring Data JPA.
- Entidad `Persona`.
- Repository para acceso a datos.
- Service para búsqueda por RUT.
- Endpoint REST para consulta por RUT.
- Configuración de Hibernate para desarrollo.
- Test básico de carga del contexto de Spring Boot.

El proyecto se encuentra en desarrollo y podrá incorporar nuevos endpoints y funcionalidades a medida que avance la implementación.

## Participantes

| Participante |
| --- |
| Eynier Cordova Serra |
| Darío Illanes Tapia |
| Francisco Javier Moraga |
| [Liliana Cedeño](https://github.com/LilianaCedeno) |
| Romina Gutiérrez |
| Pablo Francisco Igor |
| [Susana Farías Vera](https://github.com/SusanaFa) |
| Angelo López Quintana |

## Repositorio

https://github.com/SusanaFa/backend-nttdata
