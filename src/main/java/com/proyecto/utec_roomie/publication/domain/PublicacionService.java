package com.proyecto.utec_roomie.publication.domain;

import com.proyecto.utec_roomie.auth.utils.AuthorizationUtils;
import com.proyecto.utec_roomie.exceptions.UniqueResourceAlreadyExists;
import com.proyecto.utec_roomie.host.domain.Anfitrion;
import com.proyecto.utec_roomie.host.infrastructure.AnfitrionRepository;
import com.proyecto.utec_roomie.publication.dto.PublicacionRequestDto;
import com.proyecto.utec_roomie.publication.dto.PublicacionResponseDto;
import com.proyecto.utec_roomie.publication.infrastructure.PublicacionRepository;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<PublicacionResponseDto> getPublicaciones(){
        List<Publicacion> publicaciones = publicacionRepository.findAll();
        List<PublicacionResponseDto> publicacionesDto = new ArrayList<>();

        for (Publicacion publicacion : publicaciones) {
            PublicacionResponseDto publicacionDto = new PublicacionResponseDto();
            publicacionDto.setTitulo(publicacion.getTitulo());
            publicacionDto.setDescripcion(publicacion.getDescripcion());

            publicacionDto.setNombreAnfitrion(publicacion.getAnfitrion().getNombre());
            publicacionDto.setApellidoAnfitrion(publicacion.getAnfitrion().getApellido());

            publicacionDto.setBano(publicacion.getAnfitrion().getDepartamento().getBano());
            publicacionDto.setArea(publicacion.getAnfitrion().getDepartamento().getArea());
            publicacionDto.setPiso(publicacion.getAnfitrion().getDepartamento().getPiso());
            publicacionDto.setCosto(publicacion.getAnfitrion().getDepartamento().getCosto());
            publicacionDto.setHabitaciones(publicacion.getAnfitrion().getDepartamento().getHabitaciones());
            publicacionesDto.add(publicacionDto);
        }
        return publicacionesDto;
    }

    public String crearPublicacion(PublicacionRequestDto publicacionRequestDto){
        String usermail = authorizationUtils.getCurrentUserEmail();
        Anfitrion anfitrion = anfitrionRepository.findByEmail(usermail).get();

        if(publicacionRepository.findByAnfitrionEmail(usermail).isPresent()) {
            throw new UniqueResourceAlreadyExists("Ya tiene una publicacion");
        }

        Publicacion publicacion = modelMapper.map(publicacionRequestDto, Publicacion.class);
        publicacion.setAnfitrion(anfitrion);

        Publicacion savedPublicacion = publicacionRepository.save(publicacion);
        return "/publicacion/" + savedPublicacion.getId();
        }


    public void eliminarPublicacion() {
        String usermail = authorizationUtils.getCurrentUserEmail();
        Optional<Publicacion> publicacion = publicacionRepository.findByAnfitrionEmail(usermail);
        if (publicacion.isEmpty()) {
            throw new ResourceNotFoundException("No tiene una publicacion");
        }
        publicacionRepository.delete(publicacion.get());
    }

    public void updatePublicacion(PublicacionRequestDto publicacionRequestDto) {
        String usermail = authorizationUtils.getCurrentUserEmail();

        Anfitrion anfitrion = anfitrionRepository.findByEmail(usermail).get();

        Optional<Publicacion> p = publicacionRepository.findByAnfitrionEmail(usermail);

        if (p.isEmpty()) {
            throw new ResourceNotFoundException("publicacion no existe");
        }
        Publicacion publicacion = p.get();
        publicacion.setTitulo(publicacionRequestDto.getTitulo());
        publicacion.setDescripcion(publicacionRequestDto.getDescripcion());
        publicacionRepository.save(publicacion);
    }

    public List<PublicacionResponseDto> getPublicacionesByQuery(Integer bano, Integer habitacion, Double maxCosto) {

        List<Publicacion> publicacionList = publicacionRepository.findAllByBanoAndHabitacionAndMaxCosto(bano,habitacion,maxCosto);
        List<PublicacionResponseDto> publicacionesDto = new ArrayList<>();
        for (Publicacion publicacion : publicacionList) {
            PublicacionResponseDto publicacionDto = new PublicacionResponseDto();
            publicacionDto.setTitulo(publicacion.getTitulo());
            publicacionDto.setDescripcion(publicacion.getDescripcion());
            publicacionDto.setNombreAnfitrion(publicacion.getAnfitrion().getNombre());
            publicacionDto.setApellidoAnfitrion(publicacion.getAnfitrion().getApellido());
            publicacionDto.setPiso(publicacion.getAnfitrion().getDepartamento().getPiso());
            publicacionDto.setBano(publicacion.getAnfitrion().getDepartamento().getBano());
            publicacionDto.setArea(publicacion.getAnfitrion().getDepartamento().getArea());
            publicacionDto.setCosto(publicacion.getAnfitrion().getDepartamento().getCosto());
            publicacionDto.setHabitaciones(publicacion.getAnfitrion().getDepartamento().getHabitaciones());
            publicacionesDto.add(publicacionDto);
        }
        return publicacionesDto;
    }
}
