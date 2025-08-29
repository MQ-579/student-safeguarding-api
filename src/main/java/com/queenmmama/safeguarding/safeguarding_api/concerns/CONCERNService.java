package com.queenmmama.safeguarding.safeguarding_api.concerns;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CONCERNService {
    private final CONCERNRepository concernRepository;

    public CONCERNService(CONCERNRepository concernRepository) {
        this.concernRepository = concernRepository;
    } 

    public List<CONCERN> getAllCONCERNs() {
        return this.concernRepository.findAll();
    }

    public CONCERN getCONCERN(UUID id) {
        return (CONCERN)this.concernRepository.findById(id).orElseThrow(() -> {
            return new NoSuchElementException("CONCERN not found with id: " + String.valueOf(id));
        });
    }

    public CONCERN createCONCERN(CONCERN concern) {
        if (concern != null && concern.getStudentName() != null && concern.getReportedBy() != null && concern.getDescription() != null && concern.getStatus() != null) {
            return (CONCERN)this.concernRepository.save(concern);
        } else {
            throw new IllegalArgumentException("CONCERN must not be null");
        }
    }

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

    public void deleteCONCERN(UUID id) {
        if (!this.concernRepository.existsById(id)) {
            throw new NoSuchElementException("CONCERN not found with id: " + String.valueOf(id));
        } else {
            this.concernRepository.deleteById(id);
        }
    }

    public List<CONCERN> getCONCERNsByStudentName(String studentName) {
        return this.concernRepository.findByStudentName(studentName);
    }
}

    
    


