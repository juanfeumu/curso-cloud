package es.um.atica.umufly.parking.adaptors.api.rest;

import java.util.UUID;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umufly.parking.adaptors.api.rest.dto.ReservaParkingDTO;
import es.um.atica.umufly.parking.application.usecase.listarreservas.ListaReservasQuery;
import es.um.atica.umufly.parking.application.usecase.listarreservas.ListaReservasQueryHandler;
import es.um.atica.umufly.parking.application.usecase.obtenerreservas.ObtenerReservaQuery;
import es.um.atica.umufly.parking.application.usecase.obtenerreservas.ObtenerReservaQueryHandler;
import es.um.atica.umufly.parking.domain.model.ReservaParking;

@RestController
public class ParkingQueryEndpoint {

	private final ListaReservasQueryHandler listaReservasQueryHandler;
	private final ObtenerReservaQueryHandler obtenerReservaQueryHandler;
	private final ParkingModelAssembler reservasModelAssembler;
	private final PagedResourcesAssembler<ReservaParking> pagedResourcesAssembler;
	private final AuthService authService;

	public ParkingQueryEndpoint( ListaReservasQueryHandler listaReservasQueryHandler, ObtenerReservaQueryHandler obtenerReservaQueryHandler, ParkingModelAssembler reservasModelAssembler, PagedResourcesAssembler<ReservaParking> pagedResourcesAssembler,
			AuthService authService ) {
		this.listaReservasQueryHandler = listaReservasQueryHandler;
		this.obtenerReservaQueryHandler = obtenerReservaQueryHandler;
		this.reservasModelAssembler = reservasModelAssembler;
		this.pagedResourcesAssembler = pagedResourcesAssembler;
		this.authService = authService;
	}

	@GetMapping( Constants.PRIVATE_PREFIX + Constants.API_VERSION_1 + Constants.RESOURCE_PARKINGS )
	public CollectionModel<ReservaParkingDTO> getReservas( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @RequestParam( name = "page", defaultValue = "0" ) int page, @RequestParam( name = "size", defaultValue = "25" ) int size )
			throws Exception {
		return pagedResourcesAssembler.toModel( listaReservasQueryHandler.handle( ListaReservasQuery.of( authService.parseUserHeader( usuario ), page, page ) ), reservasModelAssembler );
	}

	@GetMapping( Constants.PRIVATE_PREFIX + Constants.API_VERSION_1 + Constants.RESOURCE_PARKINGS + Constants.ID_RESERVA )
	public ReservaParkingDTO getReserva( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( obtenerReservaQueryHandler.handle( ObtenerReservaQuery.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

}
