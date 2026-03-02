package es.um.atica.umufly.parkings.application.usecase.solicitarreservasparking;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umufly.parkings.domain.model.ReservaParking;

public class SolicitarReservaParkingCommandHandler implements SyncCommandHandler<ReservaParking, SolicitarReservaParkingCommand> {

	@Override
	public ReservaParking handle( SolicitarReservaParkingCommand command ) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
