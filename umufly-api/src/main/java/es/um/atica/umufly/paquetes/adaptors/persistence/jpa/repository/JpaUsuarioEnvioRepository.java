package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.um.atica.umufly.paquetes.domain.model.UsuarioEnvio;

public interface JpaUsuarioEnvioRepository extends JpaRepository<UsuarioEnvio, String> {

}
