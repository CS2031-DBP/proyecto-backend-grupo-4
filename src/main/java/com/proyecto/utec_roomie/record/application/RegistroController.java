package com.proyecto.utec_roomie.record.application;

import com.proyecto.utec_roomie.student.domain.Estudiante;
import com.proyecto.utec_roomie.record.domain.RegistroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private RegistroService registroService;


    @GetMapping
    public ResponseEntity<Void> registrarUsuario(@RequestBody @Valid Estudiante estudiante){
        registroService.registrarUsuario(estudiante);
        return ResponseEntity.created(null).build();
    }
}
