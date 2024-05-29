package com.proyecto.utec_roomie.Resena.domain;

import com.proyecto.utec_roomie.Resena.dto.ResenaRequestDto;
import com.proyecto.utec_roomie.Resena.dto.ResenaResponseDto;
import com.proyecto.utec_roomie.Resena.infrastructure.ResenaRepository;
import com.proyecto.utec_roomie.auth.utils.AuthorizationUtils;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import com.proyecto.utec_roomie.exceptions.UnauthorizeOperationException;
import com.proyecto.utec_roomie.host.domain.Anfitrion;
import com.proyecto.utec_roomie.host.infrastructure.AnfitrionRepository;
import com.proyecto.utec_roomie.roomie.domain.Roomie;
import com.proyecto.utec_roomie.roomie.infrastructure.RoomieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResenaService {
    private final AuthorizationUtils authorizationUtils;
    private final ResenaRepository resenaRepository;
    private final RoomieRepository roomieRepository;
    private final AnfitrionRepository anfitrionRepository;
    private final ModelMapper modelMapper;

    public ResenaService(AuthorizationUtils authorizationUtils, ResenaRepository resenaRepository, RoomieRepository roomieRepository, AnfitrionRepository anfitrionRepository, ModelMapper modelMapper) {
        this.authorizationUtils = authorizationUtils;
        this.resenaRepository = resenaRepository;
        this.roomieRepository = roomieRepository;
        this.anfitrionRepository = anfitrionRepository;
        this.modelMapper = modelMapper;
    }

    public void crearResena(ResenaRequestDto resena, Long receptorId) {
        String usermail = authorizationUtils.getCurrentUserEmail();
        String role = authorizationUtils.getCurrentUserRole();
        Resena newResena = new Resena();
        newResena.setComentario(resena.getComentario());
        newResena.setPuntuacion(resena.getPuntuacion());
        if(role.equals("ROLE_ANFITRION")){
            Anfitrion anfitrion = anfitrionRepository.findByEmail(usermail).get();
            Optional<Roomie> roomie = roomieRepository.findById(receptorId);
            if(roomie.isEmpty()){
                throw new ResourceNotFoundException("roomie no existe");
            }
            newResena.setReceptor(roomie.get());
            newResena.setAutor(anfitrion);
        }
        else if(role.equals("ROLE_ROOMIE")){
            Roomie roomie = roomieRepository.findByEmail(usermail).get();
            Optional<Anfitrion> anfitrion = anfitrionRepository.findById(receptorId);
            if (anfitrion.isEmpty()){
                throw new ResourceNotFoundException("anfitrion no existe");
            }
            newResena.setReceptor(anfitrion.get());
            newResena.setAutor(roomie);
        }
        resenaRepository.save(newResena);
    }


    public Page<ResenaResponseDto> getOwnResenas(int page, int size) {
        String usermail = authorizationUtils.getCurrentUserEmail();
        Pageable pageable = PageRequest.of(page, size);
        Page<Resena> resenaPage = resenaRepository.findByReceptorEmail(usermail, pageable);
        List<ResenaResponseDto> resenaDtos = resenaPage.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(resenaDtos, pageable, resenaPage.getTotalPages());
    }


    private ResenaResponseDto convertToDTO(Resena resena) {
        ResenaResponseDto dto = new ResenaResponseDto();
        dto.setApellidoAutor(resena.getAutor().getApellido());
        dto.setComentario(resena.getComentario());
        dto.setNombreAutor(resena.getAutor().getNombre());
        dto.setApellidoReceptor(resena.getReceptor().getApellido());
        dto.setNombreReceptor(resena.getReceptor().getNombre());
        dto.setPuntuacion(resena.getPuntuacion());
        return dto;
    }

    public void eliminarResena(Long resenaId) {
        String usermail = authorizationUtils.getCurrentUserEmail();
        Optional<Resena> resena = resenaRepository.findById(resenaId);
        if (resena.isEmpty()){
            throw new ResourceNotFoundException("resena no existe");
        }
        if(!usermail.equals(resena.get().getAutor().getEmail())){
            throw new UnauthorizeOperationException("no eres el autor");
        }
        resenaRepository.deleteById(resenaId);
    }
}

