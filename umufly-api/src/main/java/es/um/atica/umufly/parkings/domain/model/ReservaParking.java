package es.um.atica.umufly.parkings.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReservaParking {

	private UUID id;
	private DocumentoIdentidad identidadPasajero;
	private TipoEstacionamiento tipo;
	private Periodo periodoEstacionamiento;
	private Importe importe;
	private LocalDateTime fechaReserva;
	private EstadoReserva estado;
	private Descuento descuento;

	private ReservaParking( UUID id, DocumentoIdentidad identidadPasajero, TipoEstacionamiento tipo, Periodo periodoEstacionamiento, Importe importe, LocalDateTime fechaReserva, EstadoReserva estado, Descuento descuento ) {
		this.id = id;
		this.identidadPasajero = identidadPasajero;
		this.tipo = tipo;
		this.periodoEstacionamiento = periodoEstacionamiento;
		this.importe = importe;
		this.fechaReserva = fechaReserva;
		this.estado = estado;
		this.descuento = descuento;
	}

	public static ReservaParking of( UUID id, DocumentoIdentidad identidadPasajero, TipoEstacionamiento tipo, Periodo periodoEstacionamiento, Importe importe, LocalDateTime fechaReserva, EstadoReserva estado, Descuento descuento ) {
		if ( identidadPasajero == null ) {
			throw new IllegalArgumentException( "El pasajero no puede ser nulo" );
		}
		if ( tipo == null ) {
			throw new IllegalArgumentException( "El tipo de reserva no puede ser nulo" );
		}
		if ( periodoEstacionamiento == null ) {
			throw new IllegalArgumentException( "El periodo de estacionamiento no puede ser nulo" );
		}
		if ( importe == null ) {
			throw new IllegalArgumentException( "El importe no puede ser nulo" );
		}
		if ( estado == null ) {
			throw new IllegalArgumentException( "El estado no puede ser nulo" );
		}
		return new ReservaParking( id, identidadPasajero, tipo, periodoEstacionamiento, importe, fechaReserva, estado, descuento );
	}

	public UUID getId() {
		return id;
	}

	public DocumentoIdentidad getIdentidadPasajero() {
		return identidadPasajero;
	}

	public TipoEstacionamiento getTipo() {
		return tipo;
	}

	public Periodo getPeriodoEstacionamiento() {
		return periodoEstacionamiento;
	}

	public Importe getImporte() {
		return importe;
	}

	public LocalDateTime getFechaReserva() {
		return fechaReserva;
	}

	public EstadoReserva getEstado() {
		return estado;
	}

	public Descuento getDescuento() {
		return descuento;
	}

	public static ReservaParking solicitarReserva( DocumentoIdentidad identidadPasajero, Periodo periodoEstacionamiento, LocalDateTime fechaReserva, int reservasVuelos ) {
		// Si tenermos reservas de vuelo, entonces tenemos el 75% descuento
		Descuento descuento = new Descuento( reservasVuelos > 0 ? 75 : 0 );
		// Si el periodo es de al menos un día, entonces es larga duración
		TipoEstacionamiento tipo = periodoEstacionamiento.getDiasPeriodo() > 0 ? TipoEstacionamiento.LARGA_DURACION : TipoEstacionamiento.CORTA_DURACION;
		BigDecimal imp = null;
		if ( TipoEstacionamiento.CORTA_DURACION.equals( tipo ) ) {
			imp = BigDecimal.valueOf( 0.02d ).multiply( BigDecimal.valueOf( periodoEstacionamiento.minutosRestantes() ) );
		} else {
			imp = BigDecimal.valueOf( 7d ).multiply( BigDecimal.valueOf( periodoEstacionamiento.getDiasPeriodo() ) );
		}
		if ( descuento.descuento() > 0 ) {
			// Importe = importe * (100 - Descuento ) / 100
			imp = imp.multiply( BigDecimal.valueOf( 100d - descuento.descuento() ).divide( BigDecimal.valueOf( 100d ) ) );
		}
		Importe importe = new Importe( imp );
		return ReservaParking.of( UUID.randomUUID(), identidadPasajero, tipo, periodoEstacionamiento, importe, fechaReserva, EstadoReserva.ACTIVA, descuento );
	}

	/**
	 * Indica que puede cancelar a la fecha inidicada
	 *
	 * @param fecha
	 *              Fecha a comprobar
	 * @return Si puede o no cancelar
	 */
	public boolean puedeCancelar( LocalDateTime fecha ) {
		return periodoEstacionamiento.inicio().isAfter( fecha );
	}

	public void cancelarReserva( LocalDateTime fecha ) {
		if ( !puedeCancelar( fecha ) ) {
			throw new IllegalCallerException( "No se puede cancelar en esa fecha" );
		}
		estado = EstadoReserva.CANCELADA;
	}

}
