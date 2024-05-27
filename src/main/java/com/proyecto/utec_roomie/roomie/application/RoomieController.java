package com.proyecto.utec_roomie.roomie.application;


import com.proyecto.utec_roomie.roomie.domain.Roomie;
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

    @GetMapping("/{roomie_id}")
    public ResponseEntity<RoomieResponseDto> getRoomieByID(@PathVariable Long roomie_id){
        return ResponseEntity.ok(roomieService.getRoomie(roomie_id));
    }

    @GetMapping("/")
    public ResponseEntity<Roomie> getOwnRoomieInfo(){
        return ResponseEntity.ok(roomieService.getRoomieOwnInfo());
    }

    @PatchMapping("/")
    public ResponseEntity<Void> updateRoomie(@RequestBody RoomieResponseDto roomieResponseDto){
        roomieService.updateRoomie(roomieResponseDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{roomie_id}")
    public ResponseEntity<Void> deleteRoomie(@PathVariable Long roomie_id){
        roomieService.deleteRoomie(roomie_id);
        return ResponseEntity.ok().build();
    }




}
