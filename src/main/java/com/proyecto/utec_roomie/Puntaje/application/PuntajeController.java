package com.proyecto.utec_roomie.Puntaje.application;

import com.proyecto.utec_roomie.Puntaje.domain.PuntajeService;

import com.proyecto.utec_roomie.Puntaje.domain.Resena;
import com.proyecto.utec_roomie.Puntaje.insfrastructure.Puntaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puntajes")
public class PuntajeController {

    @Autowired
    private PuntajeService puntajeService;

    @PostMapping
    public ResponseEntity<Resena> crearPuntaje(@RequestBody Puntaje puntaje) {
        Puntaje nuevoPuntaje = puntajeService.crearPuntaje(puntaje);
        return ResponseEntity.ok(nuevoPuntaje);
    }

    @GetMapping
    public ResponseEntity<List<Resena>> obtenerTodosPuntajes() {
        List<Puntaje> puntajes = puntajeService.obtenerTodosPuntajes();
        return ResponseEntity.ok(puntajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Puntaje> obtenerPuntajePorId(@PathVariable Long id) {
        Puntaje puntaje = puntajeService.obtenerPuntajePorId(id);
        return puntaje != null ? ResponseEntity.ok(puntaje) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPuntaje(@PathVariable Long id) {
        puntajeService.eliminarPuntaje(id);
        return ResponseEntity.noContent().build();
    }
}