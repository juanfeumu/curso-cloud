package es.um.atica.umufly.vuelos.application.usecase.vuelos.obtenervuelos;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.QueryHandler;
import es.um.atica.umufly.vuelos.application.dto.VueloAmpliadoDTO;
import es.um.atica.umufly.vuelos.application.mapper.ApplicationMapper;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloReadRepository;
import es.um.atica.umufly.vuelos.application.port.VuelosReadRepository;
import es.um.atica.umufly.vuelos.domain.model.Vuelo;

@Component
public class ObtenerVueloQueryHandler implements QueryHandler<Optional<VueloAmpliadoDTO>, ObtenerVueloQuery> {

	private final VuelosReadRepository vuelosReadRepository;
	private final ReservasVueloReadRepository reservasVueloReadRepository;

	public ObtenerVueloQueryHandler( VuelosReadRepository vuelosRepository, ReservasVueloReadRepository reservasVueloRepository ) {
		this.vuelosReadRepository = vuelosRepository;
		this.reservasVueloReadRepository = reservasVueloRepository;
	}

	@Override
	public Optional<VueloAmpliadoDTO> handle( ObtenerVueloQuery query ) throws Exception {

		Vuelo vuelo = vuelosReadRepository.findVuelo( query.getIdVuelo() );
		UUID vueloReserva = query.getDocumentoIdentidadPasajero() != null ? reservasVueloReadRepository.findReservaIdByVueloIdAndPasajero( query.getDocumentoIdentidadPasajero(), vuelo.getId() ) : null;

		return Optional.of( ApplicationMapper.vueloToDTO( vuelo, vueloReserva ) );
	}


}
