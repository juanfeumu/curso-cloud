package es.um.atica.umufly.parking.adaptors.persistence.jpa;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import es.um.atica.umufly.parking.adaptors.persistence.jpa.entity.EstadoReservaParkingEnum;
import es.um.atica.umufly.parking.adaptors.persistence.jpa.entity.ReservaParkingEntity;
import es.um.atica.umufly.parking.adaptors.persistence.jpa.mapper.JpaPersistenceMapper;
import es.um.atica.umufly.parking.adaptors.persistence.jpa.repository.JpaReservaParkingRepository;
import es.um.atica.umufly.parking.adaptors.persistence.jpa.repository.JpaReservaParkingViewRepository;
import es.um.atica.umufly.parking.adaptors.persistence.jpa.repository.JpaTipoEstacionamientoRepository;
import es.um.atica.umufly.parking.application.port.ReservasParkingReadRepository;
import es.um.atica.umufly.parking.application.port.ReservasParkingWriteRepository;
import es.um.atica.umufly.parking.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.parking.domain.model.ReservaParking;

@Component
public class ReservasParkingPersistenceAdapter implements ReservasParkingReadRepository, ReservasParkingWriteRepository {

	private final JpaReservaParkingRepository jpaReservaParkingRepository;
	private final JpaReservaParkingViewRepository jpaReservaParkingViewRepository;
	private final JpaTipoEstacionamientoRepository jpaTipoEstacionamientoRepository;
	private final Clock clock;

	public ReservasParkingPersistenceAdapter( JpaReservaParkingRepository jpaReservaParkingRepository, JpaReservaParkingViewRepository jpaReservaParkingViewRepository, JpaTipoEstacionamientoRepository jpaTipoEstacionamientoRepository, Clock clock ) {
		this.jpaReservaParkingRepository = jpaReservaParkingRepository;
		this.jpaReservaParkingViewRepository = jpaReservaParkingViewRepository;
		this.jpaTipoEstacionamientoRepository = jpaTipoEstacionamientoRepository;
		this.clock = clock;
	}

	@Override
	public void persistirFormalizacionReserva( UUID idReserva, UUID idReservaFormalizada ) {
		LocalDateTime fechaActual = LocalDateTime.now( clock );
		ReservaParkingEntity entidad = jpaReservaParkingRepository.findById( idReserva.toString() ).orElseThrow( () -> new IllegalStateException( "Reserva de parking no encontrada" ) );
		entidad.setEstadoReserva( EstadoReservaParkingEnum.A );
		entidad.setFechaModificacion( fechaActual );
		entidad.setIdReservaFormalizada( idReservaFormalizada.toString() );
		jpaReservaParkingRepository.save( entidad );
	}

	@Override
	public ReservaParking findReservaById( DocumentoIdentidad documentoIdentidad, UUID idReserva ) {
		return jpaReservaParkingViewRepository.findByIdAndTipoDocumentoClienteAndNumeroDocumentoCliente( idReserva.toString(), JpaPersistenceMapper.tipoDocumentoToEntity( documentoIdentidad.tipo() ), documentoIdentidad.identificador() )
				.map( r -> JpaPersistenceMapper.reservaParkingToModel( r, jpaTipoEstacionamientoRepository.findById( r.getTipo() ).orElseGet( null ) ) ).orElseThrow( () -> new IllegalStateException( "Reserva no encontrado" ) );
	}

	@Override
	public Page<ReservaParking> findReservas( DocumentoIdentidad documentoIdentidad, int pagina, int tamanioPagina ) {
		return jpaReservaParkingViewRepository.findByTipoDocumentoClienteAndNumeroDocumentoCliente( JpaPersistenceMapper.tipoDocumentoToEntity( documentoIdentidad.tipo() ), documentoIdentidad.identificador(),
				PageRequest.of( pagina, tamanioPagina ) ).map( r -> JpaPersistenceMapper.reservaParkingToModel(r, jpaTipoEstacionamientoRepository.findById( r.getTipo()).orElseGet( null )));
	}

	@Override
	public void cancelReserva( UUID idReserva ) {
		LocalDateTime fechaActual = LocalDateTime.now( clock );
		ReservaParkingEntity entidad = jpaReservaParkingRepository.findById( idReserva.toString() ).orElseThrow( () -> new IllegalStateException( "Reserva de parking no encontrada" ) );
		entidad.setEstadoReserva( EstadoReservaParkingEnum.X );
		entidad.setFechaModificacion( fechaActual );
		jpaReservaParkingRepository.save( entidad );
	}

	@Override
	public void persistirReserva( ReservaParking reservaParking ) {
		LocalDateTime fechaActual = LocalDateTime.now( clock );
		jpaReservaParkingRepository.save( JpaPersistenceMapper.reservaParkingToEntity( reservaParking, fechaActual, fechaActual ) );

	}

}
