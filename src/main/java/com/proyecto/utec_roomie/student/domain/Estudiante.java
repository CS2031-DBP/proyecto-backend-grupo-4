package com.proyecto.utec_roomie.student.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Estudiante implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;

    private String descripcion;

    @Column(nullable = false)
    private String carrera;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Date fechaNacimiento;

    @Column(nullable = false)
    private String password;

    @Size(min = 9,max = 9)
    private String telefono;

    @Column(nullable = false)
    private TipoEstudiante tipoEstudiante;

    @DecimalMax("5.0")
    @DecimalMin("0.0")
    private Double rating;

    @Column(nullable = false)
    private Date fechaCreacion;

    private Date fechaActualizacion;

   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(tipoEstudiante.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}