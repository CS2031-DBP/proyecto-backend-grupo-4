package com.proyecto.utec_roomie.roomie.domain;

import com.proyecto.utec_roomie.auth.utils.AuthorizationUtils;
import com.proyecto.utec_roomie.exceptions.UnauthorizeOperationException;
import com.proyecto.utec_roomie.roomie.dto.RoomieRequestDto;
import com.proyecto.utec_roomie.roomie.dto.RoomieResponseDto;
import com.proyecto.utec_roomie.roomie.infrastructure.RoomieRepository;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomieService {

    private final RoomieRepository roomieRepository;
    private final ModelMapper modelMapper;
    private final AuthorizationUtils authorizationUtils;

    @Autowired
    public RoomieService(RoomieRepository roomieRepository, ModelMapper modelMapper, AuthorizationUtils authorizationUtils) {
        this.roomieRepository = roomieRepository;
        this.modelMapper = modelMapper;
        this.authorizationUtils = authorizationUtils;
    }

    public RoomieResponseDto getRoomie(Long roomie_id){
        Optional<Roomie> r = roomieRepository.findById(roomie_id);
        if(r.isEmpty()){
            throw new ResourceNotFoundException("Roomie " + roomie_id + " not found");
        }
        Roomie roomie = r.get();
        RoomieResponseDto roomieDto = new RoomieResponseDto();
        roomieDto.setApellido(roomie.getApellido());
        roomieDto.setNombre(roomie.getNombre());
        roomieDto.setDescripcion(roomie.getDescripcion());
        roomieDto.setTelefono(roomie.getTelefono());
        return roomieDto;
    }
    public RoomieResponseDto getRoomieOwnInfo() {
        String usermail = authorizationUtils.getCurrentUserEmail();
        Roomie roomie = roomieRepository.findByEmail(usermail).get();
        RoomieResponseDto roomieDto = new RoomieResponseDto();
        roomieDto.setApellido(roomie.getApellido());
        roomieDto.setNombre(roomie.getNombre());
        roomieDto.setDescripcion(roomie.getDescripcion());
        roomieDto.setTelefono(roomie.getTelefono());
        return roomieDto;
    }

    public void updateRoomie(RoomieRequestDto dto){
        String usermail = authorizationUtils.getCurrentUserEmail();
        Roomie roomie = roomieRepository.findByEmail(usermail).get();
        roomie.setDescripcion(dto.getDescripcion());
        roomie.setTelefono(dto.getTelefono());
        roomie.setCarrera(dto.getCarrera());
        roomie.setPassword(dto.getContrasena());
        roomieRepository.save(roomie);

    }

    public void deleteRoomie(){
        String usermail = authorizationUtils.getCurrentUserEmail();
        Roomie roomie = roomieRepository.findByEmail(usermail).get();
        roomieRepository.delete(roomie);
    }


}
