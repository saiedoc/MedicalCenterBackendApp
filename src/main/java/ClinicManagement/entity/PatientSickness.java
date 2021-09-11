package ClinicManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Patient_Sickness",uniqueConstraints = @UniqueConstraint(columnNames = {"patient_id","sickness_id"}))
public class PatientSickness  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientSicknessId;
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false)
    @JsonIgnoreProperties(value = "patientSicknesses",allowSetters = true)
    private Patient patient ;

    @ManyToOne
    @JoinColumn(name = "sickness_id",nullable = false)
    @JsonIgnoreProperties(value = "patientSicknesses",allowSetters = true)
    private Sickness sickness;

    public PatientSickness(int patientSicknessId) {
        this.patientSicknessId = patientSicknessId;
    }

    public PatientSickness() {
    }

    public int getPatientSicknessId() {
        return patientSicknessId;
    }

    public void setPatientSicknessId(int patientSicknessId) {
        this.patientSicknessId = patientSicknessId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Sickness getSickness() {
        return sickness;
    }

    public void setSickness(Sickness sickness) {
        this.sickness = sickness;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
