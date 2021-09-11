package ClinicManagement.controller;

import ClinicManagement.aid.IPutRequests;
import ClinicManagement.entity.*;
import ClinicManagement.factory.JsonFactory;
import ClinicManagement.notification.PushNotificationRequest;
import ClinicManagement.notification.PushNotificationService;
import ClinicManagement.service.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/update")
public class UpDateController implements IPutRequests {

    @Autowired
    private AccountService accountService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private WorkTimeDoctorService workTimeDoctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private SicknessService sicknessService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private MedicineTimeService medicineTimeService;

    @Autowired
    private PatientMedicineService patientMedicineService;

    @Autowired
    private PatientSicknessService patientSicknessService;

    @Autowired
    private WorkDaysService workDaysService;

    @Autowired
    private SpecDoctorService specDoctorService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private PushNotificationService pushNotificationService;

    @Autowired
    private TokenService tokenService;


    private final Logger logger = LoggerFactory.getLogger(UpDateController.class);

    @Override
    @PutMapping("/account")
    public ResponseEntity<JSONObject> updateAccount(@RequestBody Account account) {

        Account a = accountService.updateAccount(account);
        logger.info("UpDating Account ..");
        return new ResponseEntity<>(JsonFactory.toGetAccountObject(a), HttpStatus.OK);
    }


    @Override
    @PutMapping("/doctor")
    public ResponseEntity<JSONObject> updateDoctor(@RequestBody Doctor doctor) {

        Role role = roleService.getRoleByName("DOCTOR", true);
        doctor.getAccount().setRole(role);


        if (doctor.getSpecDoctor() != null) {
            for (SpecDoctor s : doctor.getSpecDoctor()) {
                s.setDoctor(doctor);
                specDoctorService.updateSpecDoctor(s);
            }
        }

        if (doctor.getWorkTimeDoctors() != null) {
            for (WorkTimeDoctor wt : doctor.getWorkTimeDoctors()) {
                wt.setDoctor(doctor);
                workTimeDoctorService.updateWorkTimeDoctor(wt);
            }
        }

        Doctor d = doctorService.updateDoctor(doctor);

        logger.info("UpDating doctor ..");
        return new ResponseEntity<>(JsonFactory.toGetDoctorObject(d), HttpStatus.OK);
    }

    @Override
    @PutMapping("/patient")
    public ResponseEntity<JSONObject> updatePatient(@RequestBody Patient patient) {
        Role role = roleService.getRoleByName("PATIENT", true);
        patient.getAccount().setRole(role);


        if (patient.getPatientMedicines() != null) {
            for (PatientMedicine m : patient.getPatientMedicines()) {
                m.setPatient(patient);
                patientMedicineService.updatePatientMedicine(m);
            }
        }


        if (patient.getPatientSicknesses() != null) {
            for (PatientSickness s : patient.getPatientSicknesses()) {
                s.setPatient(patient);
                patientSicknessService.updatePatientSickness(s);
            }
        }

        Patient p = patientService.updatePatient(patient);

        logger.info("UpDating Patient ..");
        return new ResponseEntity<>(JsonFactory.toGetPatientObject(p), HttpStatus.OK);

    }


    @Override
    @PutMapping("/role")
    public ResponseEntity<JSONObject> updateRole(@RequestBody Role role) {

        Role r = roleService.updateRole(role);
        logger.info("UpDating Role ..");
        return new ResponseEntity<>(JsonFactory.toGetRoleObject(r), HttpStatus.OK);
    }

    @Override
    @PutMapping("/speci")
    public ResponseEntity<JSONObject> updateSpecialization(Specialization specialization) {

        Specialization spec = specializationService.updateSpecialization(specialization);
        logger.info("UpDating Specialization ..");
        return new ResponseEntity<>(JsonFactory.toGetSpecializationObject(spec), HttpStatus.OK);
    }

    @Override
    @PutMapping("/appointment")
    public ResponseEntity<JSONObject> updateAppointment(@RequestBody Appointment appointment) {
        Appointment ap = appointmentService.updateAppointment(appointment);
        logger.info("UpDating Appointment ..");
        return new ResponseEntity<>(JsonFactory.toGetAppointmentObject(ap), HttpStatus.OK);
    }

    @Override
    @PutMapping("/appointment/status/accept/id/{id}")
    public ResponseEntity<JSONObject> updateAppointmentAcceptStatus(@PathVariable int id) {

        Status status = statusService.getStatusByName("ACCEPTED", true);
        Appointment appointment = appointmentService.getAppointmentById(id, true);
        appointment.setStatus(status);
        Appointment ap = appointmentService.updateAppointment(appointment);
        logger.info("UpDating Appointment Accept Status( " + id + " )");

        String messageBody = "Your appointment is accept";
        pushNotificationService.sendPushNotificationToToken(createNotification(appointment, messageBody));
        return new ResponseEntity<>(JsonFactory.toGetAppointmentObject(ap), HttpStatus.OK);
    }

    @Override
    @PutMapping("/appointment/status/refuse/id/{id}")
    public ResponseEntity<JSONObject> updateAppointmentRefuseStatus(@PathVariable int id) {
        Status status = statusService.getStatusByName("REFUSED", true);
        Appointment appointment = appointmentService.getAppointmentById(id, true);
        appointment.setStatus(status);
        Appointment ap = appointmentService.updateAppointment(appointment);
        logger.info("UpDating Appointment Refuse Status( " + id + " )");

        String messageBody = "Your appointment is Refuse";
        pushNotificationService.sendPushNotificationToToken(createNotification(appointment, messageBody));
        return new ResponseEntity<>(JsonFactory.toGetAppointmentObject(ap), HttpStatus.OK);
    }


    @Override
    @PutMapping("/medicine")
    public ResponseEntity<JSONObject> updateMedicine(@RequestBody Medicine medicine) {
        Medicine m = medicineService.updateMedicine(medicine);
        logger.info("UpDating Medicine ..");
        return new ResponseEntity<>(JsonFactory.toGetMedicineObject(m), HttpStatus.OK);
    }

    @Override
    @PutMapping("/sickness")
    public ResponseEntity<JSONObject> updateSickness(@RequestBody Sickness sickness) {
        Sickness s = sicknessService.updateSickness(sickness);
        logger.info("UpDating Sickness ..");
        return new ResponseEntity<>(JsonFactory.toGetSicknessObject(s), HttpStatus.OK);
    }

    @Override
    @PutMapping("/attachment")
    public ResponseEntity<JSONObject> updateAttachment(@RequestBody Attachment attachment) {
        Attachment at = attachmentService.updateAttachment(attachment);
        logger.info("UpDating Attachment ..");
        return new ResponseEntity<>(JsonFactory.toGetAttachmentObject(at), HttpStatus.OK);
    }


    @Override
    @PutMapping("/wTdoctor")
    public ResponseEntity<JSONObject> updateWorkTimeForDoctor(@RequestBody WorkTimeDoctor workTimeDoctor) {

        WorkTimeDoctor wt = workTimeDoctorService.updateWorkTimeDoctor(workTimeDoctor);
        logger.info("UpDating Work Time Doctor ..");
        return new ResponseEntity<>(JsonFactory.toGetWorkTimeDoctorObject(wt), HttpStatus.OK);
    }

    @Override
    @PutMapping("/medicineTime")
    public ResponseEntity<JSONArray> updateMedTime(@RequestBody List<PatientMedicine> patientMedicines) {
        logger.info("UpDating Medicine Time ..");

        List<PatientMedicine> pMs = patientMedicineService.updatePatientMedicine(patientMedicines);
        return new ResponseEntity(JsonFactory.toGetPatientMedicines(pMs), HttpStatus.OK);
    }

    @Override
    @PutMapping("/linkMedicineWithPatient")
    public ResponseEntity<JSONObject> linkMedicineWithPatient(@RequestBody PatientMedicine patientMedicine) {

        logger.info("Link medicine with patient ....");
        return new ResponseEntity<>(JsonFactory.toGetPatientMedObject(
                patientMedicineService.updatePatientMedicine(patientMedicine)), HttpStatus.OK);

    }


    @Override
    @PutMapping("/linkPatientWithSickness")
    public ResponseEntity<JSONObject> linkPatientWithSickness(@RequestBody PatientSickness patientSicknesses) {
        logger.info("UpDating Patient Sickness ..");

        return new ResponseEntity<>(JsonFactory.toGetPatientSicknessObject(patientSicknessService.updatePatientSickness(patientSicknesses)), HttpStatus.OK);
    }

    @Override
    @PutMapping("/workDay")
    public ResponseEntity<JSONObject> updateWorkDay(@RequestBody WorkDays workDays) {
        WorkDays workD = workDaysService.updateWorkDay(workDays);
        logger.info("UpDating  Work Day..");
        return new ResponseEntity<>(JsonFactory.toGetWorkDaysObject(workD), HttpStatus.OK);
    }

    @Override
    @PutMapping("/token")
    public ResponseEntity<JSONObject> updateToken(@RequestBody JSONObject js)
    {
        int id = (int) js.get("profile_id");
        Token token = null;
        if(js.get("role") == "DOCTOR") {
            token = tokenService.getTokenByDoctorId(id,true);
        }
        if(js.get("role") == "PATIENT" ){
            token = tokenService.getTokenByPatientId(id,true);
        }


        try {
            if(token == null) throw new NullPointerException();
        }catch (NullPointerException np){
            logger.error("Can't be Updated ..");
            np.printStackTrace();
        }

        tokenService.updateToken(token);
        logger.info("UpDating  Token ..");
        return  new ResponseEntity<>(JsonFactory.toGetTokenObject(token),HttpStatus.OK);
    }

    private PushNotificationRequest createNotification(Appointment appointment,String message)
    {
        String token = patientService.getPatientById(appointment.getPatient().getPatientId(),true).getToken().getTokenBody();
        return  new PushNotificationRequest("Appointment Reply",message,null,token);
    }

}
