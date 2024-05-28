package com.proyecto.utec_roomie.auth.domain;

import com.proyecto.utec_roomie.host.domain.Anfitrion;
import com.proyecto.utec_roomie.student.domain.Users;
import com.proyecto.utec_roomie.student.domain.Role;
import com.proyecto.utec_roomie.student.infrastructure.UserRepository;
import com.proyecto.utec_roomie.roomie.domain.Roomie;
import com.proyecto.utec_roomie.auth.dto.JwtAuthResponse;
import com.proyecto.utec_roomie.auth.dto.LoginReq;
import com.proyecto.utec_roomie.auth.dto.RegisterReq;
import com.proyecto.utec_roomie.auth.exceptions.UserAlreadyExistException;
import com.proyecto.utec_roomie.config.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository<Users> userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthService(UserRepository<Users> userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = new ModelMapper();
    }

    public JwtAuthResponse login(LoginReq req) {
        Optional<Users> user;
        user = userRepository.findByEmail(req.getEmail());

        if (user.isEmpty()) throw new UsernameNotFoundException("Email is not registered");

        if (!passwordEncoder.matches(req.getPassword(), user.get().getPassword()))
            throw new IllegalArgumentException("Password is incorrect");

        JwtAuthResponse response = new JwtAuthResponse();

        response.setToken(jwtService.generateToken(user.get()));
        return response;
    }

    public JwtAuthResponse register(RegisterReq req){
        Optional<Users> user = userRepository.findByEmail(req.getEmail());
        if (user.isPresent()) throw new UserAlreadyExistException("Email is already registered");

        if(req.getIsAnfitrion()){
            Anfitrion anfitrion = new Anfitrion();
            anfitrion.setNombre(req.getNombre());
            anfitrion.setApellido(req.getApellido());
            anfitrion.setEmail(req.getEmail());
            anfitrion.setCarrera(req.getCarrera());
            anfitrion.setFechaNacimiento(req.getFechaNacimiento());
            anfitrion.setPassword(passwordEncoder.encode(req.getPassword()));
            anfitrion.setTelefono(req.getTelefono());
            anfitrion.setRole(Role.ANFITRION);
            userRepository.save(anfitrion);
            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(jwtService.generateToken(anfitrion));
            return response;
        }
        else{
            Roomie roomie = new Roomie();
            roomie.setNombre(req.getNombre());
            roomie.setApellido(req.getApellido());
            roomie.setEmail(req.getEmail());
            roomie.setCarrera(req.getCarrera());
            roomie.setFechaNacimiento(req.getFechaNacimiento());
            roomie.setPassword(passwordEncoder.encode(req.getPassword()));
            roomie.setTelefono(req.getTelefono());
            roomie.setRole(Role.ROOMIE);
            userRepository.save(roomie);
            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(jwtService.generateToken(roomie));
            return response;
        }

    }
}
