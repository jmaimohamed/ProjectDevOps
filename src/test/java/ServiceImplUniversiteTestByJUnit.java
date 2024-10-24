//package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.UniversiteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceImplUniversiteTestByJUnit {

    private UniversiteRepository universiteRepository;
    private UniversiteServiceImpl universiteService;

    @BeforeEach
    public void setUp() {
        universiteRepository = mock(UniversiteRepository.class);
        universiteService = new UniversiteServiceImpl(universiteRepository);
    }

    @Test
    public void testRetrieveAllUniversites() {
        // Arrange
        List<Universite> universites = new ArrayList<>();
        universites.add(new Universite(1L, "Université de Tunis", "Adresse 1", null));
        universites.add(new Universite(2L, "Université de Sfax", "Adresse 2", null));
        when(universiteRepository.findAll()).thenReturn(universites);

        // Act
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Assert
        assertEquals(2, result.size());
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    public void testRetrieveUniversite() {
        // Arrange
        Universite universite = new Universite(1L, "Université de Tunis", "Adresse 1", null);
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));

        // Act
        Universite result = universiteService.retrieveUniversite(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Université de Tunis", result.getNomUniversite());
        verify(universiteRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddUniversite() {
        // Arrange
        Universite universite = new Universite(1L, "Université de Tunis", "Adresse 1", null);
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Act
        Universite result = universiteService.addUniversite(universite);

        // Assert
        assertNotNull(result);
        assertEquals("Université de Tunis", result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testModifyUniversite() {
        // Arrange
        Universite universite = new Universite(1L, "Université de Tunis", "Adresse 1", null);
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Act
        Universite result = universiteService.modifyUniversite(universite);

        // Assert
        assertNotNull(result);
        assertEquals("Université de Tunis", result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testRemoveUniversite() {
        // Act
        universiteService.removeUniversite(1L);

        // Assert
        verify(universiteRepository, times(1)).deleteById(1L);
    }
}
