package es.um.atica.umufly.parkings.domain.model;


public record Descuento( int descuento ) {

	public Descuento {
		if ( descuento < 0 || descuento > 100 ) {
			throw new IllegalArgumentException( "El descuento debe estar entre 0 y 100" );
		}
	}
}
