package ClinicManagement.repositories;

import ClinicManagement.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status,Integer> {
    Optional<Status> findByStatusIdAndIsActive(int status_id, boolean isActive);

    List<Status> findByIsActive(boolean isActive);
    Optional<Status> findByNameAndIsActive(String name,boolean IsActive);
}
