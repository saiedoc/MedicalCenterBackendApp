package ClinicManagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.json.simple.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Entity
@Table(name = "Appointment")
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBooking;

    @JsonFormat(pattern = "HH:mm:ss")
    private Time timeOfBooking;

    private boolean isActive = true;


    @ManyToOne
    @JoinColumn(name = "doctor_id",nullable = false)
    @JsonIgnoreProperties(value = "appointments",allowSetters = true)
    private Doctor doctor ;

    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false)
    @JsonIgnoreProperties(value = "appointments",allowSetters = true)
    private Patient patient ;


    @ManyToOne
    @JoinColumn(name = "status_id",nullable = false)
    @JsonIgnoreProperties(value = "appointments",allowSetters = true)
    Status status;

    public Appointment(int appointmentId, Date dateOfBooking, Time timeOfBooking) {
        this.appointmentId = appointmentId;
        this.dateOfBooking = dateOfBooking;
        this.timeOfBooking = timeOfBooking;
    }

    public Appointment() {
    }


    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public Time getTimeOfBooking() {
        return timeOfBooking;
    }

    public void setTimeOfBooking(Time timeOfBooking) {
        this.timeOfBooking = timeOfBooking;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Appointment createAppointmentWith(Doctor doctor,Patient patient,JSONObject js) {
        Appointment appointment = new Appointment();

        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String date = (String) js.get("dateOfBooking");
            System.out.println(date);
            appointment.setDateOfBooking(new Date(format.parse(date).getTime()));

            String time = (String) js.get("timeOfBooking");
            appointment.setTimeOfBooking(Time.valueOf(time));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        return appointment;
    }

}
