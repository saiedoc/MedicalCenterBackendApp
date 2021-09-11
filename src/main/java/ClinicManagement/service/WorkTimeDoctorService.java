package ClinicManagement.service;


import ClinicManagement.entity.WorkTimeDoctor;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.WorkTimeDoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;


@Service
public class WorkTimeDoctorService {

    @Autowired
    private WorkTimeDoctorRepo repository;


    public WorkTimeDoctor addWorkTimeDoctor(WorkTimeDoctor workTimeDoctor)
    {
        try {
            return repository.save(workTimeDoctor);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }
    }

    public WorkTimeDoctor updateWorkTimeDoctor(WorkTimeDoctor workTimeDoctor)
    {
        try {
            return repository.save(workTimeDoctor);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }
    }




    public WorkTimeDoctor getWorkTimeById(int id){
        try{
            return repository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new NotFoundException();
        }
    }

    public void cancelationWorkTimeForDoctor(int id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Data of work time for doctor with id : " + id + " not found to delete !!");
        }

    }
    public void activeWorkTimeDoctor(int id, boolean isActive){
        try {
            WorkTimeDoctor workTimeDoctor = repository.findById(id).get();
            workTimeDoctor.setActive(isActive);
            repository.save(workTimeDoctor);
        }
        catch (NoSuchElementException ex)
        {
            throw new NotFoundException("Can't active or non-active for not found object");
        }

    }

}
