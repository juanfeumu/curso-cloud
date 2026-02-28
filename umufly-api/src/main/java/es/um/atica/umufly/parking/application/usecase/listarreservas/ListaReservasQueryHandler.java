package es.um.atica.umufly.parking.application.usecase.listarreservas;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.QueryHandler;
import es.um.atica.umufly.parking.application.port.ReservasParkingReadRepository;
import es.um.atica.umufly.parking.domain.model.ReservaParking;

@Component
public class ListaReservasQueryHandler implements QueryHandler<Page<ReservaParking>, ListaReservasQuery> {

	private final ReservasParkingReadRepository reservasParkingRepository;

	public ListaReservasQueryHandler( ReservasParkingReadRepository reservasParkingRepository ) {
		this.reservasParkingRepository = reservasParkingRepository;
	}

	@Override
	public Page<ReservaParking> handle( ListaReservasQuery query ) throws Exception {
		return reservasParkingRepository.findReservas( query.getDocumentoIdentidad(), query.getPagina(), query.getTamanioPagina() );
	}
}
