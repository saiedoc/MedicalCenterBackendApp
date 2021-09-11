package ClinicManagement.service;

import ClinicManagement.entity.Address;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository repository;

    public Address addAddress(Address address) {
        try{
            return repository.save(address);
        }catch (Exception ex){
            throw new ReplicatedException();
        }
    }

    public Address updateAddress(Address address) {
        try{
            return repository.save(address);
        }catch (Exception ex){
            throw new ReplicatedException();
        }
    }

}
