package com.proyecto.utec_roomie.department.application;

import com.proyecto.utec_roomie.department.domain.DepartamentoService;
import com.proyecto.utec_roomie.department.dto.DepartamentoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

    private final DepartamentoService departamentoService;
    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @PreAuthorize("hasRole('ROLE_ANFITRION')")
    @PostMapping
    public ResponseEntity<Void> anadirDepartamento(@RequestBody DepartamentoDto departamento){
        departamentoService.anadirDepartamento(departamento);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ANFITRION') or hasRole('ROLE_ROOMIE')")
    @GetMapping
    public ResponseEntity<DepartamentoDto> getDepartamento(){
        return ResponseEntity.ok(departamentoService.getDepartamento());
    }

}
