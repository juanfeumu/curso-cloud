package es.um.atica.umufly.parkings.domain.model;

import java.math.BigDecimal;

public record Importe( BigDecimal valor ) {

	public Importe {
		if ( valor == null || valor.compareTo( BigDecimal.ZERO ) <= 0 ) {
			throw new IllegalArgumentException( "El importe debe ser superior a 0" );
		}
	}

}
