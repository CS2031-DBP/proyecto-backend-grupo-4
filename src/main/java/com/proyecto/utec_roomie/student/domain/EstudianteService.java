package com.proyecto.utec_roomie.student.domain;


import com.proyecto.utec_roomie.host.infrastructure.AnfitrionRepository;
import com.proyecto.utec_roomie.student.infrastructure.EstudianteRepository;
import com.proyecto.utec_roomie.roomie.infrastructure.RoomieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository<Estudiante> estudianteRepository;

    @Autowired
    private AnfitrionRepository anfitrionRepository;

    @Autowired
    private RoomieRepository roomieRepository;

    public Estudiante findByEmail(String username, String tipo) {
        Estudiante user;
        if (tipo.equals("ROOMIE"))
            user = roomieRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        else
            user = anfitrionRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user;
    }

    @Bean(name = "UserDetailsService")
    public UserDetailsService userDetailsService() {
        return username -> {
            Estudiante user = estudianteRepository
                    .findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return (UserDetails) user;
        };
    }
}
