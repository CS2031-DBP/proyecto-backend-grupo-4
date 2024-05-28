package com.proyecto.utec_roomie.publication.infraestructure;

import com.proyecto.utec_roomie.host.domain.Anfitrion;
import com.proyecto.utec_roomie.publication.domain.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {


        @Query("SELECT p FROM Publicacion p " +
       "WHERE p.anfitrion.email = :anfitrion_email")
    Optional<Publicacion> findByAnfitrionEmail(@Param("anfitrion_email")String anfitrion_email);
}
