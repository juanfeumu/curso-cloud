package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.mapper;

import java.util.UUID;

import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.DatosPaqueteEntity;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.EnvioPaqueteEntity;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.EstadoEnvioEnum;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.TipoParticipanteEnum;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.UsuarioEnvioEntity;
import es.um.atica.umufly.paquetes.domain.model.CorreoElectronico;
import es.um.atica.umufly.paquetes.domain.model.DatosPaquete;
import es.um.atica.umufly.paquetes.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;
import es.um.atica.umufly.paquetes.domain.model.EstadoEnvio;
import es.um.atica.umufly.paquetes.domain.model.ImporteEnvioPaquete;
import es.um.atica.umufly.paquetes.domain.model.NombreCompleto;
import es.um.atica.umufly.paquetes.domain.model.Telefono;
import es.um.atica.umufly.paquetes.domain.model.TipoDocumento;
import es.um.atica.umufly.paquetes.domain.model.UsuarioEnvio;
import es.um.atica.umufly.shared.adaptors.persistence.jpa.entity.TipoDocumentoEnum;

public class JpaPersistenceMapper {

	public static EnvioPaquete envioPaqueteToModel( EnvioPaqueteEntity entity, UsuarioEnvioEntity remitente, UsuarioEnvioEntity destinatario, DatosPaqueteEntity datosPaquete ) {
		// UUID idEnvio, UUID idVuelo, UsuarioEnvio remitente, UsuarioEnvio destinatario, DatosPaquete paquete, UUID
		// idSeguimiento, ImporteEnvioPaquete importe, EstadoEnvio estado
		return EnvioPaquete.of( UUID.fromString( entity.getIdEnvio() ), UUID.fromString( entity.getIdVuelo() ), usuarioEnvioToModel( remitente ), usuarioEnvioToModel( destinatario ), datosPaqueteToMOdel( datosPaquete ),
				UUID.fromString( entity.getIdSeguimiento() ), new ImporteEnvioPaquete( entity.getImporteEnvio() ), estadoEnvioToModel( entity.getEstado() ) );
	}

	public static EnvioPaqueteEntity envioPaqueteToEntity( EnvioPaquete envioPaquete ) {
		EnvioPaqueteEntity entity = new EnvioPaqueteEntity();
		entity.setIdEnvio( envioPaquete.getIdEnvio().toString() );
		entity.setIdVuelo( envioPaquete.getIdVuelo().toString() );
		entity.setIdSeguimiento( envioPaquete.getIdSeguimiento().toString() );
		entity.setRemitente( usuarioEnvioToEntity( envioPaquete.getRemitente(), TipoParticipanteEnum.REMITENTE ) );
		entity.setDestinatario( usuarioEnvioToEntity( envioPaquete.getDestinatario(), TipoParticipanteEnum.DESTINATARIO ) );
		entity.setImporteEnvio( envioPaquete.getImporte().valor() );
		entity.setEstado( estadoEnvioToEntity( envioPaquete.getEstado() ) );
		return entity;
	}

	public static UsuarioEnvio usuarioEnvioToModel( UsuarioEnvioEntity entity ) {
		// UUID idUsuario, DocumentoIdentidad identificador, NombreCompleto nombre, CorreoElectronico correo, Telefono telefono
		return UsuarioEnvio.of( UUID.fromString( entity.getIdParticipante() ), new DocumentoIdentidad( tipoDocumentoEntityToModel( entity.getTipoDocumento() ), entity.getNumeroDocumento() ), nombreToModel( entity.getNombre(), entity.getApellidos() ),
				new CorreoElectronico( entity.getEmail() ), new Telefono( entity.getTelefono() ) );
	}

	public static UsuarioEnvioEntity usuarioEnvioToEntity( UsuarioEnvio usuarioEnvio, TipoParticipanteEnum tipoParticipante ) {
		UsuarioEnvioEntity entity = new UsuarioEnvioEntity();
		entity.setIdParticipante( usuarioEnvio.getIdUsuario().toString() );
		entity.setTipoParticipante( tipoParticipante );
		entity.setTipoDocumento( tipoDocumentoToEntity( usuarioEnvio.getIdentificador().tipo() ) );
		entity.setNumeroDocumento( usuarioEnvio.getIdentificador().identificador() );
		entity.setNombre( usuarioEnvio.getNombre().nombre() );
		entity.setApellidos( usuarioEnvio.getNombre().apellidos() );
		entity.setEmail( usuarioEnvio.getCorreo().valor() );
		entity.setTelefono( usuarioEnvio.getTelefono().valor() );
		return entity;
	}

	public static DatosPaquete datosPaqueteToMOdel( DatosPaqueteEntity entity ) {
		return new DatosPaquete( UUID.fromString( entity.getIdPaquete() ), entity.getDescripcion(), entity.getPesoKg(), "N".equals( entity.getFragil() ) );
	}

	public static DatosPaqueteEntity datosPaqueteToEntity( DatosPaquete datosPaquete ) {
		DatosPaqueteEntity entity = new DatosPaqueteEntity();
		entity.setDescripcion( datosPaquete.descripcion() );
		entity.setFragil( Boolean.TRUE.equals( datosPaquete.fragil() ) ? "S" : "N" );
		entity.setIdPaquete( datosPaquete.idPaquete().toString() );
		entity.setPesoKg( datosPaquete.peso() );
		return entity;
	}

	private static TipoDocumento tipoDocumentoEntityToModel( TipoDocumentoEnum tipoDocumento ) {
		return switch ( tipoDocumento ) {
			case N -> TipoDocumento.NIF;
			case E -> TipoDocumento.NIE;
			case P -> TipoDocumento.PASAPORTE;
			default -> throw new IllegalArgumentException( "Unexpected value: " + tipoDocumento );
		};
	}

	public static TipoDocumentoEnum tipoDocumentoToEntity( TipoDocumento tipoDocumento ) {
		return switch ( tipoDocumento ) {
			case NIF -> TipoDocumentoEnum.N;
			case NIE -> TipoDocumentoEnum.E;
			case PASAPORTE -> TipoDocumentoEnum.P;
			default -> throw new IllegalArgumentException( "Unexpected value: " + tipoDocumento );
		};
	}

	private static NombreCompleto nombreToModel( String nombre, String apellidos ) {
		return new NombreCompleto( nombre, apellidos );
	}

	private static EstadoEnvio estadoEnvioToModel( EstadoEnvioEnum entity ) {
		return switch ( entity ) {
			case FACTURADO -> EstadoEnvio.FACTURADO;
			case EN_TRANSITO -> EstadoEnvio.EN_TRANSITO;
			case ENTREGADO -> EstadoEnvio.ENTREGADO;
			default -> null;
		};
	}

	private static EstadoEnvioEnum estadoEnvioToEntity( EstadoEnvio estado ) {
		return switch ( estado ) {
			case FACTURADO -> EstadoEnvioEnum.FACTURADO;
			case EN_TRANSITO -> EstadoEnvioEnum.EN_TRANSITO;
			case ENTREGADO -> EstadoEnvioEnum.ENTREGADO;
			default -> null;
		};
	}

}
