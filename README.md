# PetManager - Backend

> API para gestión de tienda de mascotas.

**Requisitos**
- Java 21
- Maven
- PostgreSQL

**Variables de entorno**

Este proyecto importa configuración desde un fichero `.env` (opcional) gracias a `spring.config.import=optional:file:.env[.properties]`. Para arrancar la aplicación localmente copia `.env.example` a `.env` y ajusta los valores.

Variables necesarias (ejemplo en `.env.example`):
- `DB_HOST` - Host de la base de datos (ej. `localhost`)
- `DB_PORT` - Puerto de la base de datos (ej. `5432`)
- `DB_NAME` - Nombre de la base de datos (ej. `petmanager`)
- `DB_USER` - Usuario de la base de datos
- `DB_PASSWORD` - Contraseña del usuario de la base de datos
- `JWT_SECRET` - Secreto usado para firmar tokens JWT

**Base de Datos**

Para que el sistema funcione correctamente, se tienen que crear las siguientes entradas en la base de datos:

1. Roles del proyecto (IDs 1: ADMIN, 2: MANAGER, 3: EMPLOYEE)

```sql
INSERT INTO roles (name, description)
VALUES
('ADMIN', 'View and edit data. Can edit user''s roles'),
('MANAGER', 'View and edit data. Can''t edit user''s roles'),
('EMPLOYEE', 'View data');
```

2. Condiciones de pago del proyecto

```sql
INSERT INTO payment_conditions (name)
VALUES
('Contado'),
('Contraentrega'),
('Crédito a 8 días'),
('Crédito a 15 días'),
('Crédito a 30 días'),
('Crédito a 45 días'),
('Crédito a 60 días'),
('Anticipo del 50% y saldo contra entrega'),
('Pago con tarjeta (crédito o débito)'),
('Transferencia bancaria previa al despacho'),
('Pago mediante consignación o giro'),
('Pago recurrente o por suscripción'),
('Otra');
```

**Autorización**

Todos los endpoints, **excepto `api/auth/**`**, están protegidos con JWT.

Para acceder a los endpoints protegidos, todas las peticiones deben incluir un header de autorización con un token JWT válido:

```
Authorization: Bearer <token_jwt>
```

**Documentación API**

La documentación completa de la API está disponible en Swagger, la cual puede ser consultada en la siguiente ruta, mientras la aplicación está corriendo:
`/swagger-ui/index.html`