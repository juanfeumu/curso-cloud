package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.EnvioPaqueteEntity;
import es.um.atica.umufly.shared.adaptors.persistence.jpa.entity.TipoDocumentoEnum;

@Component
public interface JpaEnvioPaqueteRepository extends JpaRepository<EnvioPaqueteEntity, String> {

	Page<EnvioPaqueteEntity> findRemitenteTipoDocumentoAndRemitenteNumeroDocumento( TipoDocumentoEnum tipoDocumento, String numeroDocumento, PageRequest pagina );

}
