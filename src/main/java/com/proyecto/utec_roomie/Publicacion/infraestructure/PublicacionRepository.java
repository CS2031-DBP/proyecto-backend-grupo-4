package com.proyecto.utec_roomie.Publicacion.infraestructure;

import com.proyecto.utec_roomie.Publicacion.domain.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {

}
