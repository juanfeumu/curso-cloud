package es.um.atica.umufly.vuelos.application.usecase.reservas.listarreservas;

import java.util.Optional;

import org.springframework.data.domain.Page;

import es.um.atica.fundewebjs.umubus.domain.cqrs.Query;
import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

public class ListarReservasQuery extends Query<Optional<Page<ReservaVuelo>>> {

	private DocumentoIdentidad documentoIdentidad;
	private int pagina;
	private int tamanioPagina;

	private ListarReservasQuery( DocumentoIdentidad documentoIdentidad, int pagina, int tamanioPagina ) {
		this.documentoIdentidad = documentoIdentidad;
		this.pagina = pagina;
		this.tamanioPagina = tamanioPagina;
	}

	public static ListarReservasQuery of( DocumentoIdentidad documentoIdentidad, int pagina, int tamanioPagina ) {
		return new ListarReservasQuery( documentoIdentidad, pagina, tamanioPagina );
	}

	public DocumentoIdentidad getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	public int getPagina() {
		return pagina;
	}

	public int getTamanioPagina() {
		return tamanioPagina;
	}

}
