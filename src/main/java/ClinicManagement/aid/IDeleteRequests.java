package ClinicManagement.aid;

import ClinicManagement.entity.*;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IDeleteRequests {

    ResponseEntity<Account> activeAccount(int id, boolean isActive);

    ResponseEntity<Doctor> activeDoctor(int id, boolean isActive);

    ResponseEntity<Specialization> activeSpecialization(int id, boolean isActive);

    ResponseEntity<Role> activeRole(int id, boolean isActive);

    ResponseEntity<Attachment> activeAttachment(int attachmentID, boolean isActive);

    ResponseEntity<Appointment> activePatient(int id, boolean isActive);

    ResponseEntity<Appointment> activeAppointment(int id, boolean isActive);

    ResponseEntity activeMedicineById(int id, boolean isActive);

    ResponseEntity activeSessionById(int id, boolean isActive);

    ResponseEntity activeSickness(int id, boolean isActive);

    ResponseEntity<Appointment> activeMedTime(int id, boolean isActive);

    ResponseEntity<WorkTimeDoctor> cancelationWorkTime(WorkTimeDoctor workTimeDoctor);

    ResponseEntity<JSONObject> cancelationMedicine(int medicineID, int patientID);

    ResponseEntity<Patient> cancelationSickness(int sicknessID, int patientID);

    ResponseEntity<Status> activeStatus(int status_id,boolean isActive);

    ResponseEntity activeWorkDayById(int id,boolean isActive);
}
