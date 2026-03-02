package es.um.atica.umufly.parkings.domain.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record Periodo(LocalDateTime inicio, LocalDateTime fin) {

	public Periodo {
		if ( inicio == null ) {
			throw new IllegalArgumentException( "La fecha de inicio no puede ser nula" );
		}
		if ( fin == null ) {
			throw new IllegalArgumentException( "La fecha de fin no puede ser nula" );
		}
		if ( fin.isBefore( inicio ) ) {
			throw new IllegalArgumentException( "La fecha de fin no puede ser posterior a la de inicio" );
		}
	}

	public long getDiasPeriodo() {
		return ChronoUnit.DAYS.between( inicio, fin );
	}

	public long minutosRestantes() {
		long dias = ChronoUnit.DAYS.between( inicio, fin );
		return ChronoUnit.MINUTES.between( inicio.plusDays( dias ), fin );
	}
}
