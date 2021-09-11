package ClinicManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Specialization")
public class Specialization implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int specId;
    @Column(unique = true)
    private String name;
    private boolean isActive = true;
    @OneToMany(targetEntity = SpecDoctor.class,mappedBy = "specialization",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "specialization",allowSetters = true)
    private Set<SpecDoctor> specDoctor;

    public Specialization(int spec_id, String name) {
        this.specId = spec_id;
        this.name = name;
    }

    public Specialization() {
    }

    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
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

    public Set<SpecDoctor> getSpecDoctor() {
        return specDoctor;
    }

    public void setSpecDoctor(Set<SpecDoctor> specDoctor) {
        this.specDoctor = specDoctor;
    }
}
