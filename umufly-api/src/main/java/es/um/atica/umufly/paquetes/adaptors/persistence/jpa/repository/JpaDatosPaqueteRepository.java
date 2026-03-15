package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.DatosPaqueteEntity;

@Component
public interface JpaDatosPaqueteRepository extends JpaRepository<DatosPaqueteEntity, String> {

}
