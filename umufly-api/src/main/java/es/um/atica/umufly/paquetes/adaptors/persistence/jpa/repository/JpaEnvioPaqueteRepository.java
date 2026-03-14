package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;

public interface JpaEnvioPaqueteRepository extends JpaRepository<EnvioPaquete, String> {

}
