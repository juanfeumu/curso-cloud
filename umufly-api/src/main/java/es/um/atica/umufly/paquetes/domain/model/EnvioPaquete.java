package es.um.atica.umufly.paquetes.domain.model;

import java.util.UUID;

public class EnvioPaquete {

	private UUID idEnvio;
	private UUID idVuelo;
	private UsuarioEnvio remitente;
	private UsuarioEnvio destinatario;
	private DatosPaquete paquete;
	private UUID idSeguimiento;
	private ImporteEnvioPaquete importe;
	private EstadoEnvio estado;

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

	public UUID getIdSeguimiento() {
		return idSeguimiento;
	}

	public ImporteEnvioPaquete getImporte() {
		return importe;
	}

	public EstadoEnvio getEstado() {
		return estado;
	}

	private EnvioPaquete( UUID idEnvio, UUID idVuelo, UsuarioEnvio remitente, UsuarioEnvio destinatario, DatosPaquete paquete, UUID idSeguimiento, ImporteEnvioPaquete importe, EstadoEnvio estado ) {
		this.idEnvio = idEnvio;
		this.idVuelo = idVuelo;
		this.remitente = remitente;
		this.destinatario = destinatario;
		this.paquete = paquete;
		this.idSeguimiento = idSeguimiento;
		this.importe = importe;
		this.estado = estado;
	}

	public static EnvioPaquete of( UUID idEnvio, UUID idVuelo, UsuarioEnvio remitente, UsuarioEnvio destinatario, DatosPaquete paquete, UUID idSeguimiento, ImporteEnvioPaquete importe, EstadoEnvio estado ) {
		return new EnvioPaquete( idEnvio, idVuelo, remitente, destinatario, paquete, idSeguimiento, importe, estado );
	}

	public static EnvioPaquete enviarPaquete( UUID idVuelo, UsuarioEnvio remitente, UsuarioEnvio destinatario, DatosPaquete paquete ) {
		if ( idVuelo == null ) {
			throw new IllegalArgumentException( "Debe indicar el id del vuelo" );
		}
		if ( remitente == null ) {
			throw new IllegalArgumentException( "Debe indicar el remitente" );
		}
		if ( destinatario == null ) {
			throw new IllegalArgumentException( "Debe indicar el destinatario" );
		}
		if ( paquete == null ) {
			throw new IllegalArgumentException( "Debe indicar el paquete" );
		}
		UUID idEnvio = UUID.randomUUID();
		UUID idSeguimiento = UUID.randomUUID();
		EstadoEnvio estado = EstadoEnvio.ENTREGADO;

		// Aqui debemos persistir el envio del paquete

		return EnvioPaquete.of( idEnvio, idVuelo, remitente, destinatario, paquete, idSeguimiento, null, estado );
	}

	public void calcularImporte( TarifaEnvio tarifaEnvio ) {
		if ( tarifaEnvio == null ) {
			throw new IllegalArgumentException( "Debe indicar una tarifa a aplicar" );
		}
		this.importe = new ImporteEnvioPaquete( paquete.peso() * TarifaEnvio.ofIsFragil( paquete.fragil() ).getImporteKilo() );
	}

}
