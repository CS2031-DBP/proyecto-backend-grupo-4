package com.proyecto.utec_roomie.Solicitud.domain;

import com.proyecto.utec_roomie.Anfitrion.domain.Anfitrion;
import com.proyecto.utec_roomie.Publicacion.domain.Publicacion;
import com.proyecto.utec_roomie.Roomie.domain.Roomie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Solicitud {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "publicacion_id", nullable = false)
    Publicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "roomie_id", nullable = false)
    Roomie roomie;
}
