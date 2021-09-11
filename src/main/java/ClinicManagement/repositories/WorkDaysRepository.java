package ClinicManagement.repositories;

import ClinicManagement.entity.Role;
import ClinicManagement.entity.WorkDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface WorkDaysRepository extends JpaRepository<WorkDays,Integer> {

   Optional<WorkDays> findByWorkDayIdAndIsActive(int id, boolean isActive);

    List<WorkDays> findByIsActive(boolean isActive);
}

