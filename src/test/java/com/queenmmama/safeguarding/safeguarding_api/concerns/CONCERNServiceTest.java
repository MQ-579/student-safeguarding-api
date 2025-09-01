package com.queenmmama.safeguarding.safeguarding_api.concerns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link CONCERNService} class using JUnit and Mockito.
 * 
 * <p>This class tests the CRUD operations of {@link CONCERNService} by mocking
 * {@link CONCERNRepository}. It verifies that the service methods behave correctly
 * under various scenarios including success and failure cases.</p>
 * 
 * <p>Tested scenarios include:</p>
 * <ul>
 *  <li>Retrieving all concerns</li>
 *  <li>Retrieving a concern by ID (found and not found)</li>
 *  <li>Creating a concern (valid and invalid data)</li>
 *  <li>Updating a concern (success and not found)</li>
 *  <li>Deleting a concern (success and not found)</li>
 *  <li>Retrieving concerns by student name</li>
 * </ul>
 * 
 */
class CONCERNServiceTest {

    @Mock
    private CONCERNRepository concernRepository;

    @InjectMocks
    private CONCERNService concernService;

    private CONCERN concern;
    private UUID concernId;

    /**
     * Sets up the test environment before each test case.
     * Initializes Mockito annotations and creates a sample CONCERN object.
     */
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

    /**
     * Tests retrieving all concerns.
     * Verifies that the service returns the correct list of concerns from the repository.
     * 
     */
    @Test
    void testGetAllConcerns() {
        when(concernRepository.findAll()).thenReturn(List.of(concern));

        List<CONCERN> result = concernService.getAllCONCERNs();

        assertEquals(1, result.size());
        assertEquals("Alice Johnson", result.get(0).getStudentName());
        verify(concernRepository, times(1)).findAll();
    }

    /**
     * Tests retrieving a concern by ID when the concern exists.
     * Verifies that the service returns the correct concern.
     */
    @Test
    void testGetConcern_Found() {
        when(concernRepository.findById(concernId)).thenReturn(Optional.of(concern));

        CONCERN result = concernService.getCONCERN(concernId);

        assertNotNull(result);
        assertEquals("Bullying in class", result.getDescription());
        verify(concernRepository, times(1)).findById(concernId);
    }

    /**
     * Tests retrieving a concern by ID when the concern does not exist.
     * Verifies that the service throws a NoSuchElementException.
     */
    @Test
    void testGetConcern_NotFound() {
        when(concernRepository.findById(concernId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> concernService.getCONCERN(concernId));
    }

    /**
     * Tests creating a concern with valid data.
     * Verifies that the service saves and returns the concern correctly.
     */
    @Test
    void testCreateConcern_Valid() {
        when(concernRepository.save(concern)).thenReturn(concern);

        CONCERN saved = concernService.createCONCERN(concern);

        assertNotNull(saved);
        assertEquals("Open", saved.getStatus());
        verify(concernRepository, times(1)).save(concern);
    }

    /**
     * Tests creating a concern with invalid data (null).
     * Verifies that the service throws an IllegalArgumentException.
     */
    @Test
    void testCreateConcern_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> concernService.createCONCERN(null));
    }

    /**
     * Tests updating a concern successfully.
     * Verifies that the service updates and returns the concern correctly.
     */
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

    /**
     * Tests updating a concern that does not exist.
     * Verifies that the service throws a NoSuchElementException.
     */
    @Test
    void testUpdateConcern_NotFound() {
        when(concernRepository.findById(concernId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> concernService.updateCONCERN(concernId, concern));
    }

    /**
     * Tests deleting a concern successfully.
     * Verifies that the service calls the repository to delete the concern.
     */
    @Test
    void testDeleteConcern_Success() {
        when(concernRepository.existsById(concernId)).thenReturn(true);

        concernService.deleteCONCERN(concernId);

        verify(concernRepository, times(1)).deleteById(concernId);
    }

    /**
     * Tests deleting a concern that does not exist.
     * Verifies that the service throws a NoSuchElementException.
     */
    @Test
    void testDeleteConcern_NotFound() {
        when(concernRepository.existsById(concernId)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> concernService.deleteCONCERN(concernId));
    }

    /**
     * Tests retrieving concerns by student name.
     * Verifies that the service returns the correct list of concerns for the given student name.
     */
    @Test
    void testGetConcernsByStudentName() {
        when(concernRepository.findByStudentName("Alice Johnson")).thenReturn(List.of(concern));

        List<CONCERN> result = concernService.getCONCERNsByStudentName("Alice Johnson");

        assertEquals(1, result.size());
        assertEquals("Alice Johnson", result.get(0).getStudentName());
    }
}
