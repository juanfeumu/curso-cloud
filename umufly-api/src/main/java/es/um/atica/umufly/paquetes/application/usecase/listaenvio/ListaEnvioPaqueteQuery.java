package es.um.atica.umufly.paquetes.application.usecase.listaenvio;

import org.springframework.data.domain.Page;

import es.um.atica.fundewebjs.umubus.domain.cqrs.Query;
import es.um.atica.umufly.paquetes.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;

public class ListaEnvioPaqueteQuery extends Query<Page<EnvioPaquete>> {

	private final DocumentoIdentidad documentoIdentidad;
	private final Integer pagina;
	private final Integer tamanioPagina;

	private ListaEnvioPaqueteQuery( DocumentoIdentidad documentoIdentidad, Integer pagina, Integer tamanioPagina ) {
		this.documentoIdentidad = documentoIdentidad;
		this.pagina = pagina;
		this.tamanioPagina = tamanioPagina;
	}

	public static ListaEnvioPaqueteQuery of( DocumentoIdentidad documentoIdentidad, Integer pagina, Integer tamanioPagina ) {
		return new ListaEnvioPaqueteQuery( documentoIdentidad, pagina, tamanioPagina );
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
