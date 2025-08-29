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

@RestController
@RequestMapping({"/api/concerns"})

public class CONCERNController {
    private final CONCERNService concernService;

    public CONCERNController(CONCERNService concernService) {
        this.concernService = concernService;
    }

    @GetMapping
    public List<CONCERN> getCONCERNs(@RequestParam(required = false) String studentName) {
        return studentName != null && !studentName.isBlank() ? this.concernService.getCONCERNsByStudentName(studentName) : this.concernService.getAllCONCERNs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CONCERN> getCONCERN(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(this.concernService.getCONCERN(id));
        } catch (NoSuchElementException var3) {
            return ResponseEntity.notFound().build();
        }   
    }

    @PostMapping
    public ResponseEntity<CONCERN> createCONCERN(@RequestBody CONCERN concern) {
        try {
            CONCERN Saved = this.concernService.createCONCERN(concern);
            return ResponseEntity.status(HttpStatus.CREATED).body(Saved);
        } catch (IllegalArgumentException var3) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping({"/{id}"})
   public ResponseEntity<CONCERN> updateCONCERN(@PathVariable UUID id, @RequestBody CONCERN updatedCONCERN) {
      try {
         CONCERN updated = this.concernService.updateCONCERN(id, updatedCONCERN);
         return ResponseEntity.ok(updated);
      } catch (NoSuchElementException var4) {
         return ResponseEntity.notFound().build();
      }
   }

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
    



