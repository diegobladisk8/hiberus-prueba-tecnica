# Decisiones de Corrección Manual

## 1. Validaciones de Negocio
**Decisión**: Implementar validaciones adicionales no cubiertas por IA
- Cuentas debtor/creditor no pueden ser iguales
- Fecha de ejecución no puede ser en pasado
- Monto debe ser positivo

**Razón**: La IA no capturó todas las reglas de negocio del dominio

## 2. Estructura de Respuestas
**Decisión**: Usar RFC 7807 para errores en lugar de respuestas simples
**Razón**: Estándar industry para APIs RESTful

## 3. Persistencia Reactiva
**Decisión**: Implementar R2DBC en lugar de JPA
**Razón**: Mejor performance con stack reactivo de WebFlux

## 4. Nomenclatura BIAN
**Decisión**: Ajustar nombres de campos según estándar BIAN
**Razón**: Cumplimiento con estándares del dominio bancario