package es.um.atica.umufly.parkings.application.usecase.solicitarreservasparking;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommand;
import es.um.atica.umufly.parkings.domain.model.Descuento;
import es.um.atica.umufly.parkings.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.parkings.domain.model.Periodo;
import es.um.atica.umufly.parkings.domain.model.ReservaParking;
import es.um.atica.umufly.parkings.domain.model.TipoEstacionamiento;

public class SolicitarReservaParkingCommand extends SyncCommand<ReservaParking> {

	private DocumentoIdentidad documentoIdentidadTitular;
	private TipoEstacionamiento tipo;
	private Periodo periodoEstacionamiento;
	private Descuento descuento;

	private SolicitarReservaParkingCommand( DocumentoIdentidad documentoIdentidadTitular, TipoEstacionamiento tipo, Periodo periodoEstacionamiento, Descuento descuento ) {
		this.documentoIdentidadTitular = documentoIdentidadTitular;
		this.tipo = tipo;
		this.periodoEstacionamiento = periodoEstacionamiento;
		this.descuento = descuento;
	}

	public SolicitarReservaParkingCommand of( DocumentoIdentidad documentoIdentidadTitular, TipoEstacionamiento tipo, Periodo periodoEstacionamiento, Descuento descuento ) {
		return new SolicitarReservaParkingCommand( documentoIdentidadTitular, tipo, periodoEstacionamiento, descuento );
	}

	public DocumentoIdentidad getDocumentoIdentidadTitular() {
		return documentoIdentidadTitular;
	}

	public TipoEstacionamiento getTipo() {
		return tipo;
	}

	public Periodo getPeriodoEstacionamiento() {
		return periodoEstacionamiento;
	}

	public Descuento getDescuento() {
		return descuento;
	}

}
