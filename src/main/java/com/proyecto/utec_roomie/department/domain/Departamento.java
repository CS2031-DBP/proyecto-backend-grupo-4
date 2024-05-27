package com.proyecto.utec_roomie.department.domain;

import com.proyecto.utec_roomie.host.domain.Anfitrion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer piso;
    @Column(nullable = false)
    private Double costo;
    @Column(nullable = false)
    private Integer habitaciones;
    @Column(nullable = false)
    private Integer bano;
    @Column(nullable = false)
    private Float area;
    @Column(nullable = false)
    private String direccion;

    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "departamento")
    private Anfitrion anfitrion;
}
