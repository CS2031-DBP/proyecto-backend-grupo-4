package com.proyecto.utec_roomie.roomie.application;


import com.proyecto.utec_roomie.roomie.dto.RoomieRequestDto;
import com.proyecto.utec_roomie.roomie.domain.RoomieService;
import com.proyecto.utec_roomie.roomie.dto.RoomieResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/roomie")
public class RoomieController {

    private final RoomieService roomieService;

    public RoomieController(RoomieService roomieService) {
        this.roomieService = roomieService;
    }


    @PostMapping
    public ResponseEntity<Void> anadirRoomie(@RequestBody RoomieRequestDto roomieRequestDto) {
        String uri = roomieService.anadirRoomie(roomieRequestDto);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @GetMapping("/{roomie_id}")
    public ResponseEntity<RoomieRequestDto> getRoomieByID(@PathVariable Long roomie_id){
        return ResponseEntity.ok(roomieService.getRoomie(roomie_id));
    }

    @GetMapping("/{correo}")
    public ResponseEntity<RoomieRequestDto> getRoomieByCorreo(@PathVariable String correo){
        return ResponseEntity.ok(roomieService.getRoomie(correo));
    }

    @PatchMapping("/{roomie_id}")
    public ResponseEntity<Void> updateRoomie(@PathVariable Long roomie_id,@RequestBody RoomieResponseDto roomieResponseDto){
        roomieService.updateRoomie(roomie_id, roomieResponseDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{roomie_id}")
    public ResponseEntity<Void> deleteRoomie(@PathVariable Long roomie_id){
        roomieService.deleteRoomie(roomie_id);
        return ResponseEntity.ok().build();
    }




}
