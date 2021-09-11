package ClinicManagement.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.firebase.database.DatabaseError;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "Session")
public class Session implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionId;
    private String report;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date sessionDate;
    private boolean isActive = true;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "analysesRate_id")
    @JsonIgnoreProperties(value = "session",allowSetters = true)
    private AnalysesRate analysesRate;

    @OneToMany(targetEntity = Attachment.class,mappedBy = "session" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "session",allowSetters = true)
    private Set<Attachment> attachments;

    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false)
    @JsonIgnoreProperties(value = "sessions",allowSetters = true)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id",nullable = false)
    @JsonIgnoreProperties(value = "sessions",allowSetters = true)
    private Doctor doctor;

    public Session(int session_id, String report) {
        this.sessionId = session_id;
        this.report = report;
    }
    public Session(){

    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getReport() {
        return report;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public AnalysesRate getAnalysesRate() {
        return analysesRate;
    }

    public void setAnalysesRate(AnalysesRate analysesRate) {
        this.analysesRate = analysesRate;
    }
}
