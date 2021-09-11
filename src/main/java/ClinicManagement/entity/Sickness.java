package ClinicManagement.entity;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Sickness")
public class Sickness implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sicknessId;
    @Column(unique = true)
    private String name;
    private boolean isActive = true;

   @OneToMany(targetEntity = PatientSickness.class,mappedBy = "sickness" , cascade = CascadeType.ALL)
   @JsonIgnoreProperties(value = "sickness",allowSetters = true)
   private Set<PatientSickness> patientSicknesses ;

    public Sickness(int sickness_id, String name) {
        this.sicknessId = sickness_id;
        this.name = name;
    }

    public Sickness() {
    }

    public int getSicknessId() {
        return sicknessId;
    }

    public void setSicknessId(int sicknessId) {
        this.sicknessId = sicknessId;
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

    public Set<PatientSickness> getPatientSicknesses() {
        return patientSicknesses;
    }

    public void setPatientSicknesses(Set<PatientSickness> patientSicknesses) {
        this.patientSicknesses = patientSicknesses;
    }
}
