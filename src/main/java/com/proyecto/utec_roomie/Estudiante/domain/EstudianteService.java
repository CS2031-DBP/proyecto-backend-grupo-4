//package com.proyecto.utec_roomie.Estudiante.domain;
//
//import com.proyecto.utec_roomie.Estudiante.infrastructure.EstudianteRepository;
//import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class EstudianteService {
//    @Autowired
//    private EstudianteRepository estudianteRepository;
//
//    public Estudiante getEstudiante(Long estudiante_id){
//        Optional<Estudiante> estudiante = estudianteRepository.findById(estudiante_id);
//        if(estudiante.isEmpty()){
//            throw new ResourceNotFoundException("Estudiante no encontrado");
//        }
//        return estudiante.get();
//    }
//
//
//}
