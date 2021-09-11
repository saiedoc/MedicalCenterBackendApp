package ClinicManagement.service;


import ClinicManagement.entity.Medicine;
import ClinicManagement.entity.MedicineTime;
import ClinicManagement.entity.PatientMedicine;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.MedicineRepository;
import ClinicManagement.repositories.PatientMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class PatientMedicineService {

    @Autowired
    private PatientMedicineRepository repository;

    @Autowired
    private MedicineRepository medicineRepository;



    public PatientMedicine addPatientMedicine(PatientMedicine patientMedicine){
        try {
            return repository.save(patientMedicine);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }
    }
    public  PatientMedicine updatePatientMedicine(PatientMedicine patientMedicine)
    {
        try {
            PatientMedicine pm;
            try {
                pm = repository.findByPatientAndMedicine(patientMedicine.getPatient().getPatientId(), patientMedicine.getMedicine().getMedicineId(), false).get();
                pm.setActive(true);
            } catch (Exception ex) {
                pm = patientMedicine;
            }
            return repository.save(pm);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }
    }
    public  List<PatientMedicine> updatePatientMedicine(List<PatientMedicine> patientMedicine)
    {
        List<PatientMedicine> pMs = new ArrayList<>();
        for (PatientMedicine medTime : patientMedicine) {
            try {
                Medicine medicine = medicineRepository.findByMedicineIdAndIsActive(medTime.getMedicine().getMedicineId(),true).get();
                PatientMedicine pm = repository.save(medTime);
                pm.setMedicine(medicine);

                for (MedicineTime mt:pm.getMedicineTimes()) {
                    mt.setPatientMedicine(pm);
                }



                pMs.add(pm);

            } catch (Exception ex) {
                System.out.println("\n***\n");
                ex.printStackTrace();

            }
        }
        return pMs;

    }


    public void cancelationMedcineForPatient(int medicineID, int patientID) {
        try {
            PatientMedicine patientMedicine = repository.findByPatientAndMedicine(medicineID,patientID,true).get();
            patientMedicine.setActive(false);
            repository.save(patientMedicine);
        }
        catch (NoSuchElementException ex)
        {
            throw  new NotFoundException();
        }

    }

    public List<PatientMedicine> getPatientMedicine(int patientId,boolean patientIsActive,boolean medicineIsActive){
       List<PatientMedicine> patientMedicines= repository.findByPatientId(patientId,patientIsActive,medicineIsActive);
       if(patientMedicines.isEmpty()){
           throw new NotFoundException("Patient medicine not found");
       }
       return patientMedicines;
    }

    public PatientMedicine getByPatientAndMedicine(int patientID, int medicineID, boolean isActive) {
        try{
            return repository.findByPatientAndMedicine(medicineID, patientID, isActive).get();
        }catch ( Exception ex){
            throw new NotFoundException();
        }

    }
}
