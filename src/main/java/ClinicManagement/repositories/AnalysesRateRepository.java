package ClinicManagement.repositories;

import ClinicManagement.entity.AnalysesRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysesRateRepository extends JpaRepository<AnalysesRate,Integer> {
}
