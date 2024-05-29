package com.proyecto.utec_roomie.roomie.application;


import com.proyecto.utec_roomie.roomie.domain.Roomie;
import com.proyecto.utec_roomie.roomie.dto.RoomieRequestDto;
import com.proyecto.utec_roomie.roomie.domain.RoomieService;
import com.proyecto.utec_roomie.roomie.dto.RoomieResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/roomie")
public class RoomieController {

    private final RoomieService roomieService;

    public RoomieController(RoomieService roomieService) {
        this.roomieService = roomieService;
    }

    @PreAuthorize("hasRole('ROLE_ANFITRION')")
    @GetMapping("/{roomie_id}")
    public ResponseEntity<RoomieResponseDto> getRoomieByID(@PathVariable Long roomie_id){
        return ResponseEntity.ok(roomieService.getRoomie(roomie_id));
    }
    @PreAuthorize("hasRole('ROLE_ROOMIE')")
    @GetMapping()
    public ResponseEntity<RoomieResponseDto> getOwnRoomieInfo(){
        return ResponseEntity.ok(roomieService.getRoomieOwnInfo());
    }

    @PreAuthorize("hasRole('ROLE_ROOMIE')")
    @PatchMapping("/")
    public ResponseEntity<Void> updateRoomie(@RequestBody RoomieRequestDto roomieDatos){
        roomieService.updateRoomie(roomieDatos);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ROOMIE')")
    @DeleteMapping()
    public ResponseEntity<Void> deleteRoomie(){
        roomieService.deleteRoomie();
        return ResponseEntity.ok().build();
    }




}
