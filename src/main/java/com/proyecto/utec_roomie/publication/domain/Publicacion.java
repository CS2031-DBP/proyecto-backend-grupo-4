package com.proyecto.utec_roomie.publication.domain;

import com.proyecto.utec_roomie.host.domain.Anfitrion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
public class Publicacion {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private PublicacionStatus publicacionStatus = PublicacionStatus.AVAILABLE;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descripcion;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "anfitrion_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Anfitrion anfitrion;

}
