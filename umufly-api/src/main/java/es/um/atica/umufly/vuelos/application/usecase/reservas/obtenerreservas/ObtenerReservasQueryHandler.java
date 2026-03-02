package es.um.atica.umufly.vuelos.application.usecase.reservas.obtenerreservas;

import java.util.Optional;

import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.QueryHandler;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloReadRepository;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

@Component
public class ObtenerReservasQueryHandler implements QueryHandler<Optional<ReservaVuelo>, ObtenerReservasQuery> {

	private final ReservasVueloReadRepository reservasVueloReadRepository;

	public ObtenerReservasQueryHandler( ReservasVueloReadRepository reservasVueloRepository ) {
		this.reservasVueloReadRepository = reservasVueloRepository;
	}

	@Override
	public Optional<ReservaVuelo> handle( ObtenerReservasQuery query ) throws Exception {
		return Optional.of( reservasVueloReadRepository.findReservaById( query.getDocumentoIdentidad(), query.getIdReserva() ) );
	}

}
