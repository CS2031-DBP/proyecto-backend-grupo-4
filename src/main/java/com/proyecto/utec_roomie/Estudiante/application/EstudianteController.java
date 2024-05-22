//package com.proyecto.utec_roomie.Estudiante.application;
//
//
//import com.proyecto.utec_roomie.Estudiante.domain.Estudiante;
//import com.proyecto.utec_roomie.Estudiante.domain.EstudianteService;
//import com.proyecto.utec_roomie.Estudiante.dto.EstudianteActualizacionDto;
//import com.proyecto.utec_roomie.Estudiante.infrastructure.EstudianteRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/estudiante")
//public class EstudianteController {
//
//    @Autowired
//    private EstudianteService estudianteService;
//
//
//    @PatchMapping("/{estudiante_id}")
//    public ResponseEntity<Void> actualizarDatos(@PathVariable Long estudiante_id ,
//                                                @RequestBody EstudianteActualizacionDto estudiante) {
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/{estudiante_id}")
//    public ResponseEntity<Estudiante> getEstudiante(@PathVariable Long estudiante_id) {
//        return ResponseEntity.ok(estudianteService.getEstudiante(estudiante_id));
//    }
//}
