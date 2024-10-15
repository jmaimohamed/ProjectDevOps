package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EtudiantServiceImplMock {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant;
    private List<Etudiant> etudiantList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        etudiant = new Etudiant(1L, "John", "Doe", 12345678L, new Date(), null);
        etudiantList = new ArrayList<>();
        etudiantList.add(etudiant);
        etudiantList.add(new Etudiant(2L, "Jane", "Doe", 87654321L, new Date(), null));
    }

    @Test
    void testRetrieveAllEtudiants() {
        when(etudiantRepository.findAll()).thenReturn(etudiantList);
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();
        assertEquals(2, result.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveEtudiant() {
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));
        Etudiant result = etudiantService.retrieveEtudiant(1L);
        assertNotNull(result);
        assertEquals("John", result.getNomEtudiant());
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    void testRetrieveEtudiant_NotFound() {
        when(etudiantRepository.findById(3L)).thenReturn(Optional.empty());
        Etudiant result = etudiantService.retrieveEtudiant(3L);
        assertNull(result);
        verify(etudiantRepository, times(1)).findById(3L);
    }

    @Test
    void testAddEtudiant() {
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
        Etudiant result = etudiantService.addEtudiant(etudiant);
        assertNotNull(result);
        assertEquals("John", result.getNomEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testModifyEtudiant() {
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
        Etudiant result = etudiantService.modifyEtudiant(etudiant);
        assertNotNull(result);
        assertEquals("John", result.getNomEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testRemoveEtudiant() {
        doNothing().when(etudiantRepository).deleteById(1L);
        etudiantService.removeEtudiant(1L);
        verify(etudiantRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRecupererEtudiantParCin() {
        when(etudiantRepository.findEtudiantByCinEtudiant(12345678L)).thenReturn(etudiant);
        Etudiant result = etudiantService.recupererEtudiantParCin(12345678L);
        assertNotNull(result);
        assertEquals(12345678L, result.getCinEtudiant());
        verify(etudiantRepository, times(1)).findEtudiantByCinEtudiant(12345678L);
    }
}
