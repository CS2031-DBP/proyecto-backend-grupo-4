package com.proyecto.utec_roomie.roomie.domain;

import com.proyecto.utec_roomie.student.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "roomie")
public class Roomie extends User {

}
