package ClinicManagement.service;

import ClinicManagement.entity.Specialization;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SpecializationService {

    @Autowired
    private SpecializationRepository repository;

    public List<Specialization> getSpecializations(boolean isActive) {

        List<Specialization> specializations = repository.findByIsActive(isActive);
        if (specializations.isEmpty()) throw new NotFoundException();
        return specializations;
    }

    public Specialization addSpecialization(Specialization specialization) {
        try{
            return repository.save(specialization);
        }catch(Exception ex){
            throw new ReplicatedException();
        }
    }

    public Specialization updateSpecialization(Specialization specialization) {
        try{
           return repository.save(specialization);
        }catch(Exception ex){
            throw new ReplicatedException();
        }
    }

    public Specialization getSpecializationById(int id,boolean isActive) {
        try {
            return repository.findBySpecIdAndIsActive(id,isActive).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException();
        }
    }
    public void activeSpecialization(int id, boolean isActive){
        try {
            Specialization specialization = repository.findById(id).get();
            specialization.setActive(isActive);
            repository.save(specialization);
        }
        catch (NoSuchElementException ex)
        {
            throw new NotFoundException("Can't active or non-active for not found object");
        }

    }

}
