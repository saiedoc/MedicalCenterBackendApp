package ClinicManagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(name = "WorkTimeDoctor")
public class WorkTimeDoctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wtDoctorId;
    @JsonFormat(pattern = "HH:mm:ss")
    private Time timeIN;
    @JsonFormat(pattern = "HH:mm:ss")
    private Time timeOUT;
    private boolean isActive = true;


    @ManyToOne
    @JoinColumn(name = "doctor_id",nullable = false)
    @JsonIgnoreProperties(value = "workTimeDoctors",allowSetters = true)
    private Doctor doctor ;

    @ManyToOne
    @JoinColumn(name = "workDay_id",nullable = false)
    @JsonIgnoreProperties(value = "workTimeDoctors",allowSetters = true)
    private WorkDays workDays;

    public WorkTimeDoctor(int wtDoctor_id, Time timeIN, Time timeOUT) {
        this.wtDoctorId = wtDoctor_id;
        this.timeIN = timeIN;
        this.timeOUT = timeOUT;
    }

    public WorkTimeDoctor() {
    }

    public int getWtDoctorId() {
        return wtDoctorId;
    }

    public void setWtDoctorId(int wtDoctorId) {
        this.wtDoctorId = wtDoctorId;
    }

    public Time getTimeIN() {
        return timeIN;
    }

    public void setTimeIN(Time timeIN) {
        this.timeIN = timeIN;
    }

    public Time getTimeOUT() {
        return timeOUT;
    }

    public void setTimeOUT(Time timeOUT) {
        this.timeOUT = timeOUT;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public WorkDays getWorkDays() {
        return workDays;
    }

    public void setWorkDays(WorkDays workDays) {
        this.workDays = workDays;
    }

}
