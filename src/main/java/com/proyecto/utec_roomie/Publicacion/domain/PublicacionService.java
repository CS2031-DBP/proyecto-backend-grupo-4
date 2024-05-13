package com.proyecto.utec_roomie.Publicacion.domain;

import com.proyecto.utec_roomie.Publicacion.dto.PublicacionRequestDto;
import com.proyecto.utec_roomie.Publicacion.dto.PublicacionResponseDto;
import com.proyecto.utec_roomie.Publicacion.infraestructure.PublicacionRepository;
import com.proyecto.utec_roomie.Solicitud.domain.Solicitud;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.HikariCheckpointRestoreLifecycle;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HikariCheckpointRestoreLifecycle hikariCheckpointRestoreLifecycle;

//    public void anadirSolicitud(Long publicacion_id, Solicitud solicitud){
//        Optional<Publicacion> p =  publicacionRepository.findById(publicacion_id);
//        if(p.isEmpty()){
//            //error handilng
//        }
//        Publicacion publicacion = p.get();
//        List<Solicitud> lista_solicitudes = publicacion.getSolicitud();
//        lista_solicitudes.add(solicitud);
//        publicacion.setSolicitud(lista_solicitudes);
//        publicacionRepository.save(publicacion);
//
//    }

    public String crearPublicacion(PublicacionRequestDto publicacionRequestDto){

        Publicacion publicacion = modelMapper.map(publicacionRequestDto, Publicacion.class);
        //solo un anfitrion puede hacer esto
        //solo puede hacerlo una ves relacion uno a uno

        Publicacion savedPublicacion = publicacionRepository.save(publicacion);

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
        publicacion.setImagen(publicacionResponseDto.getImagen());

        publicacionRepository.save(publicacion);
    }
}
