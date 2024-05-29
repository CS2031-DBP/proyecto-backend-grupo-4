package com.proyecto.utec_roomie.Resena.infrastructure;

import com.proyecto.utec_roomie.Resena.domain.Resena;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResenaRepository extends JpaRepository<Resena, Long> {
    @Query("select r from Resena r " +
            "where r.receptor.email = :email")
    Page<Resena> findByReceptorEmail(@Param("email") String email, Pageable pageable);
}
