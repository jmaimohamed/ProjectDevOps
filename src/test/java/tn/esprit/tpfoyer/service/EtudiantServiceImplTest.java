package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Etudiant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class EtudiantServiceImplTest {

    @Autowired
    private IEtudiantService etudiantService;

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        etudiant = new Etudiant();
        etudiant.setIdEtudiant(1L);
        etudiant.setNomEtudiant("John");
        etudiant.setPrenomEtudiant("Doe");
        etudiant.setCinEtudiant(123456);
        etudiant.setDateNaissance(new java.util.Date());
    }

    @Test
    void testAddEtudiant() {
        Etudiant created = etudiantService.addEtudiant(etudiant);

        assertNotNull(created);
        assertEquals("John", created.getNomEtudiant());
    }

    @Test
    void testRetrieveEtudiant() {
        etudiantService.addEtudiant(etudiant);
        Etudiant found = etudiantService.retrieveEtudiant(etudiant.getIdEtudiant());

        assertNotNull(found);
        assertEquals("John", found.getNomEtudiant());
    }

    @Test
    void testRemoveEtudiant() {
        etudiantService.addEtudiant(etudiant);
        etudiantService.removeEtudiant(etudiant.getIdEtudiant());

        assertThrows(Exception.class, () -> {
            etudiantService.retrieveEtudiant(etudiant.getIdEtudiant());
        });
    }
}
