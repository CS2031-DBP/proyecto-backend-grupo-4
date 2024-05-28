package com.proyecto.utec_roomie.user.infrastructure;

import com.proyecto.utec_roomie.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
    Optional<T> findByEmail(String email);
}
