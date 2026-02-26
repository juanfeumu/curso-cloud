# Parking

## Definición de Dominio

- ReservaParking
- Cliente
- Periodo
- TipoEstacionamiento
- Importe

### ReservaParking **(AR)**

- UUID id
- Periodo perido
- UUID IdCliente
- TipoEstacionamiento tipo
- Importe importe
- LocalDateTime fechaReserva;
- EstadoReserva estado
- Descuento: valor calculado en base al evento de si tiene vuelos reservados


### Periodo

- Fecha inicio
- Fecha fin: Debe ser >= fecha inicio

### TipoEstacionamiento

Debe ser un enumerado con valores {CORTA, LARGA}

### EstadoReserva

Debe ser un enumerado que refleje si está consolidado, o no


## Casos de uso

- CrearReservaParking
- CancelarReservaParking
- ObtenerReservaParking
- ListarReservasParking