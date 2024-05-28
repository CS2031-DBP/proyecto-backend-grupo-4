package com.proyecto.utec_roomie.roomie.domain;

import com.proyecto.utec_roomie.auth.utils.AuthorizationUtils;
import com.proyecto.utec_roomie.exceptions.UnauthorizeOperationException;
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
        Optional<Roomie> r =  roomieRepository.findById(roomie_id);

        if(r.isEmpty()){
            throw new ResourceNotFoundException("no existe roomie");
        }

        return modelMapper.map(r.get(), RoomieResponseDto.class);
    }

    public Roomie getRoomieOwnInfo() {
        String role = authorizationUtils.getCurrentUserRole();

        if(!role.equals("ROOMIE")){
            throw new UnauthorizeOperationException("unauthorized");
        }
        String usermail = authorizationUtils.getCurrentUserEmail();

        return roomieRepository.findByEmail(usermail).get();
    }

    public void updateRoomie(RoomieResponseDto roomieResponseDto){
        String role = authorizationUtils.getCurrentUserRole();
        if(!role.equals("ROOMIE")){
            throw new UnauthorizeOperationException("unauthorized");
        }
        String usermail = authorizationUtils.getCurrentUserEmail();
        Roomie roomie = roomieRepository.findByEmail(usermail).get();
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
