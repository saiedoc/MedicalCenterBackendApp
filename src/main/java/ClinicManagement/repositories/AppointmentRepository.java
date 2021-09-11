package ClinicManagement.repositories;



import ClinicManagement.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {



    @Query("SELECT a FROM Appointment a JOIN a.doctor d " +
            "WHERE d.doctorId =:doctorId " +
            "AND a.isActive =:isAppointmentActive " +
            "AND a.status.name =:status "+
            "AND d.isActive =:isDoctorActive")
    List<Appointment> findByDoctorIdAndStatus(@Param("doctorId") int doctor_id,@Param("status")String status, @Param("isAppointmentActive") boolean isAppointmentActive ,@Param("isDoctorActive")boolean isDoctorActive);


    @Query("SELECT a FROM Appointment a JOIN a.patient p " +
            "WHERE  p.patientId =:patientId " +
            "AND a.isActive =:isAppointmentActive " +
            "AND a.status.name =:status "+
            "AND p.isActive =:isPatientActive")
    List<Appointment> findByPatientIdAndStatus(@Param("patientId") int patient_id,@Param("status") String status,@Param("isAppointmentActive") boolean isAppointmentActive ,@Param("isPatientActive") boolean isPatientActive);

    @Query("SELECT a FROM Appointment a JOIN a.status s " +
            "WHERE s.name =:statusName " +
            "AND a.isActive =:isAppointmentActive")
    List<Appointment> findByAppointmentAndStatusName(@Param("statusName") String statusName,@Param("isAppointmentActive")boolean isAppointmentActive);


    @Query("SELECT a FROM Appointment a " +
            "WHERE a.dateOfBooking =:dateOfBooking " +
            "AND a.status.name =:name " +
            "AND a.isActive =:isAppointmentActive")
    List<Appointment> findByDateAndStatusName(@Param("dateOfBooking")Date date,@Param("name")String name,@Param("isAppointmentActive") boolean isActive);



    @Query("SELECT a FROM Appointment a JOIN a.doctor d " +
            "WHERE a.dateOfBooking =:dateOfBooking " +
            "AND a.status.name =:status " +
            "AND a.isActive =:isAppointmentActive "+
            "AND d.doctorId =:doctorId "+
            "AND d.isActive =:isDoctorActive"
    )
    List<Appointment> findByDoctorIdAndDate(@Param("doctorId") int doctor_id,@Param("dateOfBooking")Date date,@Param("status")String status,@Param("isAppointmentActive") boolean isAppointmentActive,@Param("isDoctorActive") boolean isDoctorActive);

    @Query("SELECT a FROM Appointment a JOIN a.doctor d " +
            "WHERE a.dateOfBooking < :dateOfBooking " +
            "AND a.isActive =:isAppointmentActive "+
            "AND d.doctorId =:doctorId "+
            "AND d.isActive =:isDoctorActive"
    )
    List<Appointment> findByDoctorIdAndBeforeDate(@Param("doctorId") int doctor_id,@Param("dateOfBooking")Date date,@Param("isAppointmentActive") boolean isAppointmentActive,@Param("isDoctorActive") boolean isDoctorActive);

    @Query("SELECT a FROM Appointment a JOIN a.patient p " +
            "WHERE a.dateOfBooking < :dateOfBooking " +
            "AND a.isActive =:isAppointmentActive "+
            "AND p.patientId =:patientId "+
            "AND p.isActive =:isPatientActive"
    )
    List<Appointment> findByPatientIdAndBeforeDate(@Param("patientId") int patientId,@Param("dateOfBooking") Date date,@Param("isAppointmentActive") boolean isAppointmentActive,@Param("isPatientActive") boolean isPatientActive);


    List<Appointment> findByDateOfBookingAndTimeOfBookingAndIsActive(Date date,Time time, boolean isActive);
    List<Appointment> findByDateOfBookingAndIsActive(Date date, boolean isActive);
    List<Appointment> findByTimeOfBookingAndIsActive(Time time, boolean isActive);
    Optional<Appointment> findByAppointmentIdAndIsActive(int appointment_id, boolean isActive);
    List<Appointment>findByIsActive(boolean isActive);

   }
