package es.um.atica.umufly.paquetes.application.usecase.crearenvio;

import java.util.UUID;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommand;
import es.um.atica.umufly.paquetes.domain.model.DatosPaquete;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;
import es.um.atica.umufly.paquetes.domain.model.UsuarioEnvio;

public class CrearEnvioCommand extends SyncCommand<EnvioPaquete> {

	private UUID idEnvio;
	private UUID idVuelo;
	private UsuarioEnvio remitente;
	private UsuarioEnvio destinatario;
	private DatosPaquete paquete;

	public CrearEnvioCommand( UUID idEnvio, UUID idVuelo, UsuarioEnvio remitente, UsuarioEnvio destinatario, DatosPaquete paquete ) {
		this.idEnvio = idEnvio;
		this.idVuelo = idVuelo;
		this.remitente = remitente;
		this.destinatario = destinatario;
		this.paquete = paquete;
	}

	public UUID getIdEnvio() {
		return idEnvio;
	}

	public UUID getIdVuelo() {
		return idVuelo;
	}

	public UsuarioEnvio getRemitente() {
		return remitente;
	}

	public UsuarioEnvio getDestinatario() {
		return destinatario;
	}

	public DatosPaquete getPaquete() {
		return paquete;
	}

}
