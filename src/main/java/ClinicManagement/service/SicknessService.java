package ClinicManagement.service;

import ClinicManagement.entity.Sickness;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.SicknessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SicknessService {

    @Autowired
    private SicknessRepository repository;

    public Sickness addSickness(Sickness sickness) {
        try{
           return repository.save(sickness);
        }catch (Exception ex){
            throw new ReplicatedException();
        }
    }

    public Sickness updateSickness(Sickness sickness) {
        try{
           return repository.save(sickness);
        }catch (Exception ex){
            throw new ReplicatedException();
        }
    }

    public Sickness getSicknessById(int id,boolean isActive) {
        try {
            return repository.findBySicknessIdAndIsActive(id,isActive).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException();
        }

    }

    public List<Sickness> getSicknesses(boolean isActive) {
        List<Sickness>sicknesses =repository.findByIsActive(isActive);
        if(sicknesses.isEmpty())throw new NotFoundException();
        return sicknesses;
    }


    public List<Sickness> getSicknessesForPatient(int patientID,boolean isActive) {
        List<Sickness>sicknesses =repository.findSicknessesForPatient(patientID,isActive);
        if(sicknesses.isEmpty())throw new NotFoundException();
        return sicknesses;

    }
    public void activeSickness(int id, boolean isActive){
        try {
            Sickness sickness = repository.findById(id).get();
            sickness.setActive(isActive);
            repository.save(sickness);
        }
        catch (NoSuchElementException ex)
        {
            throw new NotFoundException("Can't active or non-active for not found object");
        }

    }

}
