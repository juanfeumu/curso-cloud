package es.um.atica.umufly.parking.adaptors.persistence.jpa.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import es.um.atica.umufly.parking.adaptors.persistence.jpa.entity.EstadoReservaParkingEnum;
import es.um.atica.umufly.parking.adaptors.persistence.jpa.entity.ReservaParkingEntity;
import es.um.atica.umufly.parking.adaptors.persistence.jpa.entity.ReservaParkingViewEntity;
import es.um.atica.umufly.parking.adaptors.persistence.jpa.entity.TipoDocumentoEnum;
import es.um.atica.umufly.parking.adaptors.persistence.jpa.entity.TipoEstacionamientoEnum;
import es.um.atica.umufly.parking.adaptors.persistence.jpa.entity.TipoEstacionamientoViewExtEntity;
import es.um.atica.umufly.parking.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.parking.domain.model.Estacionamiento;
import es.um.atica.umufly.parking.domain.model.EstadoReservaParking;
import es.um.atica.umufly.parking.domain.model.Importe;
import es.um.atica.umufly.parking.domain.model.Periodo;
import es.um.atica.umufly.parking.domain.model.ReservaParking;
import es.um.atica.umufly.parking.domain.model.TipoDocumento;
import es.um.atica.umufly.parking.domain.model.TipoEstacionamiento;

public class JpaPersistenceMapper {

	private JpaPersistenceMapper() {
		throw new IllegalStateException( "Clase de utilidad" );
	}

	public static TipoDocumentoEnum tipoDocumentoToEntity( TipoDocumento tipoDocumento ) {
		return switch ( tipoDocumento ) {
			case NIF -> TipoDocumentoEnum.N;
			case NIE -> TipoDocumentoEnum.E;
			case PASAPORTE -> TipoDocumentoEnum.P;
			default -> throw new IllegalArgumentException( "Unexpected value: " + tipoDocumento );
		};
	}

	public static TipoEstacionamientoEnum tipoEstacionamientoToEntity( TipoEstacionamiento tipoEstacionamiento ) {
		return switch ( tipoEstacionamiento ) {
			case CORTA_DURACION -> TipoEstacionamientoEnum.C;
			case LARGA_DURACION -> TipoEstacionamientoEnum.L;
			default -> throw new IllegalArgumentException( "Unexpected value: " + tipoEstacionamiento );
		};
	}

	public static TipoEstacionamiento entityToTipoEstacionamiento( TipoEstacionamientoEnum tipoEstacionamiento ) {
		return switch ( tipoEstacionamiento ) {
			case C -> TipoEstacionamiento.CORTA_DURACION;
			case L -> TipoEstacionamiento.LARGA_DURACION;
			default -> throw new IllegalArgumentException( "Unexpected value: " + tipoEstacionamiento );
		};
	}

	public static TipoDocumento tipoDocumentoEntityToModel( TipoDocumentoEnum tipoDocumento ) {
		return switch ( tipoDocumento ) {
			case N -> TipoDocumento.NIF;
			case E -> TipoDocumento.NIE;
			case P -> TipoDocumento.PASAPORTE;
			default -> throw new IllegalArgumentException( "Unexpected value: " + tipoDocumento );
		};
	}

	public static EstadoReservaParkingEnum estadoReservaToEntity( EstadoReservaParking estadoReservaParking ) {
		return switch ( estadoReservaParking ) {
			case ACTIVA -> EstadoReservaParkingEnum.A;
			case CANCELADA -> EstadoReservaParkingEnum.X;
			default -> throw new IllegalArgumentException( "Estado de la reserva no contemplado: " + estadoReservaParking );
		};
	}

	public static EstadoReservaParking estadoReservaEntityToModel( EstadoReservaParkingEnum estadoReservaParking ) {
		return switch ( estadoReservaParking ) {
			case A -> EstadoReservaParking.ACTIVA;
			case X -> EstadoReservaParking.CANCELADA;
			default -> throw new IllegalArgumentException( "Estado de la reserva no contemplado: " + estadoReservaParking );
		};
	}

	public static ReservaParkingEntity reservaParkingToEntity( ReservaParking rp, LocalDateTime fechaReserva, LocalDateTime fechaModificacion ) {
		ReservaParkingEntity r = new ReservaParkingEntity();
		r.setId( rp.getId().toString() );
		r.setTipoDocumentoCliente( tipoDocumentoToEntity( rp.getIdentificadorCliente().tipo() ) );
		r.setNumeroDocumentoCliente( rp.getIdentificadorCliente().identificador() );
		r.setFechaReserva( fechaReserva );
		r.setFechaReserva( fechaModificacion );
		r.setEstadoReserva( estadoReservaToEntity( rp.getEstado() ) );
		return r;
	}

	// public static ReservaParkingViewEntity reservaParkingViewToEntity( ReservaParking rr, LocalDateTime fechaCreacion,
	// LocalDateTime fechaModificacion ) {
	// ReservaParkingViewEntity r = new ReservaParkingViewEntity();
	// r.setId( rr.getId().toString() );
	// r.setTipoDocumentoTitular( tipoDocumentoToEntity( rr.getIdentificadorTitular().tipo() ) );
	// r.setNumeroDocumentoTitular( rr.getIdentificadorTitular().identificador() );
	// r.setFechaCreacion( fechaCreacion );
	// r.setEstadoReserva( estadoReservaToEntity( rr.getEstado() ) );
	// r.addPasajero( pasajeroViewToEntity( rr.getPasajero() ) );
	// return r;
	// }

	public static ReservaParking reservaParkingToModel( ReservaParkingViewEntity r, TipoEstacionamientoViewExtEntity estacionamiento ) {
		return ReservaParking.of( UUID.fromString( r.getId() ), new DocumentoIdentidad( tipoDocumentoEntityToModel( r.getTipoDocumentoCliente() ), r.getNumeroDocumentoCliente() ),
				new Estacionamiento( entityToTipoEstacionamiento( estacionamiento.getTipo() ), estacionamiento.getPrecio() ),
				new Periodo( r.getFechaInicio(), r.getFechaFin() ),
				new Importe( r.getImporte() ), r.getFechaReserva(), estadoReservaEntityToModel( r.getEstadoReserva() ) );
	}
}
