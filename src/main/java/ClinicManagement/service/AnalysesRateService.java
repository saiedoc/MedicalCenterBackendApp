package ClinicManagement.service;

import ClinicManagement.entity.AnalysesRate;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.AnalysesRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalysesRateService {

    @Autowired
    AnalysesRateRepository repository;

    public AnalysesRate addAnalysesRate(AnalysesRate analysesRate) {
        try{
            return repository.save(analysesRate);
        }catch (Exception ex){
            throw new ReplicatedException();
        }
    }

    public AnalysesRate updateAnalyses(AnalysesRate analysesRate) {
        try{
            return repository.save(analysesRate);
        }catch (Exception ex){
            throw new ReplicatedException();
        }
    }

}
