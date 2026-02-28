package es.um.atica.umufly.parking.application.port;

import java.util.UUID;

import es.um.atica.umufly.parking.domain.model.ReservaParking;

public interface ReservasParkingWriteRepository {

	void persistirReserva( ReservaParking reservaParking );

	void persistirFormalizacionReserva( UUID idReserva, UUID idReservaFormalizada );

	void cancelReserva( UUID idReserva );

}
