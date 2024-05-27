package com.proyecto.utec_roomie.Puntaje.domain;


import com.proyecto.utec_roomie.Puntaje.insfrastructure.Puntaje;
import com.proyecto.utec_roomie.Puntaje.insfrastructure.PuntajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuntajeService {

    @Autowired
    private PuntajeRepository puntajeRepository;

    public Puntaje crearPuntaje(Puntaje puntaje) {
        return puntajeRepository.save(puntaje);
    }

    public List<Puntaje> obtenerTodosPuntajes() {
        return puntajeRepository.findAll();
    }

    public Puntaje obtenerPuntajePorId(Long id) {
        return puntajeRepository.findById(id).orElse(null);
    }

    public void eliminarPuntaje(Long id) {
        puntajeRepository.deleteById(id);
    }
}