package ClinicManagement.repositories;

import ClinicManagement.entity.Doctor;
import ClinicManagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRoleIdAndIsActive(int role_id, boolean isActive);
    List<Role> findByIsActive(boolean isActive);
    Optional<Role> findByNameAndIsActive(String name,boolean isActive);
}
