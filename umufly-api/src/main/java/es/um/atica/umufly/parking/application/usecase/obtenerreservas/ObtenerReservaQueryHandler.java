package es.um.atica.umufly.parking.application.usecase.obtenerreservas;

import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.QueryHandler;
import es.um.atica.umufly.parking.application.port.ReservasParkingReadRepository;
import es.um.atica.umufly.parking.domain.model.ReservaParking;

@Component
public class ObtenerReservaQueryHandler implements QueryHandler<ReservaParking, ObtenerReservaQuery> {

	private final ReservasParkingReadRepository reservasParkingRepository;

	public ObtenerReservaQueryHandler( ReservasParkingReadRepository reservasParkingRepository ) {
		this.reservasParkingRepository = reservasParkingRepository;
	}

	@Override
	public ReservaParking handle( ObtenerReservaQuery query ) throws Exception {
		return reservasParkingRepository.findReservaById( query.getDocumentoIdentidad(), query.getIdReserva() );
	}
}