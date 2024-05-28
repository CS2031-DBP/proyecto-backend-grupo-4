package com.proyecto.utec_roomie.publication.infrastructure;

import com.proyecto.utec_roomie.department.domain.Departamento;
import com.proyecto.utec_roomie.host.domain.Anfitrion;
import com.proyecto.utec_roomie.host.infrastructure.AnfitrionRepository;
import com.proyecto.utec_roomie.publication.domain.Publicacion;
import com.proyecto.utec_roomie.publication.infraestructure.PublicacionRepository;
import com.proyecto.utec_roomie.request.infrastructure.SolicitudRepository;
import com.proyecto.utec_roomie.roomie.infrastructure.RoomieRepository;
import com.proyecto.utec_roomie.user.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PublicacionRepositoryTest {

    private final SolicitudRepository solicitudRepository;
    private final TestEntityManager entityManager;
    private final PublicacionRepository publicacionRepository;
    private final AnfitrionRepository anfitrionRepository;
    private final RoomieRepository roomieRepository;

    @Autowired
    public PublicacionRepositoryTest(SolicitudRepository solicitudRepository, TestEntityManager entityManager, PublicacionRepository publicacionRepository, AnfitrionRepository anfitrionRepository, RoomieRepository roomieRepository) {
        this.solicitudRepository = solicitudRepository;
        this.entityManager = entityManager;
        this.publicacionRepository = publicacionRepository;
        this.anfitrionRepository = anfitrionRepository;
        this.roomieRepository = roomieRepository;
    }

    @BeforeEach
    public void setUp() {
        Departamento departamento1 = new Departamento();
        departamento1.setDireccion("aa");
        departamento1.setArea(145.3f);
        departamento1.setPiso(9);
        departamento1.setBano(1);
        departamento1.setCosto(145.34);
        departamento1.setHabitaciones(2);

        Anfitrion anfitrion1 = new Anfitrion();
        anfitrion1.setNombre("Anfitrion");
        anfitrion1.setApellido("Anfitrion");
        anfitrion1.setDepartamento(departamento1);
        anfitrion1.setEmail("anfitrion1@gmail.com");
        anfitrion1.setTelefono("123456789");
        anfitrion1.setCarrera("a");
        anfitrion1.setFechaNacimiento(Date.from(Instant.now()));
        anfitrion1.setPassword("a");
        anfitrion1.setFechaCreacion(Date.from(Instant.now()));
        anfitrion1.setRole(Role.ANFITRION);
        entityManager.persist(anfitrion1);

        Publicacion publicacion1 = new Publicacion();
        publicacion1.setDescripcion("a");
        publicacion1.setAnfitrion(anfitrion1);
        publicacion1.setTitulo("A");
        entityManager.persist(publicacion1);
    }

    @Test
    public void testGetPublicacion(){
        Anfitrion anfitrion = anfitrionRepository.findByEmail("anfitrion1@gmail.com").get();
        Optional<Publicacion> publicacion = publicacionRepository.findByAnfitrionEmail("anfitrion1@gmail.com");
        assertTrue(publicacion.isPresent());
    }
}
