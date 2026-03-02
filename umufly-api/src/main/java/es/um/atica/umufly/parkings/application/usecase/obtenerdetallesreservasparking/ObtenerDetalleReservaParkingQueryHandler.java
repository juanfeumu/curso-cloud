package es.um.atica.umufly.parkings.application.usecase.obtenerdetallesreservasparking;

import java.util.Optional;

import es.um.atica.fundewebjs.umubus.domain.cqrs.QueryHandler;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

public class ObtenerDetalleReservaParkingQueryHandler implements QueryHandler<Optional<ReservaVuelo>, ObtenerDetalleReservaParkingQuery> {

	@Override
	public Optional<ReservaVuelo> handle( ObtenerDetalleReservaParkingQuery query ) throws Exception {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
