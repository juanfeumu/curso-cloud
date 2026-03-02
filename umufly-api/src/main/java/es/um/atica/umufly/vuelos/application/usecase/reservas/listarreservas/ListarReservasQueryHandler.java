package es.um.atica.umufly.vuelos.application.usecase.reservas.listarreservas;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.QueryHandler;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloReadRepository;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

@Component
public class ListarReservasQueryHandler implements QueryHandler<Optional<Page<ReservaVuelo>>, ListarReservasQuery> {

	private final ReservasVueloReadRepository reservasVueloReadRepository;

	public ListarReservasQueryHandler( ReservasVueloReadRepository reservasVueloRepository ) {
		this.reservasVueloReadRepository = reservasVueloRepository;
	}

	@Override
	public Optional<Page<ReservaVuelo>> handle( ListarReservasQuery query ) throws Exception {
		return Optional.of( reservasVueloReadRepository.findReservas( query.getDocumentoIdentidad(), query.getPagina(), query.getTamanioPagina() ) );
	}

}
