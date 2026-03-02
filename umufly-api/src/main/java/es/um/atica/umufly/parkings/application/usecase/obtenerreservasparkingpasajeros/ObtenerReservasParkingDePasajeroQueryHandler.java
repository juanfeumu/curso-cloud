package es.um.atica.umufly.parkings.application.usecase.obtenerreservasparkingpasajeros;

import java.util.Optional;

import org.springframework.data.domain.Page;

import es.um.atica.fundewebjs.umubus.domain.cqrs.QueryHandler;
import es.um.atica.umufly.parkings.domain.model.ReservaParking;

public class ObtenerReservasParkingDePasajeroQueryHandler implements QueryHandler<Optional<Page<ReservaParking>>, ObtenerReservasParkingDePasajeroQuery> {

	@Override
	public Optional<Page<ReservaParking>> handle( ObtenerReservasParkingDePasajeroQuery query ) throws Exception {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
