package es.um.atica.umufly.parking.application.usecase.crearreservas;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umufly.parking.application.port.ReservasParkingWritePort;
import es.um.atica.umufly.parking.application.port.ReservasParkingWriteRepository;
import es.um.atica.umufly.parking.domain.model.ReservaParking;

@Component
public class CrearReservaCommandHandler implements SyncCommandHandler<ReservaParking, CrearReservaCommand> {

	private final ReservasParkingWriteRepository reservasParkingWriteRepository;
	private final ReservasParkingWritePort formalizacionReservasParkingPort;
	private final Clock clock;

	public CrearReservaCommandHandler( ReservasParkingWriteRepository reservasParkingWriteRepository, ReservasParkingWritePort formalizacionReservasParkingPort,
			Clock clock ) {
		this.reservasParkingWriteRepository = reservasParkingWriteRepository;
		this.formalizacionReservasParkingPort = formalizacionReservasParkingPort;
		this.clock = clock;
	}

	@Override
	public ReservaParking handle( CrearReservaCommand command ) throws Exception {
		// 1. Creamos y persistimos la reserva
		ReservaParking reserva = ReservaParking.solicitarReserva( command.getDocumentoIdentidadTitular(), command.getPeriodoEstacionamiento(), LocalDateTime.now( clock ) );
		reservasParkingWriteRepository.persistirReserva( reserva );

		// 2. Formalizamos la reserva llamando al backoffice para que se haga eco de la nueva reserva que acabamos de crear
		UUID idReservaFormalizada = formalizacionReservasParkingPort.formalizarReservaParking( reserva );
		reserva.formalizarReserva();
		reservasParkingWriteRepository.persistirFormalizacionReserva( reserva.getId(), idReservaFormalizada );

		return reserva;
	}

}
