package ClinicManagement.repositories;


import ClinicManagement.entity.Doctor;
import ClinicManagement.entity.Medicine;
import ClinicManagement.entity.Patient;
import autovalue.shaded.com.squareup.javapoet$.$CodeBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Integer> {


    @Query("SELECT m FROM Medicine m JOIN m.patientMedicines pm" +
            " WHERE pm.patient.patientId =:id" +
            " AND pm.patient.isActive =:isPatientActive " +
            "AND m.isActive =:isMedicineActive " +
            "AND pm.isActive =:isPatientMedicineActive")
    List<Medicine> findMedicinesForPatient(@Param("id") int patient_id,@Param("isMedicineActive") boolean isMedicineActive,@Param("isPatientActive") boolean isPatientActive,@Param("isPatientMedicineActive") boolean isPatientMedicineActive);

    Optional<Medicine> findByMedicineIdAndIsActive(int medicine_id, boolean isActive);
    List<Medicine>findByIsActive(boolean isActive);

}
