package ClinicManagement.service;


import ClinicManagement.entity.*;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SessionService {

    @Autowired
    SessionRepository repository;



    public Session getSessionById(int id,boolean isActive){
        try{
            return repository.findBySessionIdAndIsActive(id,isActive).get();
        }catch (NoSuchElementException ex){
            throw new NotFoundException();
        }
    }

    public Session writeReport(Session session) {

        try {
            return repository.save(session);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }
    }


    public List<Session> getSessionByPatientId(int patient_id,boolean isActive) {
        List<Session> sessions = repository.findSessionByPatientId(patient_id,isActive);
        if (sessions.isEmpty()) throw new NotFoundException();
        return sessions;
    }


    public void activeSession(int id,boolean isActive){
        try
        {
            Session session = repository.findById(id).get();
            session.setActive(isActive);
            repository.save(session);
        }catch (NoSuchElementException ex)
        {
            throw  new NotFoundException("Can't active or non-active for not found object");
        }

    }
}
