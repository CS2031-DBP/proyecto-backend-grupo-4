package com.proyecto.utec_roomie.department.application;

import com.proyecto.utec_roomie.department.domain.Departamento;
import com.proyecto.utec_roomie.department.domain.DepartamentoService;
import com.proyecto.utec_roomie.department.dto.DepartamentoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

    private final DepartamentoService departamentoService;
    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @PatchMapping
    public ResponseEntity<Void> anadirDepartamento(@RequestBody Departamento departamento){
        departamentoService.anadirDepartamento(departamento);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<DepartamentoDto> getDepartamento(){
        return ResponseEntity.ok(departamentoService.getDepartamento());
    }

}
