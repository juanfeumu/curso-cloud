package es.um.atica.umufly.parkings.application.usecase.obtenerreservasparkingpasajeros;

import java.util.Optional;

import org.springframework.data.domain.Page;

import es.um.atica.fundewebjs.umubus.domain.cqrs.Query;
import es.um.atica.umufly.parkings.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.parkings.domain.model.ReservaParking;

public class ObtenerReservasParkingDePasajeroQuery extends Query<Optional<Page<ReservaParking>>> {

	private DocumentoIdentidad documentoIdentidad;

	private ObtenerReservasParkingDePasajeroQuery( DocumentoIdentidad documentoIdentidad ) {
		this.documentoIdentidad = documentoIdentidad;
	}

	public static ObtenerReservasParkingDePasajeroQuery of( DocumentoIdentidad documentoIdentidad ) {
		return new ObtenerReservasParkingDePasajeroQuery( documentoIdentidad );
	}

	public DocumentoIdentidad getDocumentoIdentidad() {
		return documentoIdentidad;
	}
}
