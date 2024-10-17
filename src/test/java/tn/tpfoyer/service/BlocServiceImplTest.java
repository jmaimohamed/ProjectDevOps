package tn.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class BlocServiceImplTest {

    @InjectMocks
    private BlocServiceImpl blocService;

    @Mock
    private BlocRepository blocRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllBlocs() {
        Foyer foyer = new Foyer();
        Set<Chambre> chambres = new HashSet<>();
        Bloc bloc1 = new Bloc(1L, "Bloc A", 50L, foyer, chambres);
        Bloc bloc2 = new Bloc(2L, "Bloc B", 100L, foyer, chambres);
        List<Bloc> blocs = Arrays.asList(bloc1, bloc2);

        when(blocRepository.findAll()).thenReturn(blocs);

        List<Bloc> result = blocService.retrieveAllBlocs();

        assertEquals(2, result.size());
        verify(blocRepository, times(1)).findAll();
    }

    @Test
    public void testSqlInjectionOnInsert() {
        String maliciousInput = "'; DROP TABLE bloc; --";

        Bloc bloc = new Bloc();
        bloc.setNomBloc(maliciousInput);
        bloc.setCapaciteBloc(100);

        try {
            blocRepository.save(bloc);  // Essayer d'insérer le bloc avec une entrée malveillante
            System.out.println("Bloc enregistré avec succès. Aucune exception levée.");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Exception levée lors de l'enregistrement du bloc : " + e.getMessage());
        }

        // Vérifier le nombre de blocs dans la table
        long count = blocRepository.count();
        System.out.println("Nombre de blocs dans la table après l'insertion : " + count);

        long existingBlocCount = blocRepository.findAll().size();
        System.out.println("Nombre de blocs existants : " + existingBlocCount);

        blocRepository.deleteAll();  // Optionnel : vider la table après le test
    }

    @Test
    public void testRetrieveBloc() {
        Foyer foyer = new Foyer();
        Set<Chambre> chambres = new HashSet<>();
        Bloc bloc = new Bloc(1L, "Bloc A", 50L, foyer, chambres);
        when(blocRepository.findById(anyLong())).thenReturn(Optional.of(bloc));

        Bloc result = blocService.retrieveBloc(1L);

        assertNotNull(result);
        assertEquals("Bloc A", result.getNomBloc());
        verify(blocRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddBloc() {
        Foyer foyer = new Foyer();
        Set<Chambre> chambres = new HashSet<>();
        Bloc bloc = new Bloc(0, "Bloc A", 50L, foyer, chambres);
        when(blocRepository.save(any(Bloc.class))).thenReturn(new Bloc(1L, "Bloc A", 50L, foyer, chambres));

        Bloc result = blocService.addBloc(bloc);

        assertNotNull(result);
        assertEquals("Bloc A", result.getNomBloc());
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    public void testModifyBloc() {
        Foyer foyer = new Foyer();
        Set<Chambre> chambres = new HashSet<>();
        Bloc bloc = new Bloc(1L, "Bloc A", 50L, foyer, chambres);
        when(blocRepository.save(any(Bloc.class))).thenReturn(bloc);

        Bloc result = blocService.modifyBloc(bloc);

        assertNotNull(result);
        assertEquals("Bloc A", result.getNomBloc());
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    public void testTrouverBlocsSansFoyer() {
        Foyer foyer = new Foyer();
        Set<Chambre> chambres = new HashSet<>();
        Bloc bloc1 = new Bloc(1L, "Bloc A", 50L, foyer, chambres);
        List<Bloc> blocs = Arrays.asList(bloc1);

        when(blocRepository.findAllByFoyerIsNull()).thenReturn(blocs);

        List<Bloc> result = blocService.trouverBlocsSansFoyer();

        assertEquals(1, result.size());
        verify(blocRepository, times(1)).findAllByFoyerIsNull();
    }

    @Test
    public void testTrouverBlocsParNomEtCap() {
        Foyer foyer = new Foyer();
        Set<Chambre> chambres = new HashSet<>();
        Bloc bloc1 = new Bloc(1L, "Bloc A", 50L, foyer, chambres);
        List<Bloc> blocs = List.of(bloc1);

        when(blocRepository.findAllByNomBlocAndCapaciteBloc(anyString(), anyLong())).thenReturn(blocs);

        List<Bloc> result = blocService.trouverBlocsParNomEtCap("Bloc A", 50);

        assertEquals(1, result.size());
        verify(blocRepository, times(1)).findAllByNomBlocAndCapaciteBloc("Bloc A", 50);
    }

    @Test
    public void testSqlInjectionOnInsert1() {
        String maliciousInput = "'; DROP TABLE bloc; --";

        Bloc bloc = new Bloc();
        bloc.setNomBloc(maliciousInput);
        bloc.setCapaciteBloc(100);

        System.out.println("Bloc créé avec une tentative d'injection SQL: " + maliciousInput);

        doThrow(new DataIntegrityViolationException("could not execute statement"))
                .when(blocRepository).save(any(Bloc.class));

        System.out.println("Simulation d'une exception lors de l'enregistrement du bloc");

        DataIntegrityViolationException thrown = assertThrows(DataIntegrityViolationException.class, () -> {
            blocService.addBloc(bloc);
        });

        System.out.println("Exception levée avec le message: " + thrown.getMessage());

        assertThat(thrown.getMessage()).contains("could not execute statement");
        verify(blocRepository).save(bloc);

        System.out.println("Test terminé, vérification que la méthode save a été appelée");
    }

}
