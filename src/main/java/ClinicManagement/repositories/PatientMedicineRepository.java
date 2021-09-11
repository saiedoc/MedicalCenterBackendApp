package ClinicManagement.repositories;

import ClinicManagement.entity.PatientMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientMedicineRepository extends JpaRepository<PatientMedicine,Integer> {

    @Query("SELECT p FROM PatientMedicine p " +
            "WHERE p.patient.patientId =:patientID " +
            "AND p.medicine.medicineId =:medicineID " +
            "AND p.isActive =:isActive")
    Optional<PatientMedicine> findByPatientAndMedicine(@Param("medicineID") int medicineID,@Param("patientID")int patientID,@Param("isActive") boolean isActive);

    @Query("SELECT p FROM PatientMedicine p " +
            "WHERE p.patient.patientId =:patientID " +
            "AND p.medicine.isActive =:medicineIsActive " +
            "AND p.patient.isActive =:patientIsActive")
    List<PatientMedicine> findByPatientId(@Param("patientID")int patientId, @Param("patientIsActive")boolean patientIsActive,@Param("medicineIsActive") boolean medicineIsActive);
}
