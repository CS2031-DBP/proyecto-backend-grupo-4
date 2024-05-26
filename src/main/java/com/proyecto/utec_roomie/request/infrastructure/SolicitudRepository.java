package com.proyecto.utec_roomie.request.infrastructure;

import com.proyecto.utec_roomie.request.domain.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    @Query("SELECT s FROM Solicitud s " +
       "JOIN s.roomie r " +
       "JOIN s.publicacion p " +
       "WHERE p.id = :publicacion_id AND r.email = :roomie_email")

    Optional<Solicitud> findByPublicacionIdAndRoomieEmail(@Param("publicacion_id") Long publicacionId, @Param("roomie_email") String roomieEmail);
}
