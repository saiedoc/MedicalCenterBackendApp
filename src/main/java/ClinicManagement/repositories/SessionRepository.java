package ClinicManagement.repositories;


import ClinicManagement.entity.Attachment;
import ClinicManagement.entity.Doctor;
import ClinicManagement.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Integer> {

    @Query("SELECT s FROM Session s " +
            "WHERE s.patient.patientId =:patient_id " +
            "AND s.patient.isActive =:isPatientActive ")
    List<Session> findSessionByPatientId(@Param("patient_id") int patient_id,@Param("isPatientActive") boolean isPatientAcitve);

    @Query("SELECT s FROM Session s " +
            "WHERE s.patient.patientId =:patient_id " +
            "AND s.doctor.doctorId =:doctor_id " +
            "AND s.patient.isActive =:isPatientActive " +
            "AND s.doctor.isActive =:isDoctorActive")
    List<Session> findSessionForDoctorAndPatientId(@Param("patient_id") int patientID, @Param("doctor_id") int doctor_id
                                        , @Param("isPatientActive") boolean isPatientActive, @Param("isDoctorActive") boolean isDoctorActive);



    Optional<Session> findBySessionIdAndIsActive(int session_id, boolean isActive);
    List<Session>findByIsActive(boolean isActive);
}

