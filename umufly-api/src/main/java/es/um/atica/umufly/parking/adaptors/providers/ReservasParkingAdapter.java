package es.um.atica.umufly.parking.adaptors.providers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import es.um.atica.umufly.parking.adaptors.providers.muchovuelo.MuchoVueloClient;
import es.um.atica.umufly.parking.adaptors.providers.muchovuelo.dto.ReservaParkingDTO;
import es.um.atica.umufly.parking.adaptors.providers.muchovuelo.mapper.MuchoVueloMapper;
import es.um.atica.umufly.parking.application.port.ReservasParkingWritePort;
import es.um.atica.umufly.parking.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.parking.domain.model.ReservaParking;

@Component
public class ReservasParkingAdapter implements ReservasParkingWritePort {

	private final MuchoVueloClient muchoVueloClient;

	public ReservasParkingAdapter( MuchoVueloClient muchoVueloClient ) {
		this.muchoVueloClient = muchoVueloClient;
	}

	@Override
	public UUID formalizarReservaParking( ReservaParking reserva ) {
		ReservaParkingDTO reservaParkingMuchoVuelo = muchoVueloClient.creaReservaParking( MuchoVueloMapper.reservaToDTO( reserva ) );
		return reservaParkingMuchoVuelo.getId();
	}

	@Override
	public void cancelarReservaParking( DocumentoIdentidad documentoIdentidadTitular, UUID idReserva ) {
		muchoVueloClient.cancelarReservaParking( documentoIdentidadTitular, idReserva );
	}

}
