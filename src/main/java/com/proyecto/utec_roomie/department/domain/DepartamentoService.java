package com.proyecto.utec_roomie.department.domain;

import com.proyecto.utec_roomie.Arrendamiento.domain.Arrendamiento;
import com.proyecto.utec_roomie.Arrendamiento.infrastructure.ArrendamientoRepository;
import com.proyecto.utec_roomie.auth.utils.AuthorizationUtils;
import com.proyecto.utec_roomie.department.dto.DepartamentoDto;
import com.proyecto.utec_roomie.department.infrastructure.DepartamentoRepository;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import com.proyecto.utec_roomie.exceptions.UniqueResourceAlreadyExists;
import com.proyecto.utec_roomie.host.domain.Anfitrion;
import com.proyecto.utec_roomie.host.infrastructure.AnfitrionRepository;
import com.proyecto.utec_roomie.roomie.domain.Roomie;
import com.proyecto.utec_roomie.roomie.infrastructure.RoomieRepository;
import com.proyecto.utec_roomie.user.infrastructure.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartamentoService {
    private final DepartamentoRepository departamentoRepository;
    private final AuthorizationUtils authorizationUtils;
    private final AnfitrionRepository anfitrionRepository;
    private final ArrendamientoRepository arrendamientoRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoomieRepository roomieRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository, AuthorizationUtils authorizationUtils, AnfitrionRepository anfitrionRepository, ArrendamientoRepository arrendamientoRepository, ModelMapper modelMapper, RoomieRepository roomieRepository, UserRepository userRepository) {
        this.departamentoRepository = departamentoRepository;
        this.authorizationUtils = authorizationUtils;
        this.anfitrionRepository = anfitrionRepository;
        this.arrendamientoRepository = arrendamientoRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roomieRepository = roomieRepository;
    }


    public void anadirDepartamento(DepartamentoDto departamento) {
        String usermail = authorizationUtils.getCurrentUserEmail();
        Anfitrion anfitrion = anfitrionRepository.findByEmail(usermail).get();

        if(anfitrion.getDepartamento() != null){
            throw new UniqueResourceAlreadyExists("departamento ya existe");
        }
        Departamento depa = modelMapper.map(departamento,Departamento.class);
        Departamento savedDepartamento = departamentoRepository.save(depa);
        anfitrion.setDepartamento(savedDepartamento);
        anfitrionRepository.save(anfitrion);
    }

    public DepartamentoDto getDepartamento() {
        String usermail = authorizationUtils.getCurrentUserEmail();
        String role = authorizationUtils.getCurrentUserRole();
        if(role.equals("ROLE_ROOMIE")) {
            Roomie roomie = roomieRepository.findByEmail(usermail).get();
            Optional<Arrendamiento> arrendamiento = arrendamientoRepository.findByRoomieId(roomie.getId());
            if (arrendamiento.isEmpty()) {
                throw new ResourceNotFoundException("no tienes un departamento rentado actualmetne");
            }
            return modelMapper.map(arrendamiento.get().getAnfitrion().getDepartamento(), DepartamentoDto.class);

        }
        else{
            Anfitrion anfitrion = anfitrionRepository.findByEmail(usermail).get();
            if (anfitrion.getDepartamento() == null) {
                throw new ResourceNotFoundException("no tienes un departamento actualmente");
            }
                return modelMapper.map(anfitrion.getDepartamento(), DepartamentoDto.class);
            }
    }
}
