package es.um.atica.umufly.parkings.application.usecase.cancelarresservasparking;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umufly.parkings.domain.model.ReservaParking;

public class CancelarReservaParkingCommandHandler implements SyncCommandHandler<ReservaParking, CancelarReservaParkingCommand> {

	@Override
	public ReservaParking handle( CancelarReservaParkingCommand command ) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
