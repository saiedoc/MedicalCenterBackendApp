package ClinicManagement.repositories;

import ClinicManagement.entity.SpecDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SpecDoctorRepository extends JpaRepository<SpecDoctor,Integer> {

    @Query("SELECT s FROM SpecDoctor s " +
            "WHERE s.doctor.doctorId =:doctorID " +
            "AND s.isActive =:isActive")
    List<SpecDoctor> findByDoctorIdAndIsActive(@Param("doctorID") int doctorID,@Param("isActive") boolean isActive);
}


