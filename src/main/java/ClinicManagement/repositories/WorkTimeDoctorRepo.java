package ClinicManagement.repositories;

import ClinicManagement.entity.Doctor;
import ClinicManagement.entity.WorkTimeDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkTimeDoctorRepo extends JpaRepository<WorkTimeDoctor,Integer> {

    Optional<WorkTimeDoctor> findByWtDoctorIdAndIsActive(int wt_id, boolean isActive);
    List<WorkTimeDoctor>findByIsActive(boolean isActive);

}
