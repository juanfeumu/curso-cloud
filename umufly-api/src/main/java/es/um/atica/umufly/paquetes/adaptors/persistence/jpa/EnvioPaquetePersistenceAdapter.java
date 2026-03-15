package es.um.atica.umufly.paquetes.adaptors.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.mapper.JpaPersistenceMapper;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.repository.JpaDatosPaqueteRepository;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.repository.JpaEnvioPaqueteRepository;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.repository.JpaUsuarioEnvioRepository;
import es.um.atica.umufly.paquetes.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;

@Component
public class EnvioPaquetePersistenceAdapter {

	private JpaDatosPaqueteRepository jpaDatosPaqueteRepository;
	private JpaEnvioPaqueteRepository jpaEnvioPaqueteRepository;
	private JpaUsuarioEnvioRepository jpaUsuarioEnvioRepository;

	public EnvioPaquetePersistenceAdapter( JpaDatosPaqueteRepository jpaDatosPaqueteRepository, JpaEnvioPaqueteRepository jpaEnvioPaqueteRepository, JpaUsuarioEnvioRepository jpaUsuarioEnvioRepository ) {
		this.jpaDatosPaqueteRepository = jpaDatosPaqueteRepository;
		this.jpaEnvioPaqueteRepository = jpaEnvioPaqueteRepository;
		this.jpaUsuarioEnvioRepository = jpaUsuarioEnvioRepository;
	}

	public Page<EnvioPaquete> findEnvioPaquete( DocumentoIdentidad documentoIdentidad, int pagina, int tamanioPagina ) {
		// TipoDocumentoEnum tipoDocumento, String numeroDocumento, PageRequest pagina
		return jpaEnvioPaqueteRepository.findRemitenteTipoDocumentoAndRemitenteNumeroDocumento( JpaPersistenceMapper.tipoDocumentoToEntity( documentoIdentidad.tipo() ), documentoIdentidad.identificador(), PageRequest.of( pagina, tamanioPagina ) )
				.map( e -> JpaPersistenceMapper.envioPaqueteToModel( e, e.getRemitente(), e.getDestinatario(), e.getPaquete() ) );
	}

}
