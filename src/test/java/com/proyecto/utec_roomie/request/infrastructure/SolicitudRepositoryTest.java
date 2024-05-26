package com.proyecto.utec_roomie.request.infrastructure;

import com.proyecto.utec_roomie.AbstractContainerBaseTest;
import com.proyecto.utec_roomie.department.domain.Departamento;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import com.proyecto.utec_roomie.host.domain.Anfitrion;
import com.proyecto.utec_roomie.host.infrastructure.AnfitrionRepository;
import com.proyecto.utec_roomie.publication.domain.Publicacion;
import com.proyecto.utec_roomie.publication.infraestructure.PublicacionRepository;
import com.proyecto.utec_roomie.request.domain.Solicitud;
import com.proyecto.utec_roomie.roomie.domain.Roomie;
import com.proyecto.utec_roomie.roomie.infrastructure.RoomieRepository;
import com.proyecto.utec_roomie.student.domain.Estudiante;
import com.proyecto.utec_roomie.student.domain.TipoEstudiante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;



import java.time.Instant;
import java.util.Date;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SolicitudRepositoryTest extends AbstractContainerBaseTest {
    private final SolicitudRepository solicitudRepository;
    private final TestEntityManager entityManager;
    private final PublicacionRepository publicacionRepository;
    private final AnfitrionRepository anfitrionRepository;
    private final RoomieRepository roomieRepository;

    @Autowired
    public SolicitudRepositoryTest(SolicitudRepository solicitudRepository, TestEntityManager entityManager, PublicacionRepository publicacionRepository, AnfitrionRepository anfitrionRepository, RoomieRepository roomieRepository) {
        this.solicitudRepository = solicitudRepository;
        this.entityManager = entityManager;
        this.publicacionRepository = publicacionRepository;
        this.anfitrionRepository = anfitrionRepository;
        this.roomieRepository = roomieRepository;
    }

    @BeforeEach
    public void setUp() {
        Departamento departamento1 = new Departamento();
        departamento1.setLatitude(100.02);
        departamento1.setLongitude(49.02);
        departamento1.setArea(145.3f);
        departamento1.setPiso(9);
        departamento1.setBano(1);
        departamento1.setCosto(145.34);
        departamento1.setHabitaciones(2);

        Departamento departamento2 = new Departamento();
        departamento2.setLatitude(50.02);
        departamento2.setLongitude(20.02);
        departamento2.setArea(145.3f);
        departamento2.setPiso(9);
        departamento2.setBano(1);
        departamento2.setCosto(145.34);
        departamento2.setHabitaciones(2);

        Departamento departamento3 = new Departamento();
        departamento3.setLatitude(160.02);
        departamento3.setLongitude(-16.02);
        departamento3.setArea(145.3f);
        departamento3.setPiso(9);
        departamento3.setBano(1);
        departamento3.setCosto(145.34);
        departamento3.setHabitaciones(2);
        entityManager.persist(departamento3);
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
        anfitrion1.setTipoEstudiante(TipoEstudiante.ANFITRION);
        entityManager.persist(anfitrion1);

        Anfitrion anfitrion2 = new Anfitrion();
        anfitrion2.setNombre("Anfitrion");
        anfitrion2.setApellido("Anfitrion");
        anfitrion2.setEmail("anfitrion2@gmail.com");
        anfitrion2.setDepartamento(departamento2);
        anfitrion2.setTelefono("123456789");
        anfitrion2.setCarrera("a");
        anfitrion2.setFechaNacimiento(Date.from(Instant.now()));
        anfitrion2.setPassword("a");
        anfitrion2.setFechaCreacion(Date.from(Instant.now()));
        anfitrion2.setTipoEstudiante(TipoEstudiante.ANFITRION);
        entityManager.persist(anfitrion2);

        Anfitrion anfitrion3 = new Anfitrion();
        anfitrion3.setNombre("Anfitrion");
        anfitrion3.setApellido("Anfitrion");
        anfitrion3.setDepartamento(departamento3);
        anfitrion3.setEmail("anfitrion3@gmail.com");
        anfitrion3.setTelefono("123456789");
        anfitrion3.setCarrera("a");
        anfitrion3.setFechaNacimiento(Date.from(Instant.now()));
        anfitrion3.setPassword("a");
        anfitrion3.setFechaCreacion(Date.from(Instant.now()));
        anfitrion3.setTipoEstudiante(TipoEstudiante.ANFITRION);
        entityManager.persist(anfitrion3);

        Roomie roomie = new Roomie();
        roomie.setNombre("roomie");
        roomie.setApellido("roomie");
        roomie.setEmail("roomie@gmail.com");
        roomie.setTelefono("023456789");
        roomie.setCarrera("a");
        roomie.setFechaNacimiento(Date.from(Instant.now()));
        roomie.setPassword("a");
        roomie.setFechaCreacion(Date.from(Instant.now()));
        roomie.setTipoEstudiante(TipoEstudiante.ROOMIE);
        entityManager.persist(roomie);

        Publicacion publicacion1 = new Publicacion();
        publicacion1.setDescripcion("a");
        publicacion1.setAnfitrion(anfitrion1);
        publicacion1.setTitulo("A");
        entityManager.persist(publicacion1);

        Publicacion publicacion2 = new Publicacion();
        publicacion2.setDescripcion("a");
        publicacion2.setAnfitrion(anfitrion2);
        publicacion2.setTitulo("A");
        entityManager.persist(publicacion2);

        Publicacion publicacion3 = new Publicacion();
        publicacion3.setDescripcion("a");
        publicacion3.setAnfitrion(anfitrion3);
        publicacion3.setTitulo("A");
        entityManager.persist(publicacion3);

        Solicitud solicitud1 = new Solicitud();
        solicitud1.setPublicacion(publicacion1);
        solicitud1.setRoomie(roomie);
        entityManager.persist(solicitud1);

        Solicitud solicitud2 = new Solicitud();
        solicitud2.setPublicacion(publicacion2);
        solicitud2.setRoomie(roomie);
        entityManager.persist(solicitud2);

        Solicitud solicitud3 = new Solicitud();
        solicitud3.setPublicacion(publicacion3);
        solicitud3.setRoomie(roomie);
        entityManager.persist(solicitud3);
    }

    @Test
    public void testGetSolicitudByRoomieAndPublicacion(){
        Anfitrion anfitrion1 = anfitrionRepository.findByEmail("anfitrion1@gmail.com").get();
        Optional<Publicacion> publicacion1 = publicacionRepository.findByAnfitrion(anfitrion1);
        if(publicacion1.isPresent()) {
            Optional<Solicitud> solicitud = solicitudRepository.findByPublicacionIdAndRoomieEmail(publicacion1.get().getId(), "roomie@gmail.com");
            assertTrue(solicitud.isPresent());
        }
        else{
            throw new ResourceNotFoundException("no se encontro");
        }
    }



}
