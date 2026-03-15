package es.um.atica.umufly.paquetes.domain.model;

public record NombreCompleto( String nombre, String apellidos ) {

	public NombreCompleto {
		if ( nombre == null || nombre.isBlank() ) {
			throw new IllegalArgumentException( "Es obligatorio indicar el nombre" );
		}
		if ( apellidos == null || apellidos.isBlank() ) {
			throw new IllegalArgumentException( "Es obligatorio indicar el apellidos" );
		}
	}
}
