package es.um.atica.umufly.vuelos.application.usecase.reservas.crearreservas;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umufly.vuelos.application.port.FormalizacionReservasVueloWritePort;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloReadRepository;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloWriteRepository;
import es.um.atica.umufly.vuelos.application.port.VuelosReadRepository;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;
import es.um.atica.umufly.vuelos.domain.model.Vuelo;

@Component
public class CreaReservaQueryHandler implements SyncCommandHandler<ReservaVuelo, CreaReservaCommand> {

	private final VuelosReadRepository vuelosReadRepository;
	private final ReservasVueloReadRepository reservasVueloReadRepository;
	private final ReservasVueloWriteRepository reservasVueloWriteRepository;
	private final FormalizacionReservasVueloWritePort formalizacionReservasVueloPort;
	private final Clock clock;

	public CreaReservaQueryHandler( VuelosReadRepository vuelosRepository, ReservasVueloReadRepository reservasVueloRepository, ReservasVueloWriteRepository reservasVueloWriteRepository, FormalizacionReservasVueloWritePort formalizacionReservasVueloPort,
			Clock clock ) {
		this.vuelosReadRepository = vuelosRepository;
		this.reservasVueloReadRepository = reservasVueloRepository;
		this.reservasVueloWriteRepository = reservasVueloWriteRepository;
		this.formalizacionReservasVueloPort = formalizacionReservasVueloPort;
		this.clock = clock;
	}

	@Override
	public ReservaVuelo handle( CreaReservaCommand query ) throws Exception {
		// 1. Recuperar el vuelo
		Vuelo vuelo = vuelosReadRepository.findVuelo( query.getIdVuelo() );

		// 2. Recuperar el número de reservas del pasajero en el vuelo
		int numeroReservasPasajeroEnVuelo = reservasVueloReadRepository.countReservasByIdVueloAndPasajero( query.getIdVuelo(), query.getPasajero() );

		// 3. Recuperar el número de plazas disponibles en el avión
		int numeroPlazasDisponiblesAvion = vuelosReadRepository.plazasDisponiblesEnVuelo( vuelo );

		// 4. Creamos y persistimos la reserva de vuelo
		ReservaVuelo reservaVuelo = ReservaVuelo.solicitarReserva( query.getDocumentoIdentidadTitular(), query.getPasajero(), vuelo, query.getClaseAsiento(), LocalDateTime.now( clock ), numeroReservasPasajeroEnVuelo, numeroPlazasDisponiblesAvion );
		reservasVueloWriteRepository.persistirReserva( reservaVuelo );

		// 5. Formalizamos la reserva llamando al backoffice para que se haga eco de la nueva reserva que acabamos de crear
		UUID idReservaFormalizada = formalizacionReservasVueloPort.formalizarReservaVuelo( reservaVuelo );
		reservaVuelo.formalizarReserva();
		reservasVueloWriteRepository.persistirFormalizacionReserva( reservaVuelo.getId(), idReservaFormalizada );

		return reservaVuelo;
	}

}
