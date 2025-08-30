package com.queenmmama.safeguarding.safeguarding_api.concerns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CONCERNServiceTest {

    @Mock
    private CONCERNRepository concernRepository;

    @InjectMocks
    private CONCERNService concernService;

    private CONCERN concern;
    private UUID concernId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        concernId = UUID.randomUUID();
        concern = new CONCERN(
                concernId,
                "Alice Johnson",
                "Teacher A",
                "Bullying in class",
                "Open",
                Instant.now()
        );
    }

    @Test
    void testGetAllConcerns() {
        when(concernRepository.findAll()).thenReturn(List.of(concern));

        List<CONCERN> result = concernService.getAllCONCERNs();

        assertEquals(1, result.size());
        assertEquals("Alice Johnson", result.get(0).getStudentName());
        verify(concernRepository, times(1)).findAll();
    }

    @Test
    void testGetConcern_Found() {
        when(concernRepository.findById(concernId)).thenReturn(Optional.of(concern));

        CONCERN result = concernService.getCONCERN(concernId);

        assertNotNull(result);
        assertEquals("Bullying in class", result.getDescription());
        verify(concernRepository, times(1)).findById(concernId);
    }

    @Test
    void testGetConcern_NotFound() {
        when(concernRepository.findById(concernId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> concernService.getCONCERN(concernId));
    }

    @Test
    void testCreateConcern_Valid() {
        when(concernRepository.save(concern)).thenReturn(concern);

        CONCERN saved = concernService.createCONCERN(concern);

        assertNotNull(saved);
        assertEquals("Open", saved.getStatus());
        verify(concernRepository, times(1)).save(concern);
    }

    @Test
    void testCreateConcern_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> concernService.createCONCERN(null));
    }

    @Test
    void testUpdateConcern_Success() {
        CONCERN updatedConcern = new CONCERN(
                concernId,
                "Alice Johnson",
                "Teacher A",
                "Updated description",
                "Resolved",
                Instant.now()
        );

        when(concernRepository.findById(concernId)).thenReturn(Optional.of(concern));
        when(concernRepository.save(any(CONCERN.class))).thenReturn(updatedConcern);

        CONCERN result = concernService.updateCONCERN(concernId, updatedConcern);

        assertEquals("Resolved", result.getStatus());
        assertEquals("Updated description", result.getDescription());
    }

    @Test
    void testUpdateConcern_NotFound() {
        when(concernRepository.findById(concernId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> concernService.updateCONCERN(concernId, concern));
    }

    @Test
    void testDeleteConcern_Success() {
        when(concernRepository.existsById(concernId)).thenReturn(true);

        concernService.deleteCONCERN(concernId);

        verify(concernRepository, times(1)).deleteById(concernId);
    }

    @Test
    void testDeleteConcern_NotFound() {
        when(concernRepository.existsById(concernId)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> concernService.deleteCONCERN(concernId));
    }

    @Test
    void testGetConcernsByStudentName() {
        when(concernRepository.findByStudentName("Alice Johnson")).thenReturn(List.of(concern));

        List<CONCERN> result = concernService.getCONCERNsByStudentName("Alice Johnson");

        assertEquals(1, result.size());
        assertEquals("Alice Johnson", result.get(0).getStudentName());
    }
}
