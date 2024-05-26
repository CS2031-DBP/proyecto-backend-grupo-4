package com.proyecto.utec_roomie.roomie.domain;

import com.proyecto.utec_roomie.student.domain.TipoEstudiante;
import com.proyecto.utec_roomie.roomie.dto.RoomieRequestDto;
import com.proyecto.utec_roomie.roomie.dto.RoomieResponseDto;
import com.proyecto.utec_roomie.roomie.infrastructure.RoomieRepository;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import com.proyecto.utec_roomie.exceptions.UniqueResourceAlreadyExists;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class RoomieService {

    @Autowired
    private RoomieRepository roomieRepository;
    @Autowired
    private ModelMapper modelMapper;

    public String anadirRoomie(RoomieRequestDto roomieRequestDto) {
        Roomie newRoomie = modelMapper.map(roomieRequestDto, Roomie.class);

        newRoomie.setTipoEstudiante(TipoEstudiante.ROOMIE);
        newRoomie.setFechaCreacion(Date.from(Instant.now()));

        Optional<Roomie> r = roomieRepository.findByEmail(newRoomie.getEmail());
        if(r.isPresent()){
            throw new UniqueResourceAlreadyExists("ya existe un correo con este usuario");
        }
        Roomie savedRoomie = roomieRepository.save(newRoomie);
        return "/roomie/" + savedRoomie.getId();
    }

    public RoomieRequestDto getRoomie(Long roomie_id){
        Optional<Roomie> r =  roomieRepository.findById(roomie_id);

        if(r.isEmpty()){
            throw new ResourceNotFoundException("no existe roomie");
        }

        return modelMapper.map(r.get(), RoomieRequestDto.class);
    }

    public RoomieRequestDto getRoomie(String email) {
        Optional<Roomie> r =  roomieRepository.findByEmail(email);
        if(r.isEmpty()){
            throw new ResourceNotFoundException("no existe roomie");
        }
        return modelMapper.map(r.get(), RoomieRequestDto.class);
    }

    public void updateRoomie(Long roomie_id, RoomieResponseDto roomieResponseDto){
        Roomie roomie = returnRoomie(roomie_id);

        roomie.setNombre(roomieResponseDto.getNombre());
        roomie.setApellido(roomieResponseDto.getApellido());
        roomie.setDescripcion(roomieResponseDto.getDescripcion());
        roomie.setTelefono(roomieResponseDto.getTelefono());

        roomieRepository.save(roomie);

    }

    public void deleteRoomie(Long roomie_id){
        Roomie roomie = returnRoomie(roomie_id);
        roomieRepository.delete(roomie);
    }

    private Roomie returnRoomie(Long roomie_id) {
        Optional<Roomie> r = roomieRepository.findById(roomie_id);
        if (r.isEmpty()) {
            throw new ResourceNotFoundException("roomie no existe");
        }
        return r.get();
    }


}
