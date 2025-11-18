# Payment Orders Service - Hiberus Prueba Técnica

Servicio REST para gestión de órdenes de pago implementado con arquitectura hexagonal, Java 21, Spring Boot WebFlux y programación reactiva.

## 📋 Descripción

Este proyecto implementa un servicio REST moderno para la gestión de órdenes de pago, siguiendo los principios de arquitectura hexagonal (Ports & Adapters) y utilizando programación reactiva con Spring WebFlux.

## 🏗️ Arquitectura

El proyecto está organizado siguiendo el patrón de **Arquitectura Hexagonal**:

```
com.hiberus.payment
├── domain                  # Capa de Dominio
│   ├── model              # Entidades y objetos de valor
│   └── exception          # Excepciones de dominio
├── application            # Capa de Aplicación
│   ├── ports
│   │   ├── input         # Puertos de entrada (casos de uso)
│   │   └── output        # Puertos de salida (interfaces de repositorio)
│   └── service           # Implementación de casos de uso
├── adapters              # Capa de Adaptadores
│   ├── input
│   │   └── rest         # Controladores REST
│   │       ├── controller
│   │       └── mapper
│   └── output
│       └── persistence  # Adaptador de persistencia (in-memory)
└── config               # Configuración
```

### Capas

- **Dominio**: Contiene la lógica de negocio pura (entidades, value objects, excepciones)
- **Aplicación**: Orquesta los casos de uso y define las interfaces (puertos)
- **Adaptadores**: Implementan las interfaces definidas por la aplicación
- **Configuración**: Configuración de Spring y manejo global de excepciones

## 🚀 Tecnologías

- **Java 21**: Lenguaje de programación
- **Gradle 8.5**: Herramienta de construcción
- **Spring Boot 3.2.0**: Framework principal
- **Spring WebFlux**: Programación reactiva
- **OpenAPI 3.0**: Especificación de la API (Contract-first)
- **OpenAPI Generator**: Generación de interfaces y DTOs
- **Lombok**: Reducción de código boilerplate
- **JUnit 5**: Testing

## 📝 Endpoints REST

El servicio expone los siguientes endpoints:

### 1. Crear Orden de Pago
```bash
POST /payment-initiation/payment-orders
Content-Type: application/json

{
  "debtorAccount": "ES7921000813610123456789",
  "creditorAccount": "ES1234567890123456789012",
  "creditorName": "John Doe",
  "amount": 100.50,
  "currency": "EUR",
  "description": "Invoice payment"
}
```

**Respuesta**: 201 Created
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "debtorAccount": "ES7921000813610123456789",
  "creditorAccount": "ES1234567890123456789012",
  "creditorName": "John Doe",
  "amount": 100.50,
  "currency": "EUR",
  "description": "Invoice payment",
  "status": "PENDING",
  "createdAt": "2024-01-15T10:30:00Z",
  "updatedAt": "2024-01-15T10:30:00Z"
}
```

### 2. Consultar Orden de Pago
```bash
GET /payment-initiation/payment-orders/{id}
```

**Respuesta**: 200 OK (mismo formato que POST)

### 3. Consultar Estado de Orden de Pago
```bash
GET /payment-initiation/payment-orders/{id}/status
```

**Respuesta**: 200 OK
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "status": "PENDING",
  "updatedAt": "2024-01-15T10:30:00Z"
}
```

### Estados Posibles
- `PENDING`: Orden creada, pendiente de procesamiento
- `PROCESSING`: Orden en proceso
- `COMPLETED`: Orden completada exitosamente
- `FAILED`: Orden fallida
- `CANCELLED`: Orden cancelada

## 🛠️ Compilación y Ejecución

### Requisitos Previos
- Java 21
- Gradle 8.5+ (incluido con wrapper)

### Compilar el proyecto
```bash
./gradlew clean build
```

### Ejecutar tests
```bash
./gradlew test
```

### Ejecutar la aplicación
```bash
./gradlew bootRun
```

La aplicación estará disponible en `http://localhost:8080`

### Generar el JAR ejecutable
```bash
./gradlew bootJar
```

El JAR se generará en `build/libs/payment-orders-service-0.0.1-SNAPSHOT.jar`

## 📚 Documentación de la API

### OpenAPI Specification
La especificación OpenAPI está disponible en:
- Archivo: `src/main/resources/openapi/payment-orders-api.yaml`
- Endpoint: `http://localhost:8080/api-docs`

### Swagger UI
Interfaz interactiva para probar la API:
- URL: `http://localhost:8080/swagger-ui.html`

## 🧪 Testing

El proyecto incluye tests de integración que verifican:
- Creación de órdenes de pago
- Consulta de órdenes por ID
- Consulta de estado de órdenes
- Manejo de errores (404 para órdenes inexistentes)

Ejecutar tests:
```bash
./gradlew test
```

## 🔧 Configuración

La configuración principal se encuentra en `src/main/resources/application.yml`:

```yaml
server:
  port: 8080

logging:
  level:
    com.hiberus.payment: DEBUG
```

## 💾 Persistencia

La implementación actual utiliza un repositorio **in-memory** (`ConcurrentHashMap`) para almacenar las órdenes de pago. 

Para un entorno de producción, se debería reemplazar `InMemoryPaymentOrderRepository` con una implementación que use una base de datos real (por ejemplo, MongoDB con R2DBC para mantener la naturaleza reactiva).

## 🔄 Programación Reactiva

El servicio utiliza programación reactiva con Spring WebFlux y Project Reactor:
- Todos los endpoints devuelven tipos reactivos (`Mono<T>`)
- Procesamiento no bloqueante
- Mayor eficiencia en operaciones I/O

## 📦 Contract-First con OpenAPI

El proyecto sigue el enfoque **contract-first**:
1. La API se define primero en el archivo OpenAPI YAML
2. Las interfaces y DTOs se generan automáticamente con `openapi-generator`
3. Los controladores implementan las interfaces generadas

Esto garantiza:
- Consistencia entre especificación e implementación
- Documentación actualizada automáticamente
- Mejor colaboración entre equipos

## 🤝 Contribuir

Para contribuir al proyecto:
1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto es parte de una prueba técnica para Hiberus.
