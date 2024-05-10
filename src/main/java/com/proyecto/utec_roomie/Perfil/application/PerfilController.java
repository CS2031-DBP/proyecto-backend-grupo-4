package com.proyecto.utec_roomie.Perfil.application;


import com.proyecto.utec_roomie.Estudiante.domain.Estudiante;
import com.proyecto.utec_roomie.Perfil.domain.PerfilService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping("/{estudiante_id}")
    public ResponseEntity<Estudiante> getPerfil(@PathVariable Long estudiante_id) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{estudiante_id}")
    public ResponseEntity<Void> actualizarPerfil(@PathVariable Long estudiante_id) {
        return ResponseEntity.ok().build();
    }
}
