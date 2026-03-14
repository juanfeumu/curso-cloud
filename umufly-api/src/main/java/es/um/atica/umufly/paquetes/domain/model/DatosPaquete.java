package es.um.atica.umufly.paquetes.domain.model;

public record DatosPaquete( String descripcion, Double peso, Boolean fragil ) {

	public DatosPaquete {
		if ( descripcion == null || descripcion.isBlank() ) {
			throw new IllegalArgumentException( "La descripción no puede ser nula" );
		}
		if ( peso == null || peso <= 0 ) {
			throw new IllegalArgumentException( "El peso no puede igual o menor a 0" );
		}
		if ( fragil == null ) {
			throw new IllegalArgumentException( "Debe indicarse si es o no fragil" );
		}
	}
}
