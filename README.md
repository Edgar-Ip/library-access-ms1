# Library Access – Microservicio 1

## Descripción
Microservicio encargado de gestionar el control de acceso a una biblioteca.
Permite registrar el ingreso y salida de usuarios, controlar el aforo máximo
y consultar los usuarios actualmente dentro de la biblioteca.

⚠️ **Nota importante**  
Este microservicio fue desarrollado inicialmente en **Java 8** y posteriormente
**migrado a Java 21 (LTS)** como mejora adicional, manteniendo el comportamiento
funcional sin regresiones.

---

## Responsabilidades
- Registrar ingreso de usuarios
- Registrar salida de usuarios
- Controlar aforo máximo (10 personas)
- Validar código de usuario
- Simular latencia bajo demanda

---

## Reglas de Negocio
- El código de usuario debe ser:
  - Alfanumérico
  - Exactamente 8 caracteres
- No se permite el ingreso de usuarios duplicados
- Si el aforo máximo es alcanzado, se deniega el acceso
- Si se envía el header `MyFlag=true`, la consulta tarda 8 segundos

---

## Base de Datos
- H2 (en memoria)

---

## Tecnologías Utilizadas
- Java 21 (LTS)
- Spring Boot 3.2.x
- Spring Data JPA
- H2 Database
- Maven
- Git

---

## Requisitos
- JDK 21
- Maven 3.8+

---

## Ejecución del Proyecto

bash
mvn clean spring-boot:run

Puerto: 8081

--- 

### Endpoints

| Método | Endpoint                    | Descripción          |
| ------ | --------------------------- | -------------------- |
| POST   | `/library/entry/{userCode}` | Ingreso de usuario   |
| POST   | `/library/exit/{userCode}`  | Salida de usuario    |
| GET    | `/library/users`            | Consulta de usuarios |

---

### Header opcional
MyFlag=true

---

#### Manejo de Errores

Formato json


#### 1. Código de usuario inválido (no cumple 8 alfanumérico)

Formato json

{
  "code": 422,
  "type": "Error",
  "timestamp": 1763045191,
  "details": "El código debe tener 8 caracteres alfanuméricos"
}

#### 🧪 2. Usuario ya está dentro de la biblioteca

{
  "code": 401,
  "type": "Error",
  "timestamp": 1763045191,
  "details": "El usuario ya está dentro de la biblioteca"
}

#### 🧪 3. Aforo máximo alcanzado (10 personas)

{
  "code": 401,
  "type": "Error",
  "timestamp": 1763045191,
  "details": "Aforo máximo alcanzado"
}

#### 🧪 4. Usuario no existe al intentar salir

{
  "code": 404,
  "type": "Error",
  "timestamp": 1763045191,
  "details": "El usuario no existe en la biblioteca"
}


---


## Pruebas Unitarias
El microservicio cuenta con pruebas unitarias desarrolladas con JUnit 5 y Mockito,
enfocadas en la validación de reglas de negocio y manejo de excepciones.


---

### Versionado

-Rama java-8-stable: versión estable en Java 8

-Rama master: versión final en Java 21


---

#### Autor:

Proyecto desarrollado como ejercicio técnico de Backend Java

Edgar Eduardo Gutiérrez Mejia
