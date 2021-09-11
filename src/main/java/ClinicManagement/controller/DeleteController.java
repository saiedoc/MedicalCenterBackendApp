package ClinicManagement.controller;

import ClinicManagement.aid.IDeleteRequests;
import ClinicManagement.entity.*;
import ClinicManagement.factory.JsonFactory;
import ClinicManagement.service.*;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/delete")
public class DeleteController implements IDeleteRequests {
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
    private StatusService statusService;

    @Autowired
    private WorkDaysService workDaysService;

    @Autowired
    private PatientMedicineService patientMedicineService;

    @Autowired
    private PatientSicknessService patientSicknessService;

    private final Logger logger = LoggerFactory.getLogger(DeleteController.class);

    @Override
    @DeleteMapping("/doctor/id/{id}/isActive/{isActive}")
    public ResponseEntity<Doctor> activeDoctor(@PathVariable int id, @PathVariable boolean isActive) {
        doctorService.activeDoctor(id, isActive);
        logger.info("Delete Doctor with id : { " + id + " }");
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    @DeleteMapping("/specialization/{id}/isActive/{isActive}")
    public ResponseEntity<Specialization> activeSpecialization(@PathVariable int id, @PathVariable boolean isActive) {
        specializationService.activeSpecialization(id, isActive);
        logger.info("Delete Specialization with id : { " + id + " }");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/role/id/{id}/isActive/{isActive}")
    public ResponseEntity<Role> activeRole(@PathVariable int id, @PathVariable boolean isActive) {
        roleService.activeRole(id, isActive);
        logger.info("Delete Role with id : { " + id + " }");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/account/id/{id}/isActive/{isActive}")
    public ResponseEntity<Account> activeAccount(@PathVariable int id, @PathVariable boolean isActive) {
        accountService.activeAccount(id, isActive);
        logger.info("Delete Account with Id : { " + id + " }");
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    @DeleteMapping("/attachment/id/{id}/isActive/{isActive}")
    public ResponseEntity activeAttachment(@PathVariable int id, @PathVariable boolean isActive) {
        attachmentService.activeAttachment(id, isActive);
        logger.info("Delete Attachment with id : { " + id + " }");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/medicine/id/{id}/isActive/{isActive}")
    public ResponseEntity activeMedicineById(@PathVariable int id, @PathVariable boolean isActive) {
        medicineService.activeMedicine(id, isActive);
        logger.info("Delete Medicine with id : { " + id + " }");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/session/id/{id}/isActive/{isActive}")
    public ResponseEntity activeSessionById(@PathVariable int id, @PathVariable boolean isActive) {
        sessionService.activeSession(id, isActive);
        logger.info("Delete Session with id : { " + id + " }");
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/sickness/id/{id}/isActive/{isActive}")
    public ResponseEntity activeSickness(@PathVariable int id, @PathVariable boolean isActive) {
        sicknessService.activeSickness(id, isActive);
        logger.info("Delete Sickness with id : { " + id + " }");
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/appointment/id/{id}/isActive/{isActive}")
    public ResponseEntity<Appointment> activeAppointment(@PathVariable int id, @PathVariable boolean isActive) {
        appointmentService.activeAppointment(id, isActive);
        logger.info("Delete Appointment with id : { " + id + " }");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/medicineTime/id/{id}/isActive/{isActive}")
    public ResponseEntity<Appointment> activeMedTime(@PathVariable int id, @PathVariable boolean isActive) {
        medicineService.activeMedicine(id, isActive);
        logger.info("Delete MedTime with id : { " + id + " }");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/patient/id/{id}/isActive/{isActive}")
    public ResponseEntity activePatient(@PathVariable int id, @PathVariable boolean isActive) {
        patientService.activeDoctor(id, isActive);
        logger.info("Delete Patient with id : { " + id + " }");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/status/id/{id}/isActive/{isActive}")
    public ResponseEntity<Status> activeStatus(@PathVariable  int id, @PathVariable boolean isActive) {
        statusService.activeStatus(id, isActive);
        logger.info("Delete Patient with id : { " + id + " }");
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    @DeleteMapping("/cancel/medicine/medicineID/{medicineID}/patient/patientID/{patientID}")
    public ResponseEntity<JSONObject> cancelationMedicine(@PathVariable int medicineID, @PathVariable int patientID) {
        PatientMedicine pm = patientMedicineService.getByPatientAndMedicine(patientID, medicineID, true);
        patientMedicineService.cancelationMedcineForPatient(medicineID,patientID);
        return new ResponseEntity<>(JsonFactory.toGetPatientMedObject(pm), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/cancel/sickness/sicknessID/{sicknessID}/patient/patientID/{patientID}")
    public ResponseEntity<Patient> cancelationSickness(@PathVariable int sicknessID, @PathVariable int patientID) {
        patientSicknessService.cancelationSicknessForPatient(patientID,sicknessID );
        logger.info("cancelation Sickness with Id [ "+sicknessID+" ] and PatientID [ "+patientID+" ]");
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    @DeleteMapping("/cancel/workTime")
    public ResponseEntity<WorkTimeDoctor> cancelationWorkTime(@RequestBody WorkTimeDoctor workTimeDoctor) {
        logger.info("Cancel work Time Doctor with id : { " + workTimeDoctor.getWtDoctorId() + " }");
        workTimeDoctorService.cancelationWorkTimeForDoctor(workTimeDoctor.getWtDoctorId());
        return new ResponseEntity<>(HttpStatus.OK);

    }


    @Override
    @DeleteMapping("/workDay/id/{id}/isActive/{isActive}")
    public ResponseEntity activeWorkDayById(@PathVariable int id, @PathVariable boolean isActive) {
        workDaysService.activeWorkDay(id, isActive);
        logger.info("Delete Work Day with id : { " + id + " }");
        return new ResponseEntity(HttpStatus.OK);
    }


}
