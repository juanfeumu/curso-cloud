package es.um.atica.umufly.parkings.application.usecase.obtenerdetallesreservasparking;

import java.util.Optional;
import java.util.UUID;

import es.um.atica.fundewebjs.umubus.domain.cqrs.Query;
import es.um.atica.umufly.parkings.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

public class ObtenerDetalleReservaParkingQuery extends Query<Optional<ReservaVuelo>> {

	private DocumentoIdentidad documentoIdentidad;
	private UUID idReserva;

	private ObtenerDetalleReservaParkingQuery( DocumentoIdentidad documentoIdentidad, UUID idReserva ) {
		this.documentoIdentidad = documentoIdentidad;
		this.idReserva = idReserva;
	}

	public static ObtenerDetalleReservaParkingQuery of( DocumentoIdentidad documentoIdentidad, UUID idReserva ) {
		return new ObtenerDetalleReservaParkingQuery( documentoIdentidad, idReserva );
	}

	public DocumentoIdentidad getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	public UUID getIdReserva() {
		return idReserva;
	}
}
