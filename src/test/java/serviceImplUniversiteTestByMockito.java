




//package tn.esprit.tpfoyer.service;

//package tn.esprit.tpfoyer.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.UniversiteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class serviceImplUniversiteTestByMockito {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllUniversites() {
        // Créer une liste fictive d'universités
        List<Universite> universites = new ArrayList<>();
        universites.add(new Universite(1L, "Université de Tunis", "Adresse 1", null));
        universites.add(new Universite(2L, "Université de Sfax", "Adresse 2", null));

        // Configurer le mock pour retourner la liste fictive
        when(universiteRepository.findAll()).thenReturn(universites);

        // Appeler la méthode de service
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Vérifier les résultats
        assertEquals(2, result.size());
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    public void testRetrieveUniversite() {
        // Créer une université fictive
        Universite universite = new Universite(1L, "Université de Tunis", "Adresse 1", null);

        // Configurer le mock pour retourner l'université fictive
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));

        // Appeler la méthode de service
        Universite result = universiteService.retrieveUniversite(1L);

        // Vérifier les résultats
        assertNotNull(result);
        assertEquals("Université de Tunis", result.getNomUniversite());
        verify(universiteRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddUniversite() {
        // Créer une université fictive
        Universite universite = new Universite(1L, "Université de Tunis", "Adresse 1", null);

        // Configurer le mock pour retourner l'université ajoutée
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Appeler la méthode de service
        Universite result = universiteService.addUniversite(universite);

        // Vérifier les résultats
        assertNotNull(result);
        assertEquals("Université de Tunis", result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testModifyUniversite() {
        // Créer une université fictive
        Universite universite = new Universite(1L, "Université de Tunis", "Adresse 1", null);

        // Configurer le mock pour retourner l'université modifiée
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Appeler la méthode de service
        Universite result = universiteService.modifyUniversite(universite);

        // Vérifier les résultats
        assertNotNull(result);
        assertEquals("Université de Tunis", result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testRemoveUniversite() {
        // Appeler la méthode de service
        universiteService.removeUniversite(1L);

        // Vérifier que la méthode deleteById a été appelée
        verify(universiteRepository, times(1)).deleteById(1L);
    }
}
