package ClinicManagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "Doctor")
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;
    private String name;
    private String phoneNumber;
    private String email;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;
    private String gender;
    private boolean isActive = true;


    @OneToMany(targetEntity = SpecDoctor.class,mappedBy = "doctor",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "doctor",allowSetters = true)
    private Set<SpecDoctor> specDoctor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties(value = "doctor",allowSetters = true)
    private Account account;



    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "doctor",allowSetters = true)
    @JoinColumn(name = "token_id")
    private Token token;


    @OneToMany(targetEntity = Appointment.class,mappedBy = "doctor" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "doctor",allowSetters = true)
    private Set<Appointment> appointments;

    @OneToMany(targetEntity = WorkTimeDoctor.class,mappedBy = "doctor" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "doctor",allowSetters = true)
    private Set<WorkTimeDoctor> workTimeDoctors;



    @OneToMany(targetEntity = Session.class,mappedBy = "doctor" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "doctor",allowSetters = true)
    private Set<Session> sessions ;

    public Doctor() {
    }
    public Doctor(int doctor_id, String name, String phoneNumber,String email,Date date) {
        this.doctorId = doctor_id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = date;
    }


    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<WorkTimeDoctor> getWorkTimeDoctors() {
        return workTimeDoctors;
    }

    public void setWorkTimeDoctors(Set<WorkTimeDoctor> workTimeDoctors) {
        this.workTimeDoctors = workTimeDoctors;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public Set<SpecDoctor> getSpecDoctor() {
        return specDoctor;
    }

    public void setSpecDoctor(Set<SpecDoctor> specDoctor) {
        this.specDoctor = specDoctor;
    }

    public List<Specialization> getSpecializations()
    {
        ArrayList<Specialization> sp = new ArrayList<>();

        for(SpecDoctor sd : this.getSpecDoctor())
        {
            sp.add(sd.getSpecialization());
        }
        return sp;
    }
}
