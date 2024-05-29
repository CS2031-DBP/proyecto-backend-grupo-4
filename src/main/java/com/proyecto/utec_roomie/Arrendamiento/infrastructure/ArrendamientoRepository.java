package com.proyecto.utec_roomie.Arrendamiento.infrastructure;

import com.proyecto.utec_roomie.Arrendamiento.domain.Arrendamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArrendamientoRepository extends JpaRepository<Arrendamiento, Long> {

    @Query("select a from Arrendamiento a " +
            "where a.roomie.id =:roomieId")
    Optional<Arrendamiento> findByRoomieId(@Param("roomieId") Long roomie_id);

    @Query("select a from Arrendamiento a " +
            "where a.roomie.email = :email or a.anfitrion.email=:email")
    Optional<Arrendamiento> findByEmail(@Param("email") String email);
}
