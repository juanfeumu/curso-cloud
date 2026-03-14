package es.um.atica.umufly.paquetes.adaptors.persistence.jpa;


import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table( name = "muchovuelo_envio" )
public class EnvioPaqueteEntity {

	@Id
	@Column( name = "id_envio", nullable = false, length = 100 )
	private String idEnvio;

	@NotNull
	@ManyToOne
	@JoinColumn( name = "id_remitente", nullable = false )
	private UsuarioEnvioEntity remitente;

	@NotNull
	@ManyToOne
	@JoinColumn( name = "id_destinatario", nullable = false )
	private UsuarioEnvioEntity destinatario;

	@NotNull
	@ManyToOne
	@JoinColumn( name = "id_paquete", nullable = false )
	private DatosPaqueteEntity paquete;

	@NotNull
	@Size( max = 50 )
	@Column( name = "id_vuelo", nullable = false, length = 50 )
	private String idVuelo;

	@NotNull
	@Size( max = 50 )
	@Column( name = "id_seguimiento", nullable = false, length = 50, unique = true )
	private String idSeguimiento;

	@NotNull
	@Column( name = "importe_envio", nullable = false, precision = 10, scale = 2 )
	private BigDecimal importeEnvio;

	@NotNull
	@Size( max = 20 )
	@Column( name = "estado", nullable = false, length = 20 )
	private String estado;

	public String getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio( String idEnvio ) {
		this.idEnvio = idEnvio;
	}

	public UsuarioEnvioEntity getRemitente() {
		return remitente;
	}

	public void setRemitente( UsuarioEnvioEntity remitente ) {
		this.remitente = remitente;
	}

	public UsuarioEnvioEntity getDestinatario() {
		return destinatario;
	}

	public void setDestinatario( UsuarioEnvioEntity destinatario ) {
		this.destinatario = destinatario;
	}

	public DatosPaqueteEntity getPaquete() {
		return paquete;
	}

	public void setPaquete( DatosPaqueteEntity paquete ) {
		this.paquete = paquete;
	}

	public String getIdVuelo() {
		return idVuelo;
	}

	public void setIdVuelo( String idVuelo ) {
		this.idVuelo = idVuelo;
	}

	public String getIdSeguimiento() {
		return idSeguimiento;
	}

	public void setIdSeguimiento( String idSeguimiento ) {
		this.idSeguimiento = idSeguimiento;
	}

	public BigDecimal getImporteEnvio() {
		return importeEnvio;
	}

	public void setImporteEnvio( BigDecimal importeEnvio ) {
		this.importeEnvio = importeEnvio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado( String estado ) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash( idEnvio );
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null || getClass() != obj.getClass() ) {
			return false;
		}
		EnvioPaqueteEntity other = ( EnvioPaqueteEntity ) obj;
		return Objects.equals( idEnvio, other.idEnvio );
	}

	@Override
	public String toString() {
		return "EnvioPaqueteEntity{" + "idEnvio='" + idEnvio + '\'' + ", remitente=" + remitente + ", destinatario=" + destinatario + ", paquete=" + paquete + ", idVuelo='" + idVuelo + '\'' + ", idSeguimiento='" + idSeguimiento + '\'' + ", importeEnvio="
				+ importeEnvio + ", estado='" + estado + '\'' + '}';
	}
}