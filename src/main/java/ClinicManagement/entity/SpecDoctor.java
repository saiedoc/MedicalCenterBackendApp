package ClinicManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Specialization_Doctor")
public class SpecDoctor  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int specDoctorId;

    boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "doctor_id",nullable = false)
    @JsonIgnoreProperties(value = "specDoctor",allowSetters = true)
    private Doctor doctor ;

    @ManyToOne
    @JoinColumn(name = "spec_id",nullable = false)
    @JsonIgnoreProperties(value = "specDoctor",allowSetters = true)
    private Specialization specialization ;

    public SpecDoctor() {

    }

    public SpecDoctor(int specDoctorId) {
        this.specDoctorId = specDoctorId;
    }

    public int getSpecDoctorId() {
        return specDoctorId;
    }

    public void setSpecDoctorId(int specDoctorId) {
        this.specDoctorId = specDoctorId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
