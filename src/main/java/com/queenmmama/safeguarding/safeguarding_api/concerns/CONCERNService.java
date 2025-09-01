package com.queenmmama.safeguarding.safeguarding_api.concerns;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Service class for managing safeguarding concerns.
 * 
 * <p>This class acts as an intermediary between the controller and the repository,
 * encapsulating business logic and provides methods to create, retrieve, update, and delete CONCERN entities.
 * It interacts with the {@link CONCERNRepository} to perform database operations.</p>
 * 
 * <p>Supported methods or operations include:</p>
 * <ul>
 *  <li>getAllCONCERNs - Retrieve all concerns</li>
 *  <li>getCONCERN - Retrieve a specific concern by ID</li>
 *  <li>createCONCERN - Create a new concern with validation check</li>
 *  <li>updateCONCERN - Update an existing concern by ID</li>
 *  <li>deleteCONCERN - Delete a concern by ID</li>
 *  <li>getCONCERNsByStudentName - Retrieve concerns filtered by student name</li>
 * </ul>
 * 
 */
@Service
public class CONCERNService {
    private final CONCERNRepository concernRepository;

    /**
     * Constructs a new {@code CONCERNService} with the specified CONCERNRepository.
     * 
     * @param concernRepository the repository used to manage CONCERN entities
     * 
     */

    public CONCERNService(CONCERNRepository concernRepository) {
        this.concernRepository = concernRepository;
    } 

    /**
     * Retrieves all safeguarding concerns from the repository.
     * 
     * @return a list of all CONCERN entities
     * 
     */

    public List<CONCERN> getAllCONCERNs() {
        return this.concernRepository.findAll();
    }

    /**
     * Retrieves a specific CONCERN by its unique ID.
     * 
     * @param id the UUID of the concern to retrieve
     * @return the CONCERN entity if found
     * @throws NoSuchElementException if no concern with the specified ID exists
     * 
     */

    public CONCERN getCONCERN(UUID id) {
        return (CONCERN)this.concernRepository.findById(id).orElseThrow(() -> {
            return new NoSuchElementException("CONCERN not found with id: " + String.valueOf(id));
        });
    }

    /**
     * Creates a new safeguarding concern with the provided details.
     * 
     * @param concern the CONCERN object containing the details of the concern to create
     * @return the created or saved CONCERN 
     * @throws IllegalArgumentException if the concern or any required field is null
     * 
     */

    public CONCERN createCONCERN(CONCERN concern) {
        if (concern != null && concern.getStudentName() != null && concern.getReportedBy() != null && concern.getDescription() != null && concern.getStatus() != null) {
            return (CONCERN)this.concernRepository.save(concern);
        } else {
            throw new IllegalArgumentException("CONCERN must not be null");
        }
    }

    /**
     * Updates an existing safeguarding concern identified by its ID with the provided details.
     * @param id the UUID of the concern to update
     * @param updatedCONCERN the CONCERN data with updated fileds
     * @return the updated CONCERN entity
     * @throws NoSuchElementException if no concern with the specified ID exists   
     *  
     */

    public CONCERN updateCONCERN(UUID id, CONCERN updatedCONCERN) {
        CONCERN existing = (CONCERN)this.concernRepository.findById(id).orElseThrow(() -> {
            return new NoSuchElementException("CONCERN not found with id: " + String.valueOf(id));
        });
        existing.setStudentName(updatedCONCERN.getStudentName());
        existing.setReportedBy(updatedCONCERN.getReportedBy());
        existing.setDescription(updatedCONCERN.getDescription());
        existing.setStatus(updatedCONCERN.getStatus());
        existing.setDateTime(updatedCONCERN.getDateTime());
        return (CONCERN)this.concernRepository.save(existing);
    }

    /**
     * Deletes a safeguarding concern identified by its ID.
     * 
     * @param id the UUID of the concern to delete
     * @throws NoSuchElementException if no concern with the specified ID exists
     * 
     */
    public void deleteCONCERN(UUID id) {
        if (!this.concernRepository.existsById(id)) {
            throw new NoSuchElementException("CONCERN not found with id: " + String.valueOf(id));
        } else {
            this.concernRepository.deleteById(id);
        }
    }

    /**
     * Retrieves a list of concerns filtered by the specified student name.
     * 
     * @param studentName the name of the student whose concerns are to be retrieved
     * @return a list of CONCERNs associated with the given student name
     * 
     */

    public List<CONCERN> getCONCERNsByStudentName(String studentName) {
        return this.concernRepository.findByStudentName(studentName);
    }
}

    
    


