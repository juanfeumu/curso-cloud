package es.um.atica.umufly.vuelos.application.usecase.reservas.cancelarreservas;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umufly.vuelos.application.port.FormalizacionReservasVueloWritePort;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloReadRepository;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloWriteRepository;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

@Component
public class CancelarReservasCommandHandler implements SyncCommandHandler<ReservaVuelo, CancelarReservasCommand> {

	private final ReservasVueloReadRepository reservasVueloReadRepository;
	private final ReservasVueloWriteRepository reservasVueloWriteRepository;
	private final FormalizacionReservasVueloWritePort formalizacionReservasVueloPort;
	private final Clock clock;

	public CancelarReservasCommandHandler( ReservasVueloReadRepository reservasVueloRepository, ReservasVueloWriteRepository reservasVueloWriteRepository, FormalizacionReservasVueloWritePort formalizacionReservasVueloPort, Clock clock ) {
		this.reservasVueloReadRepository = reservasVueloRepository;
		this.reservasVueloWriteRepository = reservasVueloWriteRepository;
		this.formalizacionReservasVueloPort = formalizacionReservasVueloPort;
		this.clock = clock;
	}

	@Override
	public ReservaVuelo handle( CancelarReservasCommand command ) throws Exception {
		// 1. Recuperamos la reserva
		ReservaVuelo reservaVuelo = reservasVueloReadRepository.findReservaById( command.getDocumentoIdentidadTitular(), command.getIdReserva() );

		// 2. Cancelamos la reserva en el fronOffice
		reservaVuelo.cancelarReserva( LocalDateTime.now( clock ) );
		reservasVueloWriteRepository.cancelReserva( reservaVuelo.getId() );

		// 3. Cancelamos la reserva llamando al backoffice para que se haga eco de la cancelacion
		UUID idReservaFormalizada = reservasVueloReadRepository.findIdFormalizadaByReservaById( command.getIdReserva() );
		formalizacionReservasVueloPort.cancelarReservaVuelo( command.getDocumentoIdentidadTitular(), idReservaFormalizada );

		return reservaVuelo;
	}

}
