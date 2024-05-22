package com.proyecto.utec_roomie.auth.domain;

import com.proyecto.utec_roomie.Anfitrion.domain.Anfitrion;
import com.proyecto.utec_roomie.Estudiante.domain.Estudiante;
import com.proyecto.utec_roomie.Estudiante.domain.TipoEstudiante;
import com.proyecto.utec_roomie.Estudiante.infrastructure.EstudianteRepository;
import com.proyecto.utec_roomie.Roomie.domain.Roomie;
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

    private final EstudianteRepository<Estudiante> estudianteRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthService(EstudianteRepository<Estudiante> estudianteRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.estudianteRepository = estudianteRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = new ModelMapper();
    }

    public JwtAuthResponse login(LoginReq req) {
        Optional<Estudiante> user;
        user = estudianteRepository.findByEmail(req.getEmail());

        if (user.isEmpty()) throw new UsernameNotFoundException("Email is not registered");

        if (!passwordEncoder.matches(req.getPassword(), user.get().getPassword()))
            throw new IllegalArgumentException("Password is incorrect");

        JwtAuthResponse response = new JwtAuthResponse();

        response.setToken(jwtService.generateToken(user.get()));
        return response;
    }

    public JwtAuthResponse register(RegisterReq req){
        Optional<Estudiante> user = estudianteRepository.findByEmail(req.getEmail());
        if (user.isPresent()) throw new UserAlreadyExistException("Email is already registered");

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(req.getNombre());
        estudiante.setApellido(req.getApellido());
        estudiante.setEmail(req.getEmail());
        estudiante.setPassword(passwordEncoder.encode(req.getPassword()));
        estudiante.setTelefono(req.getTelefono());

        if(req.getIsAnfitrion()){
            Anfitrion anfitrion = modelMapper.map(estudiante,Anfitrion.class);
            anfitrion.setTipoEstudiante(TipoEstudiante.ANFITRION);
            estudianteRepository.save(anfitrion);
            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(jwtService.generateToken(anfitrion));
            return response;
        }
        else{
            Roomie roomie = modelMapper.map(estudiante,Roomie.class);
            roomie.setTipoEstudiante(TipoEstudiante.ROOMIE);
            estudianteRepository.save(roomie);
            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(jwtService.generateToken(roomie));
            return response;
        }

    }
}
