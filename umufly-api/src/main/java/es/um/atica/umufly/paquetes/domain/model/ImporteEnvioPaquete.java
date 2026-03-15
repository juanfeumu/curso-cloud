package es.um.atica.umufly.paquetes.domain.model;


public record ImporteEnvioPaquete( Double valor ) {

	public ImporteEnvioPaquete {
		if ( valor == null ) {
			throw new IllegalArgumentException( "El importe no puede ser nulo" );
		}

		if ( valor <= 0 ) {
			throw new IllegalAccessError( "El importe no puede ser cero o menor" );
		}
	}
}
