package com.proyecto.utec_roomie.host.domain;

import com.proyecto.utec_roomie.auth.utils.AuthorizationUtils;
import com.proyecto.utec_roomie.exceptions.UnauthorizeOperationException;
import com.proyecto.utec_roomie.host.dto.AnfitrionResponseDto;
import com.proyecto.utec_roomie.host.infrastructure.AnfitrionRepository;
import com.proyecto.utec_roomie.department.domain.Departamento;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class AnfitrionService {

    private final AnfitrionRepository anfitrionRepository;
    private final ModelMapper modelMapper;
    private final AuthorizationUtils authorizationUtils;

    @Autowired
    public AnfitrionService(AnfitrionRepository anfitrionRepository, ModelMapper modelMapper, AuthorizationUtils authorizationUtils){
        this.anfitrionRepository = anfitrionRepository;
        this.modelMapper = modelMapper;
        this.authorizationUtils = authorizationUtils;
    }

    public AnfitrionResponseDto getAnfitrion(Long anfitrionId) {
        Anfitrion anfitrion = returnAnfitrion(anfitrionId);
        return modelMapper.map(anfitrion, AnfitrionResponseDto.class);
    }

    public void actualizarAnfitrion(Long anfitrionId, AnfitrionResponseDto anfitrionResponseDto) {
        Anfitrion anfitrion = returnAnfitrion(anfitrionId);
        anfitrion.setDescripcion(anfitrionResponseDto.getDescripcion());
        anfitrion.setTelefono(anfitrionResponseDto.getTelefono());

        Departamento depa = anfitrion.getDepartamento();
        depa.setCosto(anfitrionResponseDto.getDepartamentoDto().getCosto());
        depa.setHabitaciones(anfitrionResponseDto.getDepartamentoDto().getHabitaciones());
        anfitrion.setDepartamento(depa);

        anfitrion.setFechaActualizacion(Date.from(Instant.now()));

        anfitrionRepository.save(anfitrion);

    }

    public void eliminarAnfitrion(Long anfitrionId) {
        Anfitrion anfitrion = returnAnfitrion(anfitrionId);
        anfitrionRepository.delete(anfitrion);
    }

    private Anfitrion returnAnfitrion(Long anfitrionId) {
        Optional<Anfitrion> a = anfitrionRepository.findById(anfitrionId);
        if (a.isEmpty()) {
            throw new ResourceNotFoundException("Anfitrion no encontrado");
        }
        return a.get();
    }

    public void anadirDepartamento(Departamento departamento) {
        String role = authorizationUtils.getCurrentUserRole();
        if(!role.equals("ANFITRION")){
            throw new UnauthorizeOperationException("no eres anfitrion");
        }
        String usermail = authorizationUtils.getCurrentUserEmail();
        Anfitrion anfitrion = anfitrionRepository.findByEmail(usermail).get();
        anfitrion.setDepartamento(departamento);
        anfitrionRepository.save(anfitrion);
    }
}
