package ClinicManagement.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Status")
public class Status  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;
    @Column(unique = true)
    private String name;

    private  boolean isActive = true;


    @OneToMany(mappedBy = "status",targetEntity = Appointment.class,cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "status",allowSetters = true)
    Set<Appointment> appointments;

    public Status() {
    }

    public Status(int statusId, String name) {
        this.statusId = statusId;
        this.name = name;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
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

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }


}
