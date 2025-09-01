package com.queenmmama.safeguarding.safeguarding_api.concerns;

import org.springframework.data.repository.ListCrudRepository;
import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing CONCERN entities.
 * 
 * <p>Extends ListCrudRepository to provide standard CRUD operations for safeguarding concerns 
 * with {@link UUID} as the unique identifier.<p>
 * 
 * <p>Custom query methods for the CONCERN entity.</p>
 * 
 * <p>Includes a method to find concerns by student name.</p>
 * @see CONCERN
 * 
 */

public interface CONCERNRepository extends ListCrudRepository<CONCERN, UUID> {
    List<CONCERN> findByStudentName(String studentName);

    /**
     * Finds a list of CONCERN entities associated with the specified student name.
     * 
     * @param studentName the name of the student to filter concerns by
     * @return a list of CONCERN entities matching the given student name
     * 
     */

}
