package es.um.atica.umufly.paquetes.domain.model;

import java.util.regex.Pattern;

public record Telefono( String valor ) {

	private static final String PATRON_TELEFONO = "[8,9]\\d{8}|\\\\+(34)[8,9]\\\\d{8}|\\\\+(?!34)\\\\d{6,15}";

	public Telefono {
		if ( valor == null || valor.isBlank() ) {
			throw new IllegalArgumentException( "El telefono no puede ser nulo" );
		}
		if ( !Pattern.matches( PATRON_TELEFONO, valor ) ) {
			throw new IllegalArgumentException( "El telefono no tiene un formato válido" );
		}
	}

}
