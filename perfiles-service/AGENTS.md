# AGENTS.md - Guía para Agentes de Codificación AI

## Arquitectura General
Este es un microservicio Spring Boot para gestión de perfiles de empleados. Forma parte de un sistema de microservicios que incluye un servicio de empleados que emite eventos via RabbitMQ.

**Componentes principales:**
- **Modelo**: `Perfil` (MongoDB) - entidad con campos como `empleadoId`, `nombre`, `email`, `activo` (soft delete)
- **Persistencia**: `PerfilRepository` (MongoRepository) con método `findByEmpleadoId`
- **Lógica**: `PerfilService` - operaciones CRUD, creación automática de perfiles
- **API**: `PerfilController` - endpoints REST en `/perfiles`
- **Mensajería**: `PerfilEventListener` - escucha eventos `empleado.creado` y `empleado.eliminado` de RabbitMQ

**Flujo de datos:**
1. Servicio de empleados emite evento `CREADO` → crea perfil por defecto
2. Servicio de empleados emite evento `ELIMINADO` → desactiva perfil (soft delete)
3. API REST permite listar, obtener, actualizar perfiles

## Patrones y Convenciones Específicos

### Mapeo y DTOs
- Usa **MapStruct** para conversiones entre entidades y DTOs (`PerfilMapper`)
- DTOs separados: `PerfilResponseDTO` (lectura), `PerfilUpdateDTO` (escritura con validaciones)
- Actualización parcial con `@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)`
- Ejemplo: `mapper.actualizarPerfilDesdeDTO(dto, perfil)` ignora campos null en update

### Respuestas API Consistentes
- Envuelve todas las respuestas en `ApiResponse<T>`: `{success: boolean, message: string, data: T}`
- Incluye mensajes descriptivos en español
- Ejemplo: `new ApiResponse<>(true, "Perfil encontrado", perfil)`

### Soft Delete
- No elimina físicamente; marca `activo = false`
- Filtra automáticamente en consultas si es necesario (no implementado aún)
- Endpoint DELETE realiza soft delete, no hard delete

### Validación
- Usa `@Valid` en controladores
- Validaciones en `PerfilUpdateDTO` con `@Size` (ej: teléfono max 10 chars)
- Manejo global de errores en `GlobalExceptionHandler` con `ApiResponse` para errores

### Mensajería
- RabbitMQ con exchange topic `empleados-exchange`
- Bindings para routing keys `empleado.creado` y `empleado.eliminado`
- Listener procesa eventos automáticamente, llama a `PerfilService`
- DTO de evento: `EmpleadoEventDTO` con campos `evento`, `id`, `nombre`, `email`

### Dependencias y Configuración
- **Java 17**, Spring Boot 3.2.5
- **MongoDB**: URI via `MONGO_URI`
- **RabbitMQ**: host/puerto/credenciales via env vars
- **Lombok + MapStruct**: requiere configuración especial en `build.gradle` (binding y procesadores)
- Puerto: 8083

## Flujos de Desarrollo Críticos

### Construcción y Ejecución
- **Build**: `./gradlew build` (genera JAR en `build/libs/`)
- **Run local**: `./gradlew bootRun`
- **Tests**: `./gradlew test`
- **Docker**: `docker build -t perfiles-service .` (usa JAR de `build/libs/`)

### Debugging
- Logs con `@Slf4j`: `log.info("Perfil creado: {}", empleadoId)`
- Eventos RabbitMQ logueados en listener
- Excepciones custom: `PerfilNotFoundException` → 404 con `ApiResponse`

### Integración
- Requiere MongoDB y RabbitMQ corriendo
- Variables de entorno: `MONGO_URI`, `RABBIT_HOST`, `RABBIT_USER`, `RABBIT_PASS`
- Eventos de empleados: asegura routing keys `empleado.creado`/`eliminado`

## Archivos Clave
- `src/main/java/.../model/Perfil.java` - entidad MongoDB
- `src/main/java/.../service/PerfilService.java` - lógica de negocio
- `src/main/java/.../controller/PerfilController.java` - API REST
- `src/main/java/.../messaging/PerfilEventListener.java` - integración RabbitMQ
- `src/main/java/.../mapper/PerfilMapper.java` - mapeos MapStruct
- `src/main/resources/application.properties` - configuración externa
- `build.gradle` - dependencias y build</content>
<parameter name="filePath">C:\Microservicios\perfiles-service\AGENTS.md
