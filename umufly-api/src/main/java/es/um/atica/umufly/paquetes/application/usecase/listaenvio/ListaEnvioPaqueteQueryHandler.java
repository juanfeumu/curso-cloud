package es.um.atica.umufly.paquetes.application.usecase.listaenvio;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.QueryHandler;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.EnvioPaquetePersistenceAdapter;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;

@Component
public class ListaEnvioPaqueteQueryHandler implements QueryHandler<Page<EnvioPaquete>, ListaEnvioPaqueteQuery> {

	private final EnvioPaquetePersistenceAdapter envioPaquetePersistenceAdapter;

	public ListaEnvioPaqueteQueryHandler( EnvioPaquetePersistenceAdapter envioPaquetePersistenceAdapter ) {
		this.envioPaquetePersistenceAdapter = envioPaquetePersistenceAdapter;
	}

	@Override
	public Page<EnvioPaquete> handle( ListaEnvioPaqueteQuery query ) throws Exception {
		return envioPaquetePersistenceAdapter.findEnvioPaquete( query.getDocumentoIdentidad(), query.getPagina(), query.getTamanioPagina() );
	}

}
