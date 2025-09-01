package com.queenmmama.safeguarding.safeguarding_api.concerns;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * REST controller that exposes CRUD API endpoints for managing safeguarding concerns.
 * 
 * <p>Supports the following operations:</p>
 * <ul>
 *   <li>Get all concerns or filter concerns by student name</li>
 *   <li>Get a specific concern by ID</li>
 *   <li>Create a new concern</li>
 *   <li>Update an existing concern</li>
 *   <li>Delete a concern</li>
 * </ul>
 */
@RestController
@RequestMapping({"/api/concerns"})

public class CONCERNController {
    private final CONCERNService concernService;

    /**
     * Constructs a new CONCERNController with the specified CONCERNService.
     * 
     * @param concernService the service used to handle concern operations
     */

    public CONCERNController(CONCERNService concernService) {
        this.concernService = concernService;
    }
    
    /**
     * Retrieves a list of all concerns or filters concerns by student name if provided.
     * @param studentName (optional) the name of the student to filter concerns
     * @return a list of CONCERN (all concerns or filtered by student name)
     * SpringDataJPA query method findByStudentName
     */
    
    @GetMapping
    public List<CONCERN> getCONCERNs(@RequestParam(required = false) String studentName) {
        return studentName != null && !studentName.isBlank() ? this.concernService.getCONCERNsByStudentName(studentName) : this.concernService.getAllCONCERNs();
    }

    /**
     * Retrieves a specific concern by its unique ID.
     * 
     * @param id the UUID of the concern to retrieve
     * @return a ResponseEntity containing the CONCERN if found, or a 404 Not Found status if not found
     * @throws NoSuchElementException if no concern with the specified ID exists
     * 
     */

    @GetMapping("/{id}")
    public ResponseEntity<CONCERN> getCONCERN(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(this.concernService.getCONCERN(id));
        } catch (NoSuchElementException var3) {
            return ResponseEntity.notFound().build();
        }   
    }

    /**
     * Creates a new safeguarding concern with the provided details.
     * 
     * @param concern the CONCERN object containing the details of the concern to create
     * @return a ResponseEntity containing the created CONCERN and a 201 Created status
     * @throws IllegalArgumentException if the provided concern details are invalid
     * 
     */

    @PostMapping
    public ResponseEntity<CONCERN> createCONCERN(@RequestBody CONCERN concern) {
        try {
            CONCERN Saved = this.concernService.createCONCERN(concern);
            return ResponseEntity.status(HttpStatus.CREATED).body(Saved);
        } catch (IllegalArgumentException var3) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Updates an existing safeguarding concern identified by its ID with the provided details.
     * 
     * @param id the UUID of the concern to update
     * @param updatedCONCERN the updated CONCERN details
     * @return a ResponseEntity containing the updated CONCERN if successful, or a 404 Not Found status if the concern does not exist
     * @throws NoSuchElementException if no concern with the specified ID exists
     * 
     */

    @PutMapping({"/{id}"})
   public ResponseEntity<CONCERN> updateCONCERN(@PathVariable UUID id, @RequestBody CONCERN updatedCONCERN) {
      try {
         CONCERN updated = this.concernService.updateCONCERN(id, updatedCONCERN);
         return ResponseEntity.ok(updated);
      } catch (NoSuchElementException var4) {
         return ResponseEntity.notFound().build();
      }
   }

   /**
    * Deletes a safeguarding concern identified by its ID.

    * @param id the UUID of the concern to delete
    * @return a ResponseEntity with a 204 No Content status if deletion is successful, or a 404 Not Found status if the concern does not exist
    * @throws NoSuchElementException if no concern with the specified ID exists

    */

    @DeleteMapping({"/{id}"})
     public ResponseEntity<Void> deleteCONCERN(@PathVariable UUID id) {
      try {
         this.concernService.deleteCONCERN(id);
         return ResponseEntity.noContent().build();
      } catch (NoSuchElementException var3) {
         return ResponseEntity.notFound().build();
      }
    }
}
    



