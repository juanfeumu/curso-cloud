package es.um.atica.umufly.parking.application.usecase.listarreservas;

import org.springframework.data.domain.Page;

import es.um.atica.fundewebjs.umubus.domain.cqrs.Query;
import es.um.atica.umufly.parking.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.parking.domain.model.ReservaParking;


public class ListaReservasQuery extends Query<Page<ReservaParking>> {

	private final DocumentoIdentidad documentoIdentidad;
	private final Integer pagina;
	private final Integer tamanioPagina;

	private ListaReservasQuery( DocumentoIdentidad documentoIdentidad, int pagina, int tamanioPagina ) {
		this.documentoIdentidad = documentoIdentidad;
		this.pagina = pagina;
		this.tamanioPagina = tamanioPagina;
	}

	public static ListaReservasQuery of( DocumentoIdentidad documentoIdentidad, int pagina, int tamanioPagina ) {
		return new ListaReservasQuery( documentoIdentidad, pagina, tamanioPagina );
	}

	public DocumentoIdentidad getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	public Integer getPagina() {
		return pagina;
	}

	public Integer getTamanioPagina() {
		return tamanioPagina;
	}
}
