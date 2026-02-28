package es.um.atica.umufly.parking.adaptors.api.rest;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umufly.parking.adaptors.api.rest.dto.ReservaParkingDTO;
import es.um.atica.umufly.parking.application.usecase.cancelarreservas.CancelarReservaCommand;
import es.um.atica.umufly.parking.application.usecase.cancelarreservas.CancelarReservaCommandHandler;
import es.um.atica.umufly.parking.application.usecase.crearreservas.CrearReservaCommand;
import es.um.atica.umufly.parking.application.usecase.crearreservas.CrearReservaCommandHandler;
import es.um.atica.umufly.parking.domain.model.Periodo;
import jakarta.validation.Valid;

@RestController
public class ParkingCommandEndpoint {

	private final CrearReservaCommandHandler crearReservaCommandHandler;
	private final CancelarReservaCommandHandler cancelarReservaCommandHandler;
	private final ParkingModelAssembler reservasModelAssembler;
	private final AuthService authService;

	public ParkingCommandEndpoint( CrearReservaCommandHandler crearReservaCommandHandler, CancelarReservaCommandHandler cancelarReservaCommandHandler, ParkingModelAssembler reservasModelAssembler,
			AuthService authService ) {
		this.crearReservaCommandHandler = crearReservaCommandHandler;
		this.cancelarReservaCommandHandler = cancelarReservaCommandHandler;
		this.reservasModelAssembler = reservasModelAssembler;
		this.authService = authService;
	}

	@PostMapping( Constants.PRIVATE_PREFIX + Constants.API_VERSION_1 + Constants.RESOURCE_PARKINGS )
	public ReservaParkingDTO creaReserva( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @RequestBody @Valid ReservaParkingDTO nuevaReserva ) throws Exception {
		// ( DocumentoIdentidad documentoIdentidadTitular, Periodo periodoEstacionamiento )
		return reservasModelAssembler.toModel( crearReservaCommandHandler
				.handle( CrearReservaCommand.of( authService.parseUserHeader( usuario ), new Periodo( nuevaReserva.getFechaInicio(), nuevaReserva.getFechaFin() ) ) ) );
	}

	@DeleteMapping( Constants.PRIVATE_PREFIX + Constants.API_VERSION_1 + Constants.RESOURCE_PARKINGS + Constants.ID_RESERVA )
	public ReservaParkingDTO cancelarReserva( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}
}
