package ClinicManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Medicine")
public class Medicine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int medicineId;
    @Column(unique = true)
    private String name;
    private boolean isActive = true;




    @OneToMany(targetEntity = PatientMedicine.class,mappedBy = "medicine" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "medicine",allowSetters = true)
    private Set<PatientMedicine> patientMedicines;



    public Medicine(int medicine_id, String name) {
        this.medicineId = medicine_id;
        this.name = name;
    }

    public Medicine() {
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<PatientMedicine> getPatientMedicines() {
        return patientMedicines;
    }

    public void setPatientMedicines(Set<PatientMedicine> patientMedicines) {
        this.patientMedicines = patientMedicines;
    }


}
