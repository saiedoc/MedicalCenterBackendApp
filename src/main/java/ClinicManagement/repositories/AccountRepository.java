package ClinicManagement.repositories;


import ClinicManagement.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {


    Account findByUsernameAndPasswordAndIsActive(String username,String password,boolean isActive);

    Optional<Account> findByAccountIdAndIsActive(int account_id,boolean isActive);
    List<Account>findByIsActive(boolean isActive);

}
