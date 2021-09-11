package ClinicManagement.repositories;

import ClinicManagement.entity.Doctor;
import ClinicManagement.entity.Sickness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SicknessRepository extends JpaRepository<Sickness,Integer> {

    @Query("SELECT s FROM Sickness s JOIN s.patientSicknesses ps JOIN ps.patient p " +
            "WHERE p.patientId =:id " +
            "AND ps.isActive =:isActive")
    List<Sickness> findSicknessesForPatient(@Param("id") int patientID,@Param("isActive") boolean isActive);

    Optional<Sickness> findBySicknessIdAndIsActive(int sickness_id, boolean isActive);
    List<Sickness>findByIsActive(boolean isActive);
}
