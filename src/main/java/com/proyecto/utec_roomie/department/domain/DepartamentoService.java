package com.proyecto.utec_roomie.department.domain;

import com.proyecto.utec_roomie.Arrendamiento.infrastructure.ArrendamientoRepository;
import com.proyecto.utec_roomie.auth.utils.AuthorizationUtils;
import com.proyecto.utec_roomie.department.dto.DepartamentoDto;
import com.proyecto.utec_roomie.department.infrastructure.DepartamentoRepository;
import com.proyecto.utec_roomie.exceptions.UnauthorizeOperationException;
import com.proyecto.utec_roomie.exceptions.UniqueResourceAlreadyExists;
import com.proyecto.utec_roomie.host.domain.Anfitrion;
import com.proyecto.utec_roomie.host.infrastructure.AnfitrionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoService {
    private final DepartamentoRepository departamentoRepository;
    private final AuthorizationUtils authorizationUtils;
    private final AnfitrionRepository anfitrionRepository;
    private final ArrendamientoRepository arrendamientoRepository;
    private final ModelMapper modelMapper;

    public DepartamentoService(DepartamentoRepository departamentoRepository, AuthorizationUtils authorizationUtils, AnfitrionRepository anfitrionRepository, ArrendamientoRepository arrendamientoRepository, ModelMapper modelMapper) {
        this.departamentoRepository = departamentoRepository;
        this.authorizationUtils = authorizationUtils;
        this.anfitrionRepository = anfitrionRepository;
        this.arrendamientoRepository = arrendamientoRepository;
        this.modelMapper = modelMapper;
    }


    public void anadirDepartamento(DepartamentoDto departamento) {
        String usermail = authorizationUtils.getCurrentUserEmail();
        Anfitrion anfitrion = anfitrionRepository.findByEmail(usermail).get();

        if(anfitrion.getDepartamento() != null){
            throw new UniqueResourceAlreadyExists("departamento ya existe");
        }
        Departamento depa = modelMapper.map(departamento,Departamento.class);
        departamentoRepository.save(depa);
        anfitrion.setDepartamento(depa);
    }

    public DepartamentoDto getDepartamento() {
        String role = authorizationUtils.getCurrentUserRole();
        String usermail = authorizationUtils.getCurrentUserEmail();
        if(role.equals("ROOMIE")){
            return new DepartamentoDto();
        }
        if(role.equals("ANFITRION")){
            Anfitrion anfitrion = anfitrionRepository.findByEmail(usermail).get();
            return modelMapper.map(anfitrion.getDepartamento(), DepartamentoDto.class);
        }
        return new DepartamentoDto();
    }
}
