package ClinicManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "WorkDays")
public class WorkDays implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workDayId;
    @Column(unique = true)
    private String name;
    private boolean isActive = true;

    @OneToMany(targetEntity = WorkTimeDoctor.class,mappedBy = "workDays" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "workDays",allowSetters = true)
    private Set<WorkTimeDoctor> workTimeDoctors;


    public WorkDays() {
    }

    public WorkDays(int workDayId, String name) {
        this.workDayId = workDayId;
        this.name = name;
    }

    public int getWorkDayId() {
        return workDayId;
    }

    public void setWorkDayId(int workDayId) {
        this.workDayId = workDayId;
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

    public Set<WorkTimeDoctor> getWorkTimeDoctors() {
        return workTimeDoctors;
    }

    public void setWorkTimeDoctors(Set<WorkTimeDoctor> workTimeDoctors) {
        this.workTimeDoctors = workTimeDoctors;
    }


}
