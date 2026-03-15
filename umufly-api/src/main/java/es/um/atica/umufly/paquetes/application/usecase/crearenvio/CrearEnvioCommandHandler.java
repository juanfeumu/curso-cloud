package es.um.atica.umufly.paquetes.application.usecase.crearenvio;

import java.util.UUID;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.repository.JpaEnvioPaqueteRepository;
import es.um.atica.umufly.paquetes.domain.model.DatosPaquete;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;
import es.um.atica.umufly.paquetes.domain.model.UsuarioEnvio;

public class CrearEnvioCommandHandler implements SyncCommandHandler<EnvioPaquete, CrearEnvioCommand> {

	private final JpaEnvioPaqueteRepository jpaEnvioPaqueteRepository;

	public CrearEnvioCommandHandler( JpaEnvioPaqueteRepository jpaEnvioPaqueteRepository ) {
		this.jpaEnvioPaqueteRepository = jpaEnvioPaqueteRepository;
	}

	@Override
	public EnvioPaquete handle( CrearEnvioCommand command ) throws Exception {
		UUID idEnvio = command.getIdEnvio();
		UUID idVuelo = command.getIdVuelo();
		UsuarioEnvio remitente = command.getRemitente();
		UsuarioEnvio destinatario = command.getDestinatario();
		DatosPaquete paquete = command.getPaquete();

		return null;
	}

}
