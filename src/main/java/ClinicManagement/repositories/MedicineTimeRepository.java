package ClinicManagement.repositories;

import ClinicManagement.entity.MedicineTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.*;

@Repository
public interface MedicineTimeRepository extends JpaRepository<MedicineTime,Integer> {

    List<MedicineTime> findByIsActive(boolean isActive);

    Optional<MedicineTime> findByMedicineTimeIdAndIsActive(int id,boolean isActive);


    @Query("SELECT mt FROM MedicineTime mt " +
            "WHERE mt.medTime =:medTime " +
            "AND mt.isActive =:isActive")
    List<MedicineTime> Do(@Param("medTime") Time time,@Param("isActive") boolean isActive);

    @Query("SELECT mt FROM MedicineTime mt JOIN mt.patientMedicine pm " +
            "WHERE pm.patient.patientId =:patientId " +
            "AND mt.isActive =:isActive")
    List<MedicineTime> findByPatientAndIsActive(@Param("patientId") int patientId,@Param("isActive") boolean isActive);
}
