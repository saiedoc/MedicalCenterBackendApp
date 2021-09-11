package ClinicManagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(name = "MedicineTime")
public class MedicineTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int medicineTimeId;

    @JsonFormat(pattern = "HH:mm:ss")
    private Time medTime;

    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "patient_med_id",nullable = false)
    @JsonIgnoreProperties(value = "medicineTimes",allowSetters = true)
    private PatientMedicine patientMedicine;


    public MedicineTime() {
    }

    public MedicineTime(int medicineTimeId, Time medTime) {
        this.medicineTimeId = medicineTimeId;
        this.medTime = medTime;
    }

    public int getMedicineTimeId() {
        return medicineTimeId;
    }

    public void setMedicineTimeId(int medicineTimeId) {
        this.medicineTimeId = medicineTimeId;
    }

    public Time getMedTime() {
        return medTime;
    }

    public void setMedTime(Time medTime) {
        this.medTime = medTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public PatientMedicine getPatientMedicine() {
        return patientMedicine;
    }

    public void setPatientMedicine(PatientMedicine patientMedicine) {
        this.patientMedicine = patientMedicine;
    }


}
