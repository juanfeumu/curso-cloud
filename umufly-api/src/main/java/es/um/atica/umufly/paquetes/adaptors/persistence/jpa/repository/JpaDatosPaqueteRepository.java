package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.DatosPaqueteEntity;

public interface JpaDatosPaqueteRepository extends JpaRepository<DatosPaqueteEntity, String> {

}
