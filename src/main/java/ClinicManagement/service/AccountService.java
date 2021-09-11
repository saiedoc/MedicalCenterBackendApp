package ClinicManagement.service;

import ClinicManagement.entity.Account;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class AccountService {

    @Autowired
    AccountRepository repository;

    public Account login(Account account) {
        try {
            Account acc = repository.findByUsernameAndPasswordAndIsActive(account.getUsername(), account.getPassword(),true);
            if (acc == null) throw new NotFoundException();
            return acc;
        } catch (Exception ex) {
            System.out.println("Login failled");
            throw new NotFoundException();
        }
    }

    public Account getAccountById(int id,boolean isActive) {
        try {
            return repository.findByAccountIdAndIsActive(id,isActive).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException();
        }
    }

    public Account addAccount(Account account) {
        try {
            return repository.save(account);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }

    }

    public Account updateAccount(Account account) {
        try {
            return repository.save(account);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }
    }


    public List<Account> getAccounts(boolean isActive) {
        List<Account> accounts = repository.findByIsActive(isActive);
        if (accounts.isEmpty()) throw new NotFoundException();
        return accounts;
    }

    public void activeAccount(int id, boolean isActive) {
        try {
            Account account = repository.findById(id).get();
            account.setActive(isActive);
            repository.save(account);
        }
        catch (NoSuchElementException ex)
        {
            throw new NotFoundException("Can't active or non-active for not found object");
        }

    }


}
