package tn.esprit.tpfoyer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.tpfoyer.control.UniversiteRestController;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.service.IUniversiteService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UniversiteRestControllerTest {

    @InjectMocks
    private UniversiteRestController universiteRestController;

    @Mock
    private IUniversiteService universiteService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(universiteRestController).build();
    }

    @Test
    void testGetUniversites() throws Exception {
        // Arrange
        List<Universite> universiteList = new ArrayList<>();
        universiteList.add(new Universite(1, "University A", "Address A", null));
        when(universiteService.retrieveAllUniversites()).thenReturn(universiteList);

        // Act & Assert
        mockMvc.perform(get("/universite/retrieve-all-universites"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nomUniversite").value("University A"));

        verify(universiteService, times(1)).retrieveAllUniversites();
    }

    @Test
    void testRetrieveUniversite() throws Exception {
        // Arrange
        Universite universite = new Universite(1, "University A", "Address A", null);
        when(universiteService.retrieveUniversite(1L)).thenReturn(universite);

        // Act & Assert
        mockMvc.perform(get("/universite/retrieve-universite/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nomUniversite").value("University A"));

        verify(universiteService, times(1)).retrieveUniversite(1L);
    }

    @Test
    void testAddUniversite() throws Exception {
        // Arrange
        Universite universite = new Universite(1, "University A", "Address A", null);
        when(universiteService.addUniversite(any(Universite.class))).thenReturn(universite);

        // Act & Assert
        mockMvc.perform(post("/universite/add-universite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(universite)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomUniversite").value("University A"));

        verify(universiteService, times(1)).addUniversite(any(Universite.class));
    }

    @Test
    void testRemoveUniversite() throws Exception {
        // Arrange
        Long universiteId = 1L;

        // Act & Assert
        mockMvc.perform(delete("/universite/remove-universite/{universite-id}", universiteId))
                .andExpect(status().isOk());

        verify(universiteService, times(1)).removeUniversite(universiteId);
    }

    @Test
    void testModifyUniversite() throws Exception {
        // Arrange
        Universite universite = new Universite(1, "University A", "Address A", null);
        when(universiteService.modifyUniversite(any(Universite.class))).thenReturn(universite);

        // Act & Assert
        mockMvc.perform(put("/universite/modify-universite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(universite)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomUniversite").value("University A"));

        verify(universiteService, times(1)).modifyUniversite(any(Universite.class));
    }
}
