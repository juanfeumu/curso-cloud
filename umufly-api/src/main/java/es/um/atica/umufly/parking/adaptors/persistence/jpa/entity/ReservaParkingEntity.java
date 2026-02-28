package es.um.atica.umufly.parking.adaptors.persistence.jpa.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table( name = "RESERVA_PARKING", schema = "FORMACION_TICARUM" )
public class ReservaParkingEntity {

	@Id
	@NotNull
	@Column( name = "ID", nullable = false, length = 36 )
	private String id;

	@NotNull
	@Column( name = "TIPO_DOCUMENTO_CLIENTE", length = 2, nullable = false )
	@Enumerated( value = EnumType.STRING )
	private TipoDocumentoEnum tipoDocumentoCliente;

	@NotNull
	@Column( name = "NUMERO_DOCUMENTO_CLIENTE", length = 15, nullable = false )
	private String numeroDocumentoCliente;

	@NotNull
	@Column( name = "FECHA_RESERVA", nullable = false )
	private LocalDateTime fechaReserva;

	@NotNull
	@Column( name = "FECHA_MODIFICACION", nullable = false )
	private LocalDateTime fechaModificacion;

	@NotNull
	@Column( name = "ESTADO_RESERVA", length = 2, nullable = false )
	@Enumerated( value = EnumType.STRING )
	private EstadoReservaParkingEnum estadoReserva;

	@Column( name = "ID_RESERVA_FORMALIZADA", length = 36, nullable = true )
	private String idReservaFormalizada;

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public TipoDocumentoEnum getTipoDocumentoCliente() {
		return tipoDocumentoCliente;
	}

	public void setTipoDocumentoCliente( TipoDocumentoEnum tipoDocumentoCliente ) {
		this.tipoDocumentoCliente = tipoDocumentoCliente;
	}

	public String getNumeroDocumentoCliente() {
		return numeroDocumentoCliente;
	}

	public void setNumeroDocumentoCliente( String numeroDocumentoCliente ) {
		this.numeroDocumentoCliente = numeroDocumentoCliente;
	}

	public LocalDateTime getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva( LocalDateTime fechaReserva ) {
		this.fechaReserva = fechaReserva;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion( LocalDateTime fechaModificacion ) {
		this.fechaModificacion = fechaModificacion;
	}

	public EstadoReservaParkingEnum getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva( EstadoReservaParkingEnum estadoReserva ) {
		this.estadoReserva = estadoReserva;
	}

	public String getIdReservaFormalizada() {
		return idReservaFormalizada;
	}

	public void setIdReservaFormalizada( String idReservaFormalizada ) {
		this.idReservaFormalizada = idReservaFormalizada;
	}

}
