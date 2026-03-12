package es.um.atica;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import es.um.atica.umufly.vuelos.domain.model.Avion;
import es.um.atica.umufly.vuelos.domain.model.ClaseAsientoReserva;
import es.um.atica.umufly.vuelos.domain.model.CorreoElectronico;
import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.vuelos.domain.model.EstadoReserva;
import es.um.atica.umufly.vuelos.domain.model.EstadoVuelo;
import es.um.atica.umufly.vuelos.domain.model.Itinerario;
import es.um.atica.umufly.vuelos.domain.model.Nacionalidad;
import es.um.atica.umufly.vuelos.domain.model.NombreCompleto;
import es.um.atica.umufly.vuelos.domain.model.Pasajero;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;
import es.um.atica.umufly.vuelos.domain.model.TipoDocumento;
import es.um.atica.umufly.vuelos.domain.model.TipoVuelo;
import es.um.atica.umufly.vuelos.domain.model.Vuelo;

@TestClassOrder( ClassOrderer.OrderAnnotation.class )
public class UmuFlyUnitariosTest {

	private DocumentoIdentidad titular;
	private Pasajero pasajero;
	private Vuelo vueloPendiente;
	private Vuelo vueloCancelado;
	private Vuelo vueloCompletado;
	private LocalDateTime SALIDA = LocalDateTime.of( 2026, 3, 1, 10, 0 );
	private LocalDateTime LLEGADA = LocalDateTime.of( 2026, 3, 1, 15, 0 );
	ReservaVuelo reservaVuelo;

	@BeforeEach
	void setUp() {
		titular = new DocumentoIdentidad( TipoDocumento.NIF, "12345678Z" );
		pasajero = Pasajero.of( titular, new NombreCompleto( "Juan", "García", "López" ), new CorreoElectronico( "juan@ejemplo.com" ), new Nacionalidad( "Española" ) );
		Itinerario itinerario = new Itinerario( SALIDA, LLEGADA, "MAD", "BCN" );
		Avion avion = new Avion( 180 );
		vueloPendiente = Vuelo.of( UUID.randomUUID(), itinerario, TipoVuelo.NACIONAL, EstadoVuelo.PENDIENTE, avion );
		vueloCancelado = Vuelo.of( UUID.randomUUID(), itinerario, TipoVuelo.NACIONAL, EstadoVuelo.CANCELADO, avion );
		vueloCompletado = Vuelo.of( UUID.randomUUID(), itinerario, TipoVuelo.NACIONAL, EstadoVuelo.COMPLETADO, avion );
	}

	@Nested
	@DisplayName( "cancelarReserva_correcta" )
	@Order( 1 )
	class TestSolicitarReserva {

		@Test
		void crear_reserva_debe_iniciarla_pendiente() {
			ReservaVuelo reservaVuelo = ReservaVuelo.solicitarReserva( titular, pasajero, vueloPendiente, ClaseAsientoReserva.BUSINESS, LLEGADA, 0, 100 );
			assertEquals( reservaVuelo.getEstado(), EstadoReserva.PENDIENTE );
		}

		@Test
		void verificar_formalizar_reserva_pasar_activa() {
			ReservaVuelo reservaVuelo = ReservaVuelo.solicitarReserva( titular, pasajero, vueloPendiente, ClaseAsientoReserva.BUSINESS, LLEGADA, 0, 100 );
			reservaVuelo.formalizarReserva();
			assertEquals( reservaVuelo.getEstado(), EstadoReserva.ACTIVA );
		}

	}

}
