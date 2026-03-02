package es.um.atica.umufly.vuelos.application.usecase.reservas.cancelarreservas;

import java.util.UUID;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommand;
import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

public class CancelarReservasCommand extends SyncCommand<ReservaVuelo> {

	private DocumentoIdentidad documentoIdentidadTitular;
	private UUID idReserva;

	private CancelarReservasCommand( DocumentoIdentidad documentoIdentidadTitular, UUID idReserva ) {
		this.documentoIdentidadTitular = documentoIdentidadTitular;
		this.idReserva = idReserva;
	}

	public static CancelarReservasCommand of( DocumentoIdentidad documentoIdentidadTitular, UUID idReserva ) {
		return new CancelarReservasCommand( documentoIdentidadTitular, idReserva );
	}

	public DocumentoIdentidad getDocumentoIdentidadTitular() {
		return documentoIdentidadTitular;
	}

	public UUID getIdReserva() {
		return idReserva;
	}

}
