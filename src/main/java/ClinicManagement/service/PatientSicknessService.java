package ClinicManagement.service;

import ClinicManagement.entity.PatientMedicine;
import ClinicManagement.entity.PatientSickness;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.PatientSicknessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PatientSicknessService {

    @Autowired
    PatientSicknessRepository repository;


    public PatientSickness addPatientSickness(PatientSickness patientSickness)
    {
        try{
            PatientSickness pm;
            try {
                pm = repository.findByPatientAndSickness(patientSickness.getPatient().getPatientId(), patientSickness.getSickness().getSicknessId(), false).get();
                pm.setActive(true);
            } catch (Exception ex) {
                pm = patientSickness;
            }
            return repository.save(pm);
        }catch (Exception ex){
            throw new ReplicatedException();
        }
    }
    public PatientSickness updatePatientSickness(PatientSickness patientSickness)
    {
        try{
            PatientSickness pm;
            try {
                pm = repository.findByPatientAndSickness(patientSickness.getPatient().getPatientId(), patientSickness.getSickness().getSicknessId(), false).get();
                pm.setActive(true);
            } catch (Exception ex) {
                pm = patientSickness;
            }
            return repository.save(pm);
        }catch (Exception ex){
            throw new ReplicatedException();
        }
    }

    public List<PatientSickness> updatePatientSickness(List<PatientSickness> patientSickness)
    {
        try{
            return repository.saveAll(patientSickness);
        }catch (Exception ex){
            throw new ReplicatedException();
        }
    }

    public void cancelationSicknessForPatient(int patientID, int sicknessID ) {
        try {
            PatientSickness patientSickness = repository.findByPatientAndSickness(patientID,sicknessID,true).get();
            patientSickness.setActive(false);
            repository.save(patientSickness);
        }
        catch (NoSuchElementException ex)
        {
            throw  new NotFoundException();
        }

    }

}
