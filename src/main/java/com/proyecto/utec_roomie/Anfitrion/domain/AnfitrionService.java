package com.proyecto.utec_roomie.Anfitrion.domain;

import com.proyecto.utec_roomie.Anfitrion.dto.AnfitrionResponseDto;
import com.proyecto.utec_roomie.Anfitrion.dto.AnfitrionRequestDto;
import com.proyecto.utec_roomie.Anfitrion.infrastructure.AnfitrionRepository;
import com.proyecto.utec_roomie.Departamento.domain.Departamento;
import com.proyecto.utec_roomie.Departamento.infrastructure.DepartamentoRepository;
import com.proyecto.utec_roomie.Edificio.domain.Edificio;
import com.proyecto.utec_roomie.Edificio.infrastructure.EdificioRepository;
import com.proyecto.utec_roomie.Estudiante.domain.TipoEstudiante;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import com.proyecto.utec_roomie.exceptions.UniqueResourceAlreadyExists;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class AnfitrionService {

    @Autowired
    private EdificioRepository edificioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private AnfitrionRepository anfitrionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AnfitrionResponseDto getAnfitrion(Long anfitrionId) {
        Anfitrion anfitrion = returnAnfitrion(anfitrionId);
        return modelMapper.map(anfitrion, AnfitrionResponseDto.class);
    }

      public String anadirAnfitrion(AnfitrionRequestDto anfitrionRequestDto) {
        Anfitrion nuevoAnfitrion = modelMapper.map(anfitrionRequestDto, Anfitrion.class);
        nuevoAnfitrion.setFecha_de_creacion(Date.from(Instant.now()));
        nuevoAnfitrion.setTipoEstudiante(TipoEstudiante.ANFITRION);

        Optional<Anfitrion> a = anfitrionRepository.findByCorreo(anfitrionRequestDto.getCorreo());
        Optional<Edificio> e = edificioRepository.findById(anfitrionRequestDto.getDepartamento().getEdificio().getId());
        Optional<Departamento> d = departamentoRepository.findByEdificioAndNro(e.get(),anfitrionRequestDto.getDepartamento().getNro());

        if (a.isPresent()) {
            throw new UniqueResourceAlreadyExists("Usuario ya existe en el sistema");
        }

        if(d.isPresent()){
            throw new UniqueResourceAlreadyExists("Departamento ya registrado en el sistema");
        }
        Anfitrion savedAnfitrion = anfitrionRepository.save(nuevoAnfitrion);
          return "/anfitrion/" + savedAnfitrion.getId();
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

}
