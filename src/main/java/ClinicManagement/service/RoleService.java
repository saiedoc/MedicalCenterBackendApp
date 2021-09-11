package ClinicManagement.service;

import ClinicManagement.entity.Role;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;



@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public List<Role> getRoles(boolean isActive) {
        List<Role> roles = repository.findByIsActive(isActive);
        if (roles.isEmpty()) throw new NotFoundException();
        return roles;
    }

    public Role addRole(Role role) {
        try{
            return repository.save(role);
        }catch (Exception ex){
            throw new ReplicatedException();

        }


    }

    public Role updateRole(Role role) {
        try{
           return repository.save(role);
        }catch (Exception ex){
            throw new ReplicatedException();

        }
    }

    public Role getRoleById(int id,boolean isActive) {

        try {
            return repository.findByRoleIdAndIsActive(id,isActive).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

    }
    public Role getRoleByName(String name,boolean isActive) {

        try {
            return repository.findByNameAndIsActive(name,isActive).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

    }

    public void activeRole(int id, boolean isActive){
        try {
            Role role = repository.findById(id).get();
            role.setActive(isActive);
            repository.save(role);
        }
        catch (NoSuchElementException ex)
        {
            throw new NotFoundException("Can't active or non-active for not found object");
        }

    }

}
