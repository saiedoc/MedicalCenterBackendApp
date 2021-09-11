package ClinicManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Token")
public class Token  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tokenId;
    @Column(unique = true)
    private String tokenBody;
    private boolean isActive = true;

    @OneToOne(mappedBy = "token")
    @JsonIgnoreProperties(value = "token",allowSetters = true)
    private Doctor doctor;

    @OneToOne(mappedBy = "token")
    @JsonIgnoreProperties(value = "token",allowSetters = true)
    private Patient patient;

    public Token(){}
    public Token(int token_id,String tokenBody)
    {
        this.tokenId = token_id;
        this.tokenBody = tokenBody;
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenBody() {
        return tokenBody;
    }

    public void setTokenBody(String tokenBody) {
        this.tokenBody = tokenBody;
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


}
