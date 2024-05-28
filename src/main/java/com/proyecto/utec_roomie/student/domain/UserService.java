package com.proyecto.utec_roomie.student.domain;


import com.proyecto.utec_roomie.host.infrastructure.AnfitrionRepository;
import com.proyecto.utec_roomie.student.infrastructure.UserRepository;
import com.proyecto.utec_roomie.roomie.infrastructure.RoomieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository<Users> userRepository;

    @Autowired
    private AnfitrionRepository anfitrionRepository;

    @Autowired
    private RoomieRepository roomieRepository;

    public Users findByEmail(String username, String tipo) {
        Users users;
        if (tipo.equals("ROLE_ROOMIE"))
            users = roomieRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Users not found"));
        else
            users = anfitrionRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Users not found"));

        return users;
    }

    @Bean(name = "UserDetailsService")
    public UserDetailsService userDetailsService() {
        return username -> {
            Users users = userRepository
                    .findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Users not found"));
            return org.springframework.security.core.userdetails.User.builder()
                    .username(users.getUsername())
                    .password(users.getPassword())
                    .roles(users.getRolePrefix()+ users.getRole())
                    .build();
        };
    }
}
