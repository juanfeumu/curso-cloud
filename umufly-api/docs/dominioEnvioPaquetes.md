# EnvioPaquete **AR**

- UUID idEnvio
- UUID idVuelo
- UsuarioEnvio remitente
- UsuarioEnvio destinatario
- DatosPaquete paquete
- UUID idSeguimiento
- BigDecimal importe
- EstadoEnvio estado

## EstadoEnvio **VO**

- Enum: Facturado, EnTransito, Entregado

## DatosPaquete **A**

- String descripción
- BigDecimal peso
- Boolean fragil

## Tarifa **VO**
- Enum: Fragil, Normal
- BigDecimal precioKilo;

## UsuarioEnvio **A**
- UUID idUsuario
- NombreCompleto nombreCompleto
- DocumentoIdentidad documentoIdentidad
- CorreoElectronico email
- Telefono telefono

## NombreCompleto **VO**

- String nombre
- String primerApellido
- String segundoApellido

## DocumentoIdentidad **VO**

- TipoDocumento tipo
- String identificador

## CorreoElectronico **VO**

- String valor

## Telefono **VO**

- String valor
