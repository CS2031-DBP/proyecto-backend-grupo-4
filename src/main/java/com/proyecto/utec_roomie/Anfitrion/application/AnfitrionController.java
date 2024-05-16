package com.proyecto.utec_roomie.Anfitrion.application;

import com.proyecto.utec_roomie.Anfitrion.dto.AnfitrionResponseDto;
import com.proyecto.utec_roomie.Anfitrion.domain.AnfitrionService;
import com.proyecto.utec_roomie.Anfitrion.dto.AnfitrionRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController("/anfitrion")
public class AnfitrionController
{
    @Autowired
    private AnfitrionService anfitrionService;

    @PostMapping
    public ResponseEntity<Void> anadirAnfitrion(@RequestBody AnfitrionRequestDto anfitrionRequestDto){
        String uri = anfitrionService.anadirAnfitrion(anfitrionRequestDto);
        return ResponseEntity.created(URI.create(uri)).build();
    }
    @GetMapping("/{anfitrion_id}")
    public ResponseEntity<AnfitrionResponseDto> getAnfitrion(@PathVariable Long anfitrion_id){
        return ResponseEntity.ok(anfitrionService.getAnfitrion(anfitrion_id));
    }

    @PatchMapping("/{anfitrion_id}")
    public ResponseEntity<Void> actualizarAnfitrion(@PathVariable Long anfitrion_id,@RequestBody AnfitrionResponseDto anfitrionActualizacionDto){
        anfitrionService.actualizarAnfitrion(anfitrion_id, anfitrionActualizacionDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{anfitrion_id}")
    public ResponseEntity<Void> eliminarAnfitrion(@PathVariable Long anfitrion_id){
        anfitrionService.eliminarAnfitrion(anfitrion_id);
        return ResponseEntity.ok().build();
    }

}
