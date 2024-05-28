package com.proyecto.utec_roomie.host.application;

import com.proyecto.utec_roomie.host.dto.AnfitrionRequestDto;
import com.proyecto.utec_roomie.host.dto.AnfitrionResponseDto;
import com.proyecto.utec_roomie.host.domain.AnfitrionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController("/anfitrion")
public class AnfitrionController
{

    private final AnfitrionService anfitrionService;
    @Autowired
    public AnfitrionController(AnfitrionService anfitrionService){
        this.anfitrionService = anfitrionService;
    }

    @PreAuthorize("hasRole('ROLE_ANFITRION')")
    @GetMapping
    public ResponseEntity<AnfitrionResponseDto> getAnfitrionOwnInfo(){
        return ResponseEntity.ok(anfitrionService.getAnfitrionOwnInfo());
    }

    @PreAuthorize("hasRole('ROLE_ROOMIE')")
    @GetMapping("/{anfitrion_id}")
    public ResponseEntity<AnfitrionResponseDto> getAnfitrionInfo(Long anfitrion_id){
        return ResponseEntity.ok(anfitrionService.getAnfitrion(anfitrion_id));
    }


    @PreAuthorize("hasRole('ROLE_ANFITRION')")
    @PatchMapping()
    public ResponseEntity<Void> actualizarAnfitrion(@RequestBody AnfitrionRequestDto anfitrionUpdate){
        anfitrionService.actualizarAnfitrion(anfitrionUpdate);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ANFITRION')")
    @DeleteMapping("/{anfitrion_id}")
    public ResponseEntity<Void> eliminarAnfitrion(){
        anfitrionService.eliminarAnfitrion();
        return ResponseEntity.ok().build();
    }

}
