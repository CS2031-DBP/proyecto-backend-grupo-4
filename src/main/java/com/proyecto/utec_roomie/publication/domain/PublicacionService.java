package com.proyecto.utec_roomie.publication.domain;

import com.proyecto.utec_roomie.auth.utils.AuthorizationUtils;
import com.proyecto.utec_roomie.exceptions.UnauthorizeOperationException;
import com.proyecto.utec_roomie.exceptions.UniqueResourceAlreadyExists;
import com.proyecto.utec_roomie.host.domain.Anfitrion;
import com.proyecto.utec_roomie.host.infrastructure.AnfitrionRepository;
import com.proyecto.utec_roomie.publication.dto.PublicacionRequestDto;
import com.proyecto.utec_roomie.publication.dto.PublicacionResponseDto;
import com.proyecto.utec_roomie.publication.infraestructure.PublicacionRepository;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final ModelMapper modelMapper;
    private final AuthorizationUtils authorizationUtils;
    private final AnfitrionRepository anfitrionRepository;

    @Autowired
    public PublicacionService(PublicacionRepository publicacionRepository, ModelMapper modelMapper,
                              AuthorizationUtils authorizationUtils, AnfitrionRepository anfitrionRepository) {
        this.publicacionRepository = publicacionRepository;
        this.modelMapper = modelMapper;
        this.authorizationUtils = authorizationUtils;
        this.anfitrionRepository = anfitrionRepository;
    }


    public String crearPublicacion(PublicacionRequestDto publicacionRequestDto){
        String role = authorizationUtils.getCurrentUserRole();
        String usermail = authorizationUtils.getCurrentUserEmail();
        if(!role.equals("ANFITRION")) {
            throw new UnauthorizeOperationException("no eres anfitrion");
        }
        Anfitrion anfitrion = anfitrionRepository.findByEmail(usermail).get();
        if(anfitrion.getPublicacion() != null) {
            throw new UniqueResourceAlreadyExists("Ya tiene una publicacion");
        }
            Publicacion publicacion = modelMapper.map(publicacionRequestDto, Publicacion.class);
            Publicacion savedPublicacion = publicacionRepository.save(publicacion);
            anfitrion.setPublicacion(savedPublicacion);
            return "/publicacion/" + savedPublicacion.getId();
        }


    public void eliminarPublicacion(Long publicacionId) {
        Optional<Publicacion> publicacion = publicacionRepository.findById(publicacionId);
        if (publicacion.isEmpty()) {
            throw new ResourceNotFoundException("publicacion no existe p causa gaaaaaaa");
        }
        publicacionRepository.delete(publicacion.get());
    }

    public void updatePublicacion(Long publicacionId, PublicacionResponseDto publicacionResponseDto) {
        Optional<Publicacion> p = publicacionRepository.findById(publicacionId);
        if (p.isEmpty()) {
            throw new ResourceNotFoundException("publicacion no existe p causa gaaaaaaa");
        }

        Publicacion publicacion = p.get();
        publicacion.setTitulo(publicacionResponseDto.getTitulo());
        publicacion.setDescripcion(publicacionResponseDto.getDescripcion());

        publicacionRepository.save(publicacion);
    }
}
