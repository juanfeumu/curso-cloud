package es.um.atica.umufly.parking.application.port;

import java.util.UUID;

import org.springframework.data.domain.Page;

import es.um.atica.umufly.parking.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.parking.domain.model.ReservaParking;

public interface ReservasParkingReadRepository {

	ReservaParking findReservaById( DocumentoIdentidad documentoIdentidad, UUID idReserva );

	Page<ReservaParking> findReservas( DocumentoIdentidad documentoIdentidad, int pagina, int tamanioPagina );

}
