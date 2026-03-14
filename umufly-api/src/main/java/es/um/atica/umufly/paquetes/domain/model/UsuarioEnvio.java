package es.um.atica.umufly.paquetes.domain.model;

import java.util.UUID;

public class UsuarioEnvio {

	private UUID idUsuario;
	private NombreCompleto nombre;
	private DocumentoIdentidad identificador;
	private CorreoElectronico correo;
	private Telefono telefono;

	private UsuarioEnvio( UUID idUsuario, DocumentoIdentidad identificador, NombreCompleto nombre, CorreoElectronico correo, Telefono telefono ) {
		this.idUsuario = idUsuario;
		this.identificador = identificador;
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
	}

	public static UsuarioEnvio of( UUID idUsuario, DocumentoIdentidad identificador, NombreCompleto nombre, CorreoElectronico correo, Telefono telefono ) {
		if ( idUsuario == null ) {
			throw new IllegalArgumentException( "El ID del usuario no puede ser nulo" );
		}
		if ( identificador == null ) {
			throw new IllegalArgumentException( "El identificador del usuario no puede ser nulo" );
		}
		if ( nombre == null ) {
			throw new IllegalArgumentException( "El nombre del usuario no puede ser nulo" );
		}
		if ( correo == null ) {
			throw new IllegalArgumentException( "El correo del usuario no puede ser nulo" );
		}
		if ( telefono == null ) {
			throw new IllegalArgumentException( "El telefono del usuario no puede ser nulo" );
		}
		return new UsuarioEnvio( idUsuario, identificador, nombre, correo, telefono );
	}

	public UUID getIdUsuario() {
		return idUsuario;
	}

	public DocumentoIdentidad getIdentificador() {
		return identificador;
	}

	public NombreCompleto getNombre() {
		return nombre;
	}

	public CorreoElectronico getCorreo() {
		return correo;
	}

	public Telefono getTelefono() {
		return telefono;
	}

}
