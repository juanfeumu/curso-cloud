package es.um.atica.umufly.parking.application.usecase.cancelarreservas;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umufly.parking.application.port.ReservasParkingReadRepository;
import es.um.atica.umufly.parking.application.port.ReservasParkingWritePort;
import es.um.atica.umufly.parking.application.port.ReservasParkingWriteRepository;
import es.um.atica.umufly.parking.domain.model.ReservaParking;

@Component
public class CancelarReservaCommandHandler implements SyncCommandHandler<ReservaParking, CancelarReservaCommand> {

	private final ReservasParkingReadRepository reservasParkingReadRepository;
	private final ReservasParkingWriteRepository reservasParkingWriteRepository;
	private final ReservasParkingWritePort formalizacionReservasParkingPort;
	private final Clock clock;

	public CancelarReservaCommandHandler( ReservasParkingReadRepository reservasparkingRepository, ReservasParkingWriteRepository reservasParkingWriteRepository, ReservasParkingWritePort formalizacionReservasParkingPort, Clock clock ) {
		this.reservasParkingReadRepository = reservasparkingRepository;
		this.reservasParkingWriteRepository = reservasParkingWriteRepository;
		this.formalizacionReservasParkingPort = formalizacionReservasParkingPort;
		this.clock = clock;
	}

	@Override
	public ReservaParking handle( CancelarReservaCommand command ) throws Exception {
		// 1. Recuperamos la reserva
		ReservaParking reserva = reservasParkingReadRepository.findReservaById( command.getDocumentoIdentidadTitular(), command.getIdReserva() );

		// 2. Cancelamos la reserva en el fronOffice
		reserva.cancelarReserva( LocalDateTime.now( clock ) );
		reservasParkingWriteRepository.cancelReserva( reserva.getId() );

		// 3. Cancelamos la reserva llamando al backoffice para que se haga eco de la cancelacion
		formalizacionReservasParkingPort.cancelarReservaParking( command.getDocumentoIdentidadTitular(), command.getIdReserva() );

		return reserva;
	}

}
