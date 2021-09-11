package ClinicManagement.repositories;


import ClinicManagement.entity.Address;
import ClinicManagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("SELECT p FROM Patient p JOIN p.patientSicknesses pk JOIN pk.sickness s " +
            "WHERE s.name =:sickness_name " +
            "AND p.isActive =:isPatientActive")
    List<Patient> findBySickness(@Param("sickness_name") String sicknessName,@Param("isPatientActive") boolean isPatientActive);

    @Query("SELECT DISTINCT p FROM Patient p JOIN p.appointments a " +
            "WHERE a.doctor.doctorId =:id " +
            "AND a.doctor.isActive =:isDoctorActive " +
            "AND p.isActive =:isPatientActive")
    List<Patient> findByDoctorId(@Param("id") int id,@Param("isPatientActive") boolean isPatientAcitve,@Param("isDoctorActive") boolean isDoctorActive);

    @Query("SELECT p FROM Patient p JOIN p.patientMedicines pm" +
            " WHERE pm.medicine.name =:name " +
            "AND p.isActive =:isPatientActive " +
            "AND pm.isActive =:isMedicineActive")
    List<Patient> findByMedicine(@Param("name") String name,@Param("isPatientActive") boolean isPatientActive,@Param("isMedicineActive") boolean isMedicineActive);

    @Query("SELECT p FROM Patient p " +
            "WHERE p.account.accountId =:id " +
            "AND p.isActive =:isActive")
    Optional<Patient> findByAccountIdAndIsActive(@Param("id") int id,@Param("isActive") boolean isActive);
    List<Patient> findByAddressAndIsActive(Address address, boolean isActive);
    List<Patient> findByNameAndIsActive(String name,boolean isActive);
    List<Patient> findByDateOfBirthAndIsActive(Date dateOfBirth, boolean isActive);

    Optional<Patient> findByPatientIdAndIsActive(int patient_id, boolean isActive);
    List<Patient>findByIsActive(boolean isActive);

}
