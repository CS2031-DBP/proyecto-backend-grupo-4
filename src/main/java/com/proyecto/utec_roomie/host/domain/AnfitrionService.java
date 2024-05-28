package com.proyecto.utec_roomie.host.domain;

import com.proyecto.utec_roomie.auth.utils.AuthorizationUtils;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import com.proyecto.utec_roomie.exceptions.UnauthorizeOperationException;
import com.proyecto.utec_roomie.host.dto.AnfitrionRequestDto;
import com.proyecto.utec_roomie.host.dto.AnfitrionResponseDto;
import com.proyecto.utec_roomie.host.infrastructure.AnfitrionRepository;
import com.proyecto.utec_roomie.department.domain.Departamento;
import org.modelmapper.ModelMapper;
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

    public AnfitrionResponseDto getAnfitrionOwnInfo() {
        String usermail = authorizationUtils.getCurrentUserEmail();
        Anfitrion anfitrion = anfitrionRepository.findByEmail(usermail).get();
        return modelMapper.map(anfitrion, AnfitrionResponseDto.class);
    }

    public void actualizarAnfitrion(AnfitrionRequestDto anfitrionUpdate) {
        String usermail = authorizationUtils.getCurrentUserEmail();
        Anfitrion anfitrion = anfitrionRepository.findByEmail(usermail).get();
        anfitrion.setDescripcion(anfitrionUpdate.getDescripcion());
        anfitrion.setTelefono(anfitrionUpdate.getTelefono());
        anfitrion.setCarrera(anfitrionUpdate.getCarrera());
        anfitrion.setPassword(anfitrionUpdate.getContrasena());
        anfitrion.setFechaActualizacion(Date.from(Instant.now()));
        anfitrionRepository.save(anfitrion);

    }

    public void eliminarAnfitrion() {
        String usermail = authorizationUtils.getCurrentUserEmail();
        anfitrionRepository.delete(anfitrionRepository.findByEmail(usermail).get());
    }

    public AnfitrionResponseDto getAnfitrion(Long anfitrionId) {
        Optional<Anfitrion> anfitrion = anfitrionRepository.findById(anfitrionId);
        if (anfitrion.isEmpty()){
            throw new ResourceNotFoundException("anfitrion no existe");
        }
        return modelMapper.map(anfitrion.get(), AnfitrionResponseDto.class);
    }
}
