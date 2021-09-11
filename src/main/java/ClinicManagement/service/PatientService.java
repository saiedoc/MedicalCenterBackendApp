package ClinicManagement.service;


import ClinicManagement.entity.*;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private SicknessRepository sicknessRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SessionRepository sessionRepository;





    public Patient addPatient(Patient patient) {
        try {
            return patientRepository.save(patient);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }
    }

    public Patient updatePatient(Patient patient) {
        try {
            return patientRepository.save(patient);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }
    }


    public List<Patient> getPatients(boolean isActive) {
        List<Patient> patients = patientRepository.findByIsActive(isActive);
        if (patients.isEmpty()) throw new NotFoundException();
        return patients;
    }

    public Patient getPatientById(int id,boolean isActive) {
        try {
            return patientRepository.findByPatientIdAndIsActive(id,isActive).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException();
        }
    }

    public List<Patient> getPatientsByName(String name,boolean isActive) {
        List<Patient> patients = patientRepository.findByNameAndIsActive(name,isActive);
        if (patients.isEmpty()) throw new NotFoundException();
        return patients;
    }

    public List<Patient> getPatientByMedicine(String medicine_name,boolean isPatientActive,boolean isMedicineActive) {
        List<Patient> patients = patientRepository.findByMedicine(medicine_name,isPatientActive,isMedicineActive);
        if (patients.isEmpty()) throw new NotFoundException();
        return patients;

    }

    public List<Patient> getPatientBytAddress(Address address,boolean isActive) {
        List<Patient> patients = patientRepository.findByAddressAndIsActive(address,isActive);
        if (patients.isEmpty()) throw new NotFoundException();
        return patients;
    }

    public List<Patient> getPatientByDoctorId(int id,boolean isPatientActive,boolean isDoctorActive) {
        List<Patient> patients = patientRepository.findByDoctorId(id,isPatientActive,isDoctorActive);
        if (patients.isEmpty()) throw new NotFoundException();
        return patients;
    }


    public List<Patient> getPatientBySickness(String sicknessName,boolean isActive) {
        return patientRepository.findBySickness(sicknessName,isActive);
    }

    public List<Patient> getPatientsByDate(Date dateOfBirth, boolean isActive) {
        List<Patient> patients = patientRepository.findByDateOfBirthAndIsActive(dateOfBirth,isActive);
        if (patients.isEmpty()) throw new NotFoundException();
        return patients;
    }


    public List<Session> getReportsForPatient(int patientID, int doctorID,boolean isPatientActive,boolean isDoctorActive) {
        List<Session> sessionResponses = sessionRepository.findSessionForDoctorAndPatientId(patientID, doctorID,isPatientActive,isDoctorActive);
        if (sessionResponses.isEmpty()) throw new NotFoundException();
        return sessionResponses;
    }


    public void linkPatientWithSickness(int patientID, int sicknessID) {

        Patient patient = patientRepository.findById(patientID).get();
        Sickness sickness = sicknessRepository.findById(sicknessID).get();
        patientRepository.save(patient);

    }

    public void cancelationSicknessForPatient(int sicknessID, int patientID) {
        Patient patient = patientRepository.findById(patientID).get();
        Sickness sickness = sicknessRepository.findById(sicknessID).get();
        patientRepository.save(patient);

    }

    public void activeDoctor(int id, boolean isActive) {
        try {
            Patient patient = patientRepository.findById(id).get();
            patient.setActive(isActive);
            patientRepository.save(patient);
        }catch (NoSuchElementException ex)
        {
            throw new NotFoundException("Can't active or non-active for not found object");
        }

    }

    public Patient getPatientByAccount(int id, boolean isActive) {
        try {
            return patientRepository.findByAccountIdAndIsActive(id,isActive).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException();
        }
    }
}
