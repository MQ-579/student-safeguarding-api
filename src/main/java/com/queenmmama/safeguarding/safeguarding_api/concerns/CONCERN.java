package com.queenmmama.safeguarding.safeguarding_api.concerns;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "concerns")

public class CONCERN {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    public UUID id;
    public String studentName;
    public String reportedBy;
    public String description;
    public String status;
    public Instant dateTime;

    public CONCERN(UUID id, String studentName, String reportedBy, String description, String status, Instant createdAt) {
        this.id = id;
        this.studentName = studentName;
        this.reportedBy = reportedBy;
        this.description = description;
        this.status = (status == null) || (status.isBlank()) ? "Open" : status;
        this.dateTime = createdAt;
    }

    public CONCERN() {
        this(null, "", "", "", "", null);
    }

    public UUID getID() {
        return this.id;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getReportedBy() {
        return this.reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = (status == null || status.isBlank()) ? "Open" : status;
    }

    public Instant getDateTime() {
        return this.dateTime;
    }   

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }


}
