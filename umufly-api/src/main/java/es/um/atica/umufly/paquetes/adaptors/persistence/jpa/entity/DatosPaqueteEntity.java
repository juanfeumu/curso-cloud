package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "muchovuelo_paquete_envio")
public class DatosPaqueteEntity {

	@Id
	@Column(name = "id_paquete", nullable = false, length = 100)
	private String idPaquete;

	@NotNull
	@Size(max = 500)
	@Column(name = "descripcion", nullable = false, length = 500)
	private String descripcion;

	@NotNull
	@Column( name = "peso_kg", nullable = false )
	private Double pesoKg;

	@NotNull
	@Column(name = "fragil", nullable = false, length = 1)
	private String fragil = "N";

	// Getters and Setters

	public String getIdPaquete() {
		return idPaquete;
	}

	public void setIdPaquete(String idPaquete) {
		this.idPaquete = idPaquete;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPesoKg() {
		return pesoKg;
	}

	public void setPesoKg(Double pesoKg) {
		this.pesoKg = pesoKg;
	}

	public String getFragil() {
		return fragil;
	}

	public void setFragil(String fragil) {
		this.fragil = fragil;
	}

	// Override equals and hashCode if necessary (e.g., for collections)

	@Override
	public String toString() {
		return "DatosPaqueteEntity{" +
				"idPaquete='" + idPaquete + '\'' +
				", descripcion='" + descripcion + '\'' +
				", pesoKg=" + pesoKg +
				", fragil='" + fragil + '\'' +
				'}';
	}
}