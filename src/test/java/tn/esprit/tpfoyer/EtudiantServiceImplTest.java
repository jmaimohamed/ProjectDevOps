package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.service.IEtudiantService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class EtudiantServiceImplTest {

    @Autowired
    private IEtudiantService etudiantService;

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        etudiant = new Etudiant(1L, "John", "Doe", 12345678L, new Date(), new HashSet<>());
    }

    @Test
    @Order(1)
    void testAddEtudiant() {
        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);
        assertNotNull(savedEtudiant);
        assertEquals(etudiant.getCinEtudiant(), savedEtudiant.getCinEtudiant());
    }

    @Test
    @Order(2)
    void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();
        assertNotNull(etudiants);
        assertEquals(1, etudiants.size());
    }

    @Test
    @Order(3)
    void testRetrieveEtudiant() {
        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(etudiant.getIdEtudiant());
        assertNotNull(retrievedEtudiant);
        assertEquals(etudiant.getCinEtudiant(), retrievedEtudiant.getCinEtudiant());
    }

    @Test
    @Order(4)
    void testModifyEtudiant() {
        etudiant.setNomEtudiant("Modified Name");
        Etudiant updatedEtudiant = etudiantService.modifyEtudiant(etudiant);
        assertEquals("Modified Name", updatedEtudiant.getNomEtudiant());
    }

    @Test
    @Order(5)
    void testRemoveEtudiant() {
        etudiantService.removeEtudiant(etudiant.getIdEtudiant());
        Etudiant deletedEtudiant = etudiantService.retrieveEtudiant(etudiant.getIdEtudiant());
        assertNull(deletedEtudiant); // Ensure it was deleted
    }
}
