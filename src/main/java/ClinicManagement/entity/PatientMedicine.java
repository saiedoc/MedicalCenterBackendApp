package ClinicManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "PatientMedicine",uniqueConstraints = @UniqueConstraint(columnNames = {"patient_id","medicine_id"}))
public class PatientMedicine  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientMedicineId;

    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false)
    @JsonIgnoreProperties(value = "patientMedicines",allowSetters = true)
    private Patient patient;


    @OneToMany(targetEntity = MedicineTime.class,mappedBy = "patientMedicine" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "patientMedicine",allowSetters = true)
    private Set<MedicineTime> medicineTimes;

    @ManyToOne
    @JoinColumn(name = "medicine_id",nullable = false)
    @JsonIgnoreProperties(value = "patientMedicines",allowSetters = true)
    private Medicine medicine;

    public PatientMedicine() {
    }

    public int getPatientMedicineId() {
        return patientMedicineId;
    }

    public void setPatientMedicineId(int patientMedicineId) {
        this.patientMedicineId = patientMedicineId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Set<MedicineTime> getMedicineTimes() {
        return medicineTimes;
    }

    public void setMedicineTimes(Set<MedicineTime> medicineTimes) {
        this.medicineTimes = medicineTimes;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }


}
