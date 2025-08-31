package com.queenmmama.safeguarding.safeguarding_api.concerns;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import java.time.Instant;
import java.util.UUID;

/**
 * Entity representing a safeguarding concern reported for a student.
 * 
 * <p>This class is mapped to the "concerns" table in the database.  
 * It includes fields for the concern's ID, student name, reporter, description, status, and the timestamp of when the concern was created.</p>
 * 
 * <p>The status field defaults to "Open" if not provided. The dateTime field captures when the concern was reported.<p>
 */
@Entity
@Table(name = "concerns")


public class CONCERN {

    /**
     * The unique identifier for the concern (generated as a UUID).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    public UUID id;

    /**
     * The name of the student the concern is about.
     */
    public String studentName;

    /**
     * The name of the person who reported the concern.
     */
    public String reportedBy;

    /**
     * A detailed description of the concern.
     */
    public String description;

    /**
     * The current status of the concern (e.g., "Open", "In Progress", "Resolved").
     * Defaults to "Open" if not specified.
     */
    public String status;

    /**
     * The date and time when the concern was reported/created.
     */
    public Instant dateTime;

    /**
     * Constructs a new CONCERN with the specified details.
     * 
     * @param id The unique identifier for the concern (UUID).
     * @param studentName The name of the student the concern is about.
     * @param reportedBy The name of the person who reported the concern.
     * @param description A detailed description of the concern.
     * @param status The current status of the concern. Defaults to "Open" if null or blank.
     * @param createdAt The date and time when the concern was reported/created.
     */
    public CONCERN(UUID id, String studentName, String reportedBy, String description, String status, Instant createdAt) {
        this.id = id;
        this.studentName = studentName;
        this.reportedBy = reportedBy;
        this.description = description;
        this.status = (status == null) || (status.isBlank()) ? "Open" : status;
        this.dateTime = createdAt;
    }
    
    /**
     * Default no args constructor initializing empty values.
     * Required by JPA.
     */
    public CONCERN() {
        this(null, "", "", "", "", null);
    }

    /** @return the concern's unique ID */
    public UUID getID() {
        return this.id;
    }

    /** @return the student's name */
    public String getStudentName() {
        return this.studentName;
    }

     /** @param studentName sets the student’s name */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /** @return the reporter’s name */
    public String getReportedBy() {
        return this.reportedBy;
    }

    /** @param reportedBy sets the reporter’s name */
    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    /** @return the concern's description */
    public String getDescription() {
        return this.description;
    }

    /** @param description sets the concern's description */
    public void setDescription(String description) {
        this.description = description;
    }

    /** @return the current status of the concern */
    public String getStatus() {
        return this.status;
    }

    /** @param status sets the current status of the concern. Defaults to "Open" if null or blank */
    public void setStatus(String status) {
        this.status = (status == null || status.isBlank()) ? "Open" : status;
    }

    /** @return the date and time when the concern was reported/created */
    public Instant getDateTime() {
        return this.dateTime;
    }   

    /** @param dateTime sets the date and time when the concern was reported/created */
    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }


}
