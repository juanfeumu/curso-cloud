package es.um.atica.umufly.parkings.application.usecase.cancelarresservasparking;

import java.util.UUID;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommand;
import es.um.atica.umufly.parkings.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.parkings.domain.model.ReservaParking;

public class CancelarReservaParkingCommand extends SyncCommand<ReservaParking> {

	private DocumentoIdentidad documentoIdentidadTitular;
	private UUID idReserva;

	private CancelarReservaParkingCommand( DocumentoIdentidad documentoIdentidadTitular, UUID idReserva ) {
		this.documentoIdentidadTitular = documentoIdentidadTitular;
		this.idReserva = idReserva;
	}

	public static CancelarReservaParkingCommand of( DocumentoIdentidad documentoIdentidadTitular, UUID idReserva ) {
		return new CancelarReservaParkingCommand( documentoIdentidadTitular, idReserva );
	}

	public DocumentoIdentidad getDocumentoIdentidadTitular() {
		return documentoIdentidadTitular;
	}

	public UUID getIdReserva() {
		return idReserva;
	}

}
