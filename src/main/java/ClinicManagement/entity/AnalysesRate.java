package ClinicManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AnalysesRate")
public class AnalysesRate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int analysesRateId;

    private float oxygenation;
    private float bloodGlucose;
    private float bloodPressureL;
    private float bloodPressureH;

    boolean isActive = true;

    @OneToOne(mappedBy = "analysesRate")
    @JsonIgnoreProperties(value = "analysesRate",allowSetters = true)
    private Session session;



    public int getAnalysesRateId() {
        return analysesRateId;
    }

    public void setAnalysesRateId(int analysesRateId) {
        this.analysesRateId = analysesRateId;
    }

    public float getOxygenation() {
        return oxygenation;
    }

    public void setOxygenation(float oxygenation) {
        this.oxygenation = oxygenation;
    }

    public float getBloodGlucose() {
        return bloodGlucose;
    }

    public void setBloodGlucose(float bloodGlucose) {
        this.bloodGlucose = bloodGlucose;
    }

    public float getBloodPressureL() {
        return bloodPressureL;
    }

    public void setBloodPressureL(float bloodPressureL) {
        this.bloodPressureL = bloodPressureL;
    }

    public float getBloodPressureH() {
        return bloodPressureH;
    }

    public void setBloodPressureH(float bloodPressureH) {
        this.bloodPressureH = bloodPressureH;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
