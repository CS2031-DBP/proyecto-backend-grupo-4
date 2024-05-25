package com.proyecto.utec_roomie.department.application;

import com.proyecto.utec_roomie.department.domain.Departamento;
import com.proyecto.utec_roomie.department.domain.DepartamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;
    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping()
    public ResponseEntity<List<Departamento>> getDepartamentos(@RequestParam(required = false)Double latitud_cercana,
                                                              @RequestParam(required = false)Double longitud_cercana,
                                                              @RequestParam(required = false) Double costo_maximo,
                                                              @RequestParam(required = false) Float puntaje,
                                                              @RequestParam(required = false) Integer habitaciones,
                                                              @RequestParam(required = false) Float area_minima) {
        return ResponseEntity.ok(departamentoService.getDepartamentos(latitud_cercana, longitud_cercana,costo_maximo,puntaje,habitaciones,area_minima));
    }

}
