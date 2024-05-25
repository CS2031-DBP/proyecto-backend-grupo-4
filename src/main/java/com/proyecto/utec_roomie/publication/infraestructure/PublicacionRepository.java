package com.proyecto.utec_roomie.publication.infraestructure;

import com.proyecto.utec_roomie.publication.domain.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {

}
