package ClinicManagement.service;

import ClinicManagement.entity.Doctor;
import ClinicManagement.entity.SpecDoctor;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.SpecDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpecDoctorService {

    @Autowired
    SpecDoctorRepository repository;

    public SpecDoctor addSpecDoctor(SpecDoctor specDoctor) {
        try{
            return repository.save(specDoctor);
        }catch (Exception ex){
            throw new ReplicatedException();
        }
    }

    public SpecDoctor updateSpecDoctor(SpecDoctor specDoctor) {
        try{
            return repository.save(specDoctor);
        }catch (Exception ex){
            throw new ReplicatedException();
        }
    }

    public List<SpecDoctor> getSpecDoctorByDoctor(int doctorID,boolean isActive)
    {
        List<SpecDoctor> sp = repository.findByDoctorIdAndIsActive(doctorID,isActive);
        if(sp.isEmpty()) throw new  NotFoundException();
        return sp;
    }

}
