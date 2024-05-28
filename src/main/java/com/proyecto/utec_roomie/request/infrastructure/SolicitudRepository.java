package com.proyecto.utec_roomie.request.infrastructure;

import com.proyecto.utec_roomie.request.domain.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    @Query("select s from Solicitud s " +
            "WHERE s.roomie.email = :roomie_email and s.publicacion.id = :publicacion_id" )
    Optional<Solicitud> findByPublicacionIdAndRoomieEmail(@Param("publicacion_id") Long publicacionId, @Param("roomie_email") String roomieEmail);

    @Query("SELECT s FROM Solicitud s " +
       "WHERE s.publicacion.id = :publicacion_id")
    List<Solicitud> findAllByPublicacionId(@Param("publicacion_id") Long publicacionId);

    @Query("SELECT s FROM Solicitud s " +
       "WHERE s.roomie.email = :roomie_email")
    List<Solicitud> findAllByRoomieId(@Param("roomie_email") String roomieEmail);

    @Modifying
    @Query("Update Solicitud s set s.solicitudStatus = com.proyecto.utec_roomie.request.domain.SolicitudStatus.CANCELLED " +
            "where s.publicacion.id =:publicacion_id ")
    void setAllSolicitudCancelledByPublicacionId(@Param("publicacion_id")Long publicacionId);


}
