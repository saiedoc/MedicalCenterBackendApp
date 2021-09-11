package ClinicManagement.service;

import ClinicManagement.entity.WorkDays;
import ClinicManagement.entity.WorkTimeDoctor;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.WorkDaysRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class WorkDaysService{

    @Autowired
    WorkDaysRepository repository;


    public WorkDays addWorkDay(WorkDays workDays)
    {
        try {
            return repository.save(workDays);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }
    }

    public WorkDays updateWorkDay(WorkDays workDays)
    {

        try {
            return repository.save(workDays);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }
    }

    public WorkDays getWorkDayById(int id,boolean isActive)
    {
        try{
            return repository.findByWorkDayIdAndIsActive(id,isActive).get();
        }catch (NoSuchElementException ex){
            throw new NotFoundException();
        }
    }

    public List<WorkDays> getWorkDays(boolean isActive)
    {
        List<WorkDays> workDays = repository.findByIsActive(isActive);
        if (workDays.isEmpty()) throw new NotFoundException();
        return workDays;
    }

    public void activeWorkDay(int id, boolean isActive){
        try {
            WorkDays workDays = repository.findById(id).get();
            workDays.setActive(isActive);
            repository.save(workDays);
        }
        catch (NoSuchElementException ex)
        {
            throw new NotFoundException("Can't active or non-active for not found object");
        }

    }

}
