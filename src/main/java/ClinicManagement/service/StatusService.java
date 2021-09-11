package ClinicManagement.service;

import ClinicManagement.entity.Status;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public Status addStatus(Status status) {
        try {
            return statusRepository.save(status);
        } catch (Exception ex) {
            throw new ReplicatedException();
        }
    }

    public Status updateStatus(Status status) {
        try {
            return statusRepository.save(status);
        } catch (Exception ex) {
            throw new ReplicatedException();
        }
    }

    public Status getStatusById(int status_id, boolean isActive) {
        try {
            return statusRepository.findByStatusIdAndIsActive(status_id, isActive).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException();
        }
    }

    public Status getStatusByName(String name, boolean isActive) {
        try {
            return statusRepository.findByNameAndIsActive(name, isActive).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException();
        }
    }

    public List<Status> getStatuses(boolean isActive) {
        List<Status> statuses = statusRepository.findByIsActive(isActive);
        if (statuses.isEmpty()) {
            throw new NotFoundException();
        }
        return statuses;
    }

    public void activeStatus(int id, boolean isActive) {
        try {
            Status status = statusRepository.findById(id).get();
            status.setActive(isActive);
            statusRepository.save(status);
        } catch (NoSuchElementException ex) {
            throw new NotFoundException();
        }
    }
}
