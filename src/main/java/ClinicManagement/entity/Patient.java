package ClinicManagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "Patient")
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;
    private String name;
    private String phoneNumber;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;
    private String sec_phoneNumber;
    private String gender;
    private String email;
    private boolean isActive = true;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "patient",allowSetters = true)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "patient",allowSetters = true)
    @JoinColumn(name = "token_id")
    private Token token;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @JsonIgnoreProperties(value = "patient",allowSetters = true)
    private Address address;


    @OneToMany(targetEntity = Appointment.class,mappedBy = "patient" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "patient",allowSetters = true)
    private Set<Appointment> appointments;

    @OneToMany(targetEntity = PatientSickness.class,mappedBy = "patient" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "patient",allowSetters = true)
    private Set<PatientSickness> patientSicknesses ;

    @OneToMany(targetEntity = Session.class,mappedBy = "patient" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "patient",allowSetters = true)
    private Set<Session> sessions ;


    @OneToMany(targetEntity = PatientMedicine.class,mappedBy = "patient" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "patient",allowSetters = true)
    private Set<PatientMedicine> patientMedicines;



    public Patient(int patient_id, String name, String phoneNumber,Date dateOfBirth, String sec_PhoneNumber) {
        this.patientId = patient_id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.sec_phoneNumber = sec_PhoneNumber;
    }

    public Patient() {
    }



    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSec_phoneNumber() {
        return sec_phoneNumber;
    }

    public void setSec_phoneNumber(String sec_phoneNumber) {
        this.sec_phoneNumber = sec_phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public Set<PatientMedicine> getPatientMedicines() {
        return patientMedicines;
    }

    public void setPatientMedicines(Set<PatientMedicine> patientMedicines) {
        this.patientMedicines = patientMedicines;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<PatientSickness> getPatientSicknesses() {
        return patientSicknesses;
    }

    public void setPatientSicknesses(Set<PatientSickness> patientSicknesses) {
        this.patientSicknesses = patientSicknesses;
    }
}
