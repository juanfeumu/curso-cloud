package es.um.atica.umufly.vuelos.application.usecase.vuelos.listarvuelos;

import java.util.Optional;

import org.springframework.data.domain.Page;

import es.um.atica.fundewebjs.umubus.domain.cqrs.Query;
import es.um.atica.umufly.vuelos.application.dto.VueloAmpliadoDTO;
import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;

public class ListarVuelosQuery extends Query<Optional<Page<VueloAmpliadoDTO>>> {

	private DocumentoIdentidad documentoIdentidadPasajero;
	private Integer pagina;
	private Integer tamanioPagina;

	private ListarVuelosQuery( DocumentoIdentidad documentoIdentidadPasajero, Integer pagina, Integer tamanioPagina ) {
		this.documentoIdentidadPasajero = documentoIdentidadPasajero;
		this.pagina = pagina;
		this.tamanioPagina = tamanioPagina;
	}

	public static ListarVuelosQuery of( DocumentoIdentidad documentoIdentidadPasajero, Integer pagina, Integer tamanioPagina ) {
		return new ListarVuelosQuery( documentoIdentidadPasajero, pagina, tamanioPagina );
	}

	public DocumentoIdentidad getDocumentoIdentidadPasajero() {
		return documentoIdentidadPasajero;
	}

	public Integer getPagina() {
		return pagina;
	}

	public Integer getTamanioPagina() {
		return tamanioPagina;
	}

}
