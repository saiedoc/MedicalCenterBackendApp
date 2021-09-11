package ClinicManagement.repositories;


import ClinicManagement.entity.Account;
import ClinicManagement.entity.Appointment;
import ClinicManagement.entity.Doctor;
import ClinicManagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {


    @Query("SELECT d FROM Doctor d JOIN d.sessions s " +
            "WHERE s.patient.patientId =:id " +
            "AND d.isActive =:isDoctorActive " +
            "AND s.patient.isActive =:isPatientActive")
    List<Doctor> findDoctorByPatient(@Param("id") int patient_id, @Param("isDoctorActive") boolean isDoctorActive, @Param("isPatientActive") boolean isPatientActive);

    @Query("SELECT d FROM Doctor d JOIN d.specDoctor sd JOIN sd.specialization s" +
            " WHERE s.name =:spec_name " +
            "AND d.isActive =:isDoctorActive " +
            "AND s.isActive =:isSpecActive")
    List<Doctor> findDoctorBySpecialization(@Param("spec_name") String specName, @Param("isDoctorActive") boolean isDoctorActive, @Param("isDoctorActive") boolean isSpecActive);

    @Query("SELECT d FROM Doctor d JOIN d.workTimeDoctors wt" +
            " WHERE wt.workDays.name =:dayName" +
            " AND d.isActive =:isDoctorActive")
    List<Doctor> findByWorkDay(@Param("dayName") String workDay_name, @Param("isDoctorActive") boolean isDoctorActive);

    @Query("SELECT d FROM Doctor d " +
            "WHERE d.account.accountId =:id " +
            "AND d.isActive =:isActive")
    Optional<Doctor> findByAccountIdAndIsActive(@Param("id")int id,@Param("isActive") boolean isActive);
    Optional<Doctor> findByNameAndIsActive(String name, boolean isActive);

    Optional<Doctor> findByDoctorIdAndIsActive(int doctor_id, boolean isActive);

    List<Doctor> findByIsActive(boolean isActive);


}


