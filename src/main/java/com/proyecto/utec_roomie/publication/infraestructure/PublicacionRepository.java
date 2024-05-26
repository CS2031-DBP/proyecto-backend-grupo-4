package com.proyecto.utec_roomie.publication.infraestructure;

import com.proyecto.utec_roomie.host.domain.Anfitrion;
import com.proyecto.utec_roomie.publication.domain.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {

//    @Query("Select p from Publicacion p, Anfitrion A where p.anfitrion.email = :ownerEmail")
//    Optional<Publicacion> findByAnfitrionEmail(@Param("ownerEmail") String anfitrionEmail);

//    Optional<Publicacion> findByAnfitrion(Anfitrion anfitrion);
    Optional<Publicacion> findByAnfitrion(Anfitrion anfitrion);
}
