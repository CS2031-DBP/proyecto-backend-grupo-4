package com.proyecto.utec_roomie.Resena.application;

import com.proyecto.utec_roomie.Resena.domain.ResenaService;
import com.proyecto.utec_roomie.Resena.dto.ResenaRequestDto;
import com.proyecto.utec_roomie.Resena.dto.ResenaResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resenas")
public class ResenaController {
    private final ResenaService resenaService;

    public ResenaController(ResenaService resenaService) {
        this.resenaService = resenaService;
    }

    @PreAuthorize("hasRole('ROLE_ANFITRION') or hasRole('ROLE_ROOMIE')")
    @PostMapping("/crear/{receptor_id}")
    public ResponseEntity<String> crearResena(@RequestBody ResenaRequestDto resena,@PathVariable Long receptor_id) {
        resenaService.crearResena(resena,receptor_id);
        return ResponseEntity.ok("Resena creada con exito!");
    }

    @PreAuthorize("hasRole('ROLE_ROOMIE') or hasRole('ROLE_ANFITRION')")
    @GetMapping
    public ResponseEntity<Page<ResenaResponseDto>> getOwnResenas(@RequestParam int page, @RequestParam int size ) {
        return ResponseEntity.ok(resenaService.getOwnResenas(page,size));
    }

    @PreAuthorize("hasRole('ROLE_ROOMIE') or hasRole('ROLE_ANFITRION')")
    @DeleteMapping("/eliminar/{resena_id}")
    public ResponseEntity<String> eliminarResena(@PathVariable Long resena_id) {
        resenaService.eliminarResena(resena_id);
        return ResponseEntity.ok("resena eliminada con exito!");
    }


}
