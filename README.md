# backend-nttdata

Backend desarrollado en **Java** con **Spring Boot** y **Spring Data JPA** para la consulta de personas almacenadas en una base de datos **MySQL**.

Actualmente la API permite consultar información de personas mediante su RUT.
---
## Tecnologías
- Java 17
- Spring Boot 4.0.8
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL
- Railway
- MySQL Connector/J
- Lombok
- Maven
---

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
---

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
MySQL(Railway)
```
---
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

### Base de Datos

La aplicación utiliza una base de datos **MySQL alojada en Railway**.
Hibernate está configurado para crear y actualizar automáticamente la estructura de la base de datos:

Durante el desarrollo, Hibernate está configurado con:

```properties
spring.jpa.hibernate.ddl-auto=update
```

Al iniciar la aplicación, Hibernate crea automáticamente la tabla correspondiente a la entidad `Persona` si ésta no existe.

## Configuración de MySQL

La configuración de conexión se encuentra en:

```text
src/main/resources/application.properties
```

Actualmente contiene:

```properties
spring.application.name=personas

spring.datasource.url=jdbc:mysql://altaria.proxy.rlwy.net:13282/railway
spring.datasource.username=root
spring.datasource.password=uqYBrMgrIVMJSUXvCjuWiieglYOBtlop

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8083
```
---

## Ejecutar el proyecto
### Requisitos

- Java 17
- Git

No es necesario instalar MySQL localmente, ya que la aplicación utiliza una base de datos remota en Railway.

## Clonar el repositorio

```bash
git clone https://github.com/SusanaFa/backend-nttdata.git
cd backend-nttdata
```

## Ejecutar

### Windows

```bash
mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

Por defecto la aplicación puede ejecutarse en:

```text
http://localhost:8083
```

## API

### Buscar persona por RUT

```http
GET /persona/v1/find
```

El RUT se envía como query parameter mediante `rut`.

Ejemplo:

```http
GET http://localhost:8083/persona/v1/find?rut=12345678-9
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
URL: http://localhost:8083/persona/v1/find
```

Agregar el siguiente Query Param:

```text
KEY: rut
VALUE: RUT_DE_LA_PERSONA
```

Ejemplo:

```text
http://localhost:8083/persona/v1/find?rut=12345678-9
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

- Conexión a MySQL.
- Base de datos remota en Railway.
- Persistencia mediante Spring Data JPA.
- Hibernate para gestión de entidades.
- Entidad `Persona`.
- Repository para acceso a datos.
- Service para búsqueda por RUT.
- Endpoint REST para consulta de personas.
- Creación automática de tablas mediante Hibernate.
- Integración con GitHub.
- Preparación para despliegue en Azure App Service.
 
El proyecto se encuentra en desarrollo y podrá incorporar nuevos endpoints y funcionalidades a medida que avance la implementación.

### Participantes

| Participante | GitHub |
|-------------|--------|
| Eynier Cordova Serra | [@blackwolf62](https://github.com/blackwolf62) |
| Darío Illanes Tapia | - |
| Francisco Javier Moraga | - |
| Liliana Cedeño | [@LilianaCedeno](https://github.com/LilianaCedeno) |
| Romina Gutiérrez | - |
| Pablo Francisco Igor | [@pableteih](https://github.com/pableteih) |
| Susana Farías Vera | [@SusanaFa](https://github.com/SusanaFa) |
| Angelo López Quintana | [@lord-angelo](https://github.com/lord-angelo) |

## Repositorio

https://github.com/SusanaFa/backend-nttdata
