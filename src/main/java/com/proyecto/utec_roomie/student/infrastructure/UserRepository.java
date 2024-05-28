package com.proyecto.utec_roomie.student.infrastructure;

import com.proyecto.utec_roomie.student.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository<T extends Users> extends JpaRepository<T, Long> {
    Optional<T> findByEmail(String email);
}
