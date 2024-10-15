package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;

@SpringBootTest
class ChambreServiceImplTest {

    @Autowired
    ChambreServiceImpl chambreService;

    @Test
    void testReserverChambreDisponible() {
        TypeChambre type = TypeChambre.SIMPLE;
        Chambre chambreReservee = chambreService.reserverChambreDisponible(type);

        assertNotNull(chambreReservee, "La chambre ne doit pas être nulle");
        assertEquals(type, chambreReservee.getTypeC(), "Le type de la chambre doit correspondre");
        assertFalse(chambreReservee.isDisponible(), "La chambre doit être marquée comme non disponible");
    }
}
