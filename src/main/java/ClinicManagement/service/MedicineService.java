package ClinicManagement.service;


import ClinicManagement.entity.Medicine;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.MedicineRepository;
import ClinicManagement.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class MedicineService {

    @Autowired
    private MedicineRepository repository;

    @Autowired
    private PatientRepository patientRepository;


    public Medicine addMedicine(Medicine medicine) {
        try {
            return repository.save(medicine);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }
    }
    public Medicine updateMedicine(Medicine medicine) {
        try {
            return repository.save(medicine);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }
    }

    public Medicine getMedicineById(int id,boolean isActive) {
        try {
            return repository.findByMedicineIdAndIsActive(id,isActive).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException();
        }
    }

    public List<Medicine> getMedicines(boolean isActive) {
        List<Medicine> medicines = repository.findByIsActive(isActive);
        if (medicines.isEmpty()) throw new NotFoundException();
        return medicines;
    }


    public List<Medicine> getMedicinesForPatient(int patientID,boolean isMedicineActive,boolean isPatientActive,boolean isPatientMedicineActive) {
       List<Medicine> medicines = repository.findMedicinesForPatient(patientID,isMedicineActive,isPatientActive,isPatientMedicineActive);
        if (medicines.isEmpty()) throw new NotFoundException();
        return medicines;

    }
    public void activeMedicine(int id, boolean isActive) {
        try {
            Medicine medicine = repository.findById(id).get();
            medicine.setActive(isActive);
            repository.save(medicine);
        } catch (NoSuchElementException ex) {
            throw new NotFoundException("Can't active or non-active for not found object");
        }
    }
}
