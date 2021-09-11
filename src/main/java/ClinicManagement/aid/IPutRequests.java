package ClinicManagement.aid;

import ClinicManagement.entity.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

public interface IPutRequests {

    ResponseEntity<JSONObject> updateDoctor(Doctor doctor);

    ResponseEntity<JSONObject> updateSpecialization(Specialization specialization);

    ResponseEntity<JSONObject> updateRole(Role role);

    ResponseEntity<JSONObject> updateAppointment(Appointment appointment);

    ResponseEntity<JSONObject> updateAccount(Account account);



    ResponseEntity<JSONObject> updateAppointmentAcceptStatus(int id);

    ResponseEntity<JSONObject> updateAppointmentRefuseStatus(int id);

    ResponseEntity<JSONObject> updatePatient(Patient patient);



    ResponseEntity<JSONObject> updateAttachment(Attachment attachment);

    ResponseEntity<JSONArray> updateMedTime(List<PatientMedicine> patientMedicines);

    ResponseEntity<JSONObject> linkMedicineWithPatient(PatientMedicine patientMedicine);


    ResponseEntity<JSONObject> updateWorkTimeForDoctor(WorkTimeDoctor workTimeDoctor);

    ResponseEntity<JSONObject> updateMedicine(Medicine medicine);
    ResponseEntity<JSONObject> updateSickness(Sickness sickness);


    ResponseEntity<JSONObject> linkPatientWithSickness( PatientSickness patientSicknesses);

    ResponseEntity<JSONObject> updateWorkDay(WorkDays workDays);


    ResponseEntity<JSONObject> updateToken( JSONObject js);
}
