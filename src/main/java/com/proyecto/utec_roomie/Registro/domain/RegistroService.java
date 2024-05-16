package com.proyecto.utec_roomie.Registro.domain;

import com.proyecto.utec_roomie.Estudiante.domain.Estudiante;
import com.proyecto.utec_roomie.Estudiante.infrastructure.EstudianteRepository;
import com.proyecto.utec_roomie.Registro.infrastructure.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class RegistroService {


    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    public void registrarUsuario(Estudiante estudiante) {

//        Boolean a = registroRepository.


//        estudianteRepository.save(estudiante); deberiamos hacer estudiante una entidad?
//        o de frnete con registor?
        Registro registro = new Registro();
        registro.setUsuario(estudiante);
        registro.setFecha_de_creacion(Date.from(Instant.now()));
        registroRepository.save(registro);
    }

}
