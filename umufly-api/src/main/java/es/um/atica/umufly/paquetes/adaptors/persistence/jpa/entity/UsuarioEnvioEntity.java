package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity;

import java.util.Objects;

import es.um.atica.umufly.shared.adaptors.persistence.jpa.entity.TipoDocumentoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table( name = "muchovuelo_participante_envio" )
public class UsuarioEnvioEntity {

	@Id
	@Column( name = "id_participante", nullable = false, length = 100 )
	private String idParticipante;

	@NotNull
	@Column( name = "nombre", nullable = false, length = 100 )
	private String nombre;

	@NotNull
	@Column( name = "apellidos", nullable = false, length = 150 )
	private String apellidos;

	@NotNull
	@Column( name = "numero_documento", nullable = false, length = 15 )
	private String numeroDocumento;

	@NotNull
	@Column( name = "tipo_documento", nullable = false, length = 2 )
	@Enumerated( value = EnumType.STRING )
	private TipoDocumentoEnum tipoDocumento;

	@NotNull
	@Column( name = "email", nullable = false, length = 250 )
	private String email;

	@Column( name = "telefono", length = 30 )
	private String telefono;

	@NotNull
	@Column( name = "tipo_participante", nullable = false, length = 20 )
	@Enumerated( value = EnumType.STRING )
	private TipoParticipanteEnum tipoParticipante;

	public String getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante( String idParticipante ) {
		this.idParticipante = idParticipante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre( String nombre ) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos( String apellidos ) {
		this.apellidos = apellidos;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento( String numeroDocumento ) {
		this.numeroDocumento = numeroDocumento;
	}

	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento( TipoDocumentoEnum tipoDocumento ) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono( String telefono ) {
		this.telefono = telefono;
	}

	public TipoParticipanteEnum getTipoParticipante() {
		return tipoParticipante;
	}

	public void setTipoParticipante( TipoParticipanteEnum tipoParticipante ) {
		this.tipoParticipante = tipoParticipante;
	}

	@Override
	public int hashCode() {
		return Objects.hash( idParticipante );
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null || getClass() != obj.getClass() ) {
			return false;
		}
		UsuarioEnvioEntity other = ( UsuarioEnvioEntity ) obj;
		return Objects.equals( idParticipante, other.idParticipante );
	}

	@Override
	public String toString() {
		return "UsuarioEnvioEntity{" + "idParticipante='" + idParticipante + '\'' + ", nombre='" + nombre + '\'' + ", apellidos='" + apellidos + '\'' + ", numeroDocumento='" + numeroDocumento + '\'' + ", tipoDocumento='" + tipoDocumento + '\'' + ", email='"
				+ email + '\'' + ", telefono='" + telefono + '\'' + ", tipoParticipante='" + tipoParticipante + '\'' + '}';
	}
}