package ClinicManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Column(unique = true)
    private String username;
    private String password;
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    @JsonIgnoreProperties(value = "accounts",allowSetters = true)
    private Role role;

    @OneToOne(mappedBy = "account")
    @JsonIgnoreProperties(value = "account",allowSetters = true)
    private Doctor doctor;


    @OneToOne(mappedBy = "account")
    @JsonIgnoreProperties(value = "account",allowSetters = true)
    private Patient patient;


    public Account(int account_id, String username, String password) {
        this.accountId = account_id;
        this.username = username;
        this.password = password;
    }

    public Account() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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


}
