package ClinicManagement.service;



import ClinicManagement.entity.Doctor;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.AppointmentRepository;
import ClinicManagement.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    private AppointmentRepository appointmentRepository;


    public Doctor addDoctor(Doctor doctor) {
        try {
            return repository.save(doctor);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }

    }

    public Doctor updateDoctor(Doctor doctor) {
        try {
            return repository.save(doctor);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }

    }

    public List<Doctor> getDoctors(boolean isActive) {
        List<Doctor> doctors = repository.findByIsActive(isActive);
        if (doctors.isEmpty()) throw new NotFoundException();
        return doctors;
    }

    public Doctor getDoctorById(int id,boolean isActive) {
        try {
            return repository.findByDoctorIdAndIsActive(id,isActive).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException();
        }
    }


    public Doctor getDoctorByName(String name,boolean isActive) {
        try {
            return repository.findByNameAndIsActive(name,isActive).get();
        }
        catch (NoSuchElementException ex)
        {
            throw new NotFoundException();
        }

    }

    public List<Doctor> getDoctorByPatient(int patientID,boolean isDoctorActive,boolean isPatientActive) {
        List<Doctor> doctors = repository.findDoctorByPatient(patientID,isDoctorActive,isPatientActive);
        if (doctors.isEmpty()) throw new NotFoundException();
        return doctors;
    }

    public List<Doctor> getDoctorBySpecialization(String specName,boolean isDoctorActive,boolean isSpecActive) {
        List<Doctor> doctors =  repository.findDoctorBySpecialization(specName,isDoctorActive,isSpecActive);
        if (doctors.isEmpty()) throw new NotFoundException();
        return doctors;
    }

    public List<Doctor> getDoctorByDay(String workDay_name,boolean isDoctorActive) {
        List<Doctor> doctors =  repository.findByWorkDay(workDay_name,isDoctorActive);
        if (doctors.isEmpty()) throw new NotFoundException();
        return doctors;
    }



    public void activeDoctor(int id, boolean isActive){

        try {
            Doctor doctor = repository.findById(id).get();
            doctor.setActive(isActive);
            repository.save(doctor);
        }catch (NoSuchElementException ex){
            throw  new NotFoundException("Can't active or non-active for not found object");
        }

    }

    public Doctor getDoctorByAccount(int id, boolean isActive) {
        try {
            return repository.findByAccountIdAndIsActive(id,isActive).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException();
        }
    }
}
