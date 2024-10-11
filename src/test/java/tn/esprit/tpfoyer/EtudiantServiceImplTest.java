package tn.esprit.tpfoyer;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.service.IEtudiantService;

import java.util.List;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class EtudiantServiceImplTest {

    @Autowired
    IEtudiantService us;
    @Test
    @Order(1)
    public void testRetrieveAllUsers() {
        List<Etudiant> listUsers = us.retrieveAllEtudiants();
        Assertions.assertEquals(0, listUsers.size());
    }

}
