package ClinicManagement.repositories;

import ClinicManagement.entity.Doctor;
import ClinicManagement.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization,Integer> {

    Optional<Specialization> findBySpecIdAndIsActive(int spec_id, boolean isActive);
    List<Specialization>findByIsActive(boolean isActive);
}
