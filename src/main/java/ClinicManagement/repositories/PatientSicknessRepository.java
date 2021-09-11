package ClinicManagement.repositories;

import ClinicManagement.entity.PatientSickness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface PatientSicknessRepository extends JpaRepository<PatientSickness,Integer> {

    @Query("SELECT p FROM PatientSickness p " +
            "WHERE p.patient.patientId =:patientId " +
            "AND p.sickness.sicknessId =:sicknessId " +
            "AND p.isActive =:isActive")
    Optional<PatientSickness> findByPatientAndSickness(@Param("patientId") int patientId, @Param("sicknessId")  int sicknessId, @Param("isActive")  boolean isActive);
}
