package es.um.atica.umufly.parking.adaptors.api.rest;

import java.util.UUID;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.um.atica.umufly.parking.adaptors.api.rest.dto.ReservaParkingDTO;
import es.um.atica.umufly.parking.adaptors.api.rest.mapper.ApiRestMapper;
import es.um.atica.umufly.parking.domain.model.ReservaParking;

@Component
public class ParkingModelAssembler implements RepresentationModelAssembler<ReservaParking, ReservaParkingDTO> {

	private final LinkService linkService;

	public ParkingModelAssembler( LinkService linkService ) {
		this.linkService = linkService;
	}

	@Override
	public ReservaParkingDTO toModel( ReservaParking entity ) {
		ReservaParkingDTO reservaParking = ApiRestMapper.reservaParkingToDTO( entity );
		reservaParking.add( linkSelf( entity.getId() ) );
		return reservaParking;
	}

	private Link linkSelf( UUID idReserva ) {
		return Link.of( linkService.privateApi().path( Constants.RESOURCE_PARKINGS ).pathSegment( idReserva.toString() ).build().toString() );
	}

}
