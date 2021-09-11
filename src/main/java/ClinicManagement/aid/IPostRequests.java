package ClinicManagement.aid;

import ClinicManagement.entity.*;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface IPostRequests {

    ResponseEntity<JSONObject> addAccount(Account account);
    ResponseEntity<JSONObject> addDoctor(Doctor doctor);
    ResponseEntity<JSONObject> writeReport(Session session);
    ResponseEntity<JSONObject> addSickness(Sickness sickness);
    ResponseEntity<JSONObject> addSpecialization(Specialization specialization);
    ResponseEntity<JSONObject> addRole(Role role);
    ResponseEntity<JSONObject> addMedicine(Medicine medicine);
    ResponseEntity<JSONObject> addAttachment(Attachment attachment);
    ResponseEntity<JSONObject> addPatient(Patient patient);

    ResponseEntity<JSONObject> addToken(JSONObject js);

    ResponseEntity<JSONObject> addMedTime(MedicineTime medicineTime);
    ResponseEntity<JSONObject> login(Account account);
    ResponseEntity<JSONObject> makeAppointment(JSONObject js);
    ResponseEntity<JSONObject> addWorkTimeDoctor(WorkTimeDoctor workTimeDoctor);

    ResponseEntity<JSONObject> addWorkDay(WorkDays workDays);
}
