package ClinicManagement.repositories;

import ClinicManagement.entity.Doctor;
import ClinicManagement.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Integer>{

    @Query("SELECT t FROM Token t JOIN t.doctor d " +
            "WHERE d.doctorId =:doctorId " +
            "AND d.isActive =:isActive")
    Optional<Token> findTokenByIdAndDoctor(@Param("doctorId") int doctorId, @Param("isActive") boolean isActive);


    @Query("SELECT t FROM Token t JOIN t.patient p " +
            "WHERE p.patientId =:patientId " +
            "AND p.isActive =:isActive")
    Optional<Token> findTokenByIdAndPatient(@Param("patientId") int patientId,@Param("isActive") boolean isActive);


    Optional<Token> findByTokenIdAndIsActive(int token_id, boolean isActive);
    List<Token> findByIsActive(boolean isActive);
}
