package es.um.atica.umufly.vuelos.application.usecase.reservas.obtenerreservas;

import java.util.Optional;
import java.util.UUID;

import es.um.atica.fundewebjs.umubus.domain.cqrs.Query;
import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

public class ObtenerReservasQuery extends Query<Optional<ReservaVuelo>> {

	private DocumentoIdentidad documentoIdentidad;
	private UUID idReserva;

	private ObtenerReservasQuery( DocumentoIdentidad documentoIdentidad, UUID idReserva ) {
		this.documentoIdentidad = documentoIdentidad;
		this.idReserva = idReserva;
	}

	public static ObtenerReservasQuery of( DocumentoIdentidad documentoIdentidad, UUID idReserva ) {
		return new ObtenerReservasQuery( documentoIdentidad, idReserva );
	}

	public DocumentoIdentidad getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	public UUID getIdReserva() {
		return idReserva;
	}

}