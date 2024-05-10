package com.proyecto.utec_roomie.Anfitrion.application;

import com.proyecto.utec_roomie.Anfitrion.domain.AnfitrionService;
import com.proyecto.utec_roomie.Anfitrion.dto.AnfitrionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/anfitrion")
public class AnfitrionController
{
    @Autowired
    private AnfitrionService anfitrionService;

    @GetMapping("/{anfitrion_id}")
    public ResponseEntity<AnfitrionDto> getAnfitrion(@PathVariable Long anfitrion_id){
        return ResponseEntity.ok(anfitrionService.getAnfitrion(anfitrion_id));
    }

    @PatchMapping("/{anfitrion_id}")
    public ResponseEntity<Void> actualizarAnfitrion(@PathVariable Long anfitrion_id,@RequestBody AnfitrionDto anfitrionDto){
        anfitrionService.actualizarAnfitrion(anfitrion_id,anfitrionDto);
        return ResponseEntity.ok().build();
    }

}
