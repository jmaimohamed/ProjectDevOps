package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UniversiteServiceImplMockito {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllUniversites() {
        // Arrange
        List<Universite> universiteList = new ArrayList<>();
        Universite universite = new Universite(1, "University A", "Address A", null);
        universiteList.add(universite);
        when(universiteRepository.findAll()).thenReturn(universiteList);

        // Act
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Assert
        assertEquals(1, result.size());
        assertEquals("University A", result.get(0).getNomUniversite());
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveUniversite() {
        // Arrange
        Universite universite = new Universite(1, "University A", "Address A", null);
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));

        // Act
        Universite result = universiteService.retrieveUniversite(1L);

        // Assert
        assertNotNull(result);
        assertEquals("University A", result.getNomUniversite());
        verify(universiteRepository, times(1)).findById(1L);
    }

    @Test
    void testAddUniversite() {
        // Arrange
        Universite universite = new Universite(1, "University A", "Address A", null);
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Act
        Universite result = universiteService.addUniversite(universite);

        // Assert
        assertNotNull(result);
        assertEquals("University A", result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testModifyUniversite() {
        // Arrange
        Universite universite = new Universite(1, "University A", "Address A", null);
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Act
        Universite result = universiteService.modifyUniversite(universite);

        // Assert
        assertNotNull(result);
        assertEquals("University A", result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRemoveUniversite() {
        // Arrange
        Long universiteId = 1L;

        // Act
        universiteService.removeUniversite(universiteId);

        // Assert
        verify(universiteRepository, times(1)).deleteById(universiteId);
    }
}
