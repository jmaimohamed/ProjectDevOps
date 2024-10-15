package tn.esprit.tpfoyer;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.service.IEtudiantService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class EtudiantServiceImplTest {

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        etudiant = new Etudiant(1L, "John", "Doe", 12345678L, new Date(), new HashSet<>());
    }

    @Test
    void testEtudiantAttributes() {
        assertEquals(1L, etudiant.getIdEtudiant());
        assertEquals("John", etudiant.getNomEtudiant());
        assertEquals("Doe", etudiant.getPrenomEtudiant());
        assertEquals(12345678L, etudiant.getCinEtudiant());
        assertNotNull(etudiant.getDateNaissance());
    }

    @Test
    void testReservations() {
        assertNotNull(etudiant.getReservations());
        assertEquals(0, etudiant.getReservations().size());

        etudiant.getReservations().add(null);  // Example to check adding to set
        assertEquals(1, etudiant.getReservations().size());
    }


}

