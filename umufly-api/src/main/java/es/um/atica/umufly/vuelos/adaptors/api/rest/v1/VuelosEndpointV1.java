package es.um.atica.umufly.vuelos.adaptors.api.rest.v1;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umufly.vuelos.adaptors.api.rest.Constants;
import es.um.atica.umufly.vuelos.adaptors.api.rest.v1.dto.VueloDTO;
import es.um.atica.umufly.vuelos.application.dto.VueloAmpliadoDTO;
import es.um.atica.umufly.vuelos.application.usecase.vuelos.listarvuelos.ListarVuelosQuery;
import es.um.atica.umufly.vuelos.application.usecase.vuelos.listarvuelos.ListarVuelosQueryHandler;

@RestController
public class VuelosEndpointV1 {

	private final ListarVuelosQueryHandler listarVuelosQueryHandler;
	private final VuelosModelAssemblerV1 vuelosModelAssemblervV1;
	private final PagedResourcesAssembler<VueloAmpliadoDTO> pagedResourcesAssembler;

	public VuelosEndpointV1( ListarVuelosQueryHandler listarVuelosQueryHandler, VuelosModelAssemblerV1 vuelosModelAssemblervV1, PagedResourcesAssembler<VueloAmpliadoDTO> pagedResourcesAssembler ) {
		this.listarVuelosQueryHandler = listarVuelosQueryHandler;
		this.vuelosModelAssemblervV1 = vuelosModelAssemblervV1;
		this.pagedResourcesAssembler = pagedResourcesAssembler;
	}

	@GetMapping( Constants.PRIVATE_PREFIX + Constants.API_VERSION_1 + Constants.RESOURCE_VUELOS )
	public CollectionModel<VueloDTO> getVuelos( @RequestParam( name = "page", defaultValue = "0" ) int page, @RequestParam( name = "size", defaultValue = "25" ) int size ) throws Exception {
		Optional<Page<VueloAmpliadoDTO>> pageVuelos = listarVuelosQueryHandler.handle( ListarVuelosQuery.of( null, page, size ) );
		if ( pageVuelos.isPresent() ) {
			return pagedResourcesAssembler.toModel( pageVuelos.get(), vuelosModelAssemblervV1 );
		}
		return null;
	}
}
