package com.proyecto.utec_roomie.Anfitrion.domain;

import com.proyecto.utec_roomie.Anfitrion.dto.AnfitrionDto;
import com.proyecto.utec_roomie.Anfitrion.infrastructure.AnfitrionRepository;
import com.proyecto.utec_roomie.Departamento.domain.Departamento;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnfitrionService {

    @Autowired
    private AnfitrionRepository anfitrionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AnfitrionDto getAnfitrion(Long anfitrionId) {
        Anfitrion anfitrion = returnAnfitrion(anfitrionId);
        return modelMapper.map(anfitrion, AnfitrionDto.class);
    }

    public void actualizarAnfitrion(Long anfitrionId, AnfitrionDto anfitrionDto) {
        Anfitrion anfitrion = returnAnfitrion(anfitrionId);
        anfitrion.setDescripcion(anfitrionDto.getDescripcion());
        anfitrion.setTelefono(anfitrionDto.getTelefono());
        anfitrion.setDireccion(anfitrionDto.getDireccion());

        Departamento depa = anfitrion.getDepartamento();
        depa.setCosto(anfitrionDto.getDepartamentoDto().getCosto());
        depa.setHabitaciones(anfitrionDto.getDepartamentoDto().getHabitaciones());
        anfitrion.setDepartamento(depa);

        anfitrionRepository.save(anfitrion);

    }

    private Anfitrion returnAnfitrion(Long anfitrionId) {
        Optional<Anfitrion> a = anfitrionRepository.findById(anfitrionId);
        if (a.isEmpty()) {
            throw new ResourceNotFoundException("Anfitrion no encontrado");
        }
        return a.get();
    }
}
