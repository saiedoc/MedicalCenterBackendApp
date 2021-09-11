package ClinicManagement.controller;

import ClinicManagement.aid.IGetRequests;
import ClinicManagement.entity.Address;
import ClinicManagement.entity.Appointment;
import ClinicManagement.entity.Attachment;
import ClinicManagement.factory.JsonFactory;
import ClinicManagement.service.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("api/get")
public class GetController implements IGetRequests {

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
    private AnalysesRateService analysesRateService;

    @Autowired
    private  PatientMedicineService patientMedicineService;

    private final Logger logger = LoggerFactory.getLogger(GetController.class);


    /****   Get Requests for all info about entities ***/


    @Override
    @GetMapping("/specializations")
    public ResponseEntity<List<JSONArray>> getSpecializations() {
        logger.info("getting Specializations ..");
        return new ResponseEntity<>(JsonFactory.toGetSpecializations(specializationService.getSpecializations(true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/doctors")
    public ResponseEntity<JSONArray> getDoctors() {
        logger.info("getting Doctors ..");
        return new ResponseEntity<>(JsonFactory.toGetDoctors(doctorService.getDoctors(true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/roles")
    public ResponseEntity<JSONArray> getRoles() {
        logger.info("getting Roles ..");
        return new ResponseEntity<>(JsonFactory.toGetRoles(roleService.getRoles(true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/accounts")
    public ResponseEntity<JSONArray> getAccounts() {
        logger.info("getting Accounts ..");
        return new ResponseEntity<>(JsonFactory.toGetAccounts(accountService.getAccounts(true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/medicines")
    public ResponseEntity<JSONArray> getMedicines() {
        logger.info("getting Medicines ..");
        return new ResponseEntity<>(JsonFactory.toGetMedicines(medicineService.getMedicines(true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/patients")
    public ResponseEntity<JSONArray> getPatients() {
        logger.info("getting Patients ..");
        return new ResponseEntity<>(JsonFactory.toGetPatients(patientService.getPatients(true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/sicknesses")
    public ResponseEntity<JSONArray> getSicknesses() {
        logger.info("getting Sicknesses ..");
        return new ResponseEntity<>(JsonFactory.toGetSicknesses(sicknessService.getSicknesses(true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/appointments")
    public ResponseEntity<JSONArray> getAppointments() {
        logger.info("getting Appointments ..");
        return new ResponseEntity<>(JsonFactory.toGetAppointments(appointmentService.getAppointments(true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/statuses")
    public ResponseEntity<JSONArray> getStatuses() {
        logger.info("getting Statuses ..");
        return new ResponseEntity<>(JsonFactory.toGetStatuses(statusService.getStatuses(true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/workDays")
    public ResponseEntity<JSONArray> getWorkDays() {
        logger.info("getting workDays ..");
        return new ResponseEntity<>(JsonFactory.toGetWorkDays(workDaysService.getWorkDays(true)), HttpStatus.OK);
    }


    /**
     * Get Requests for Doctor entity using By attribute
     **/

    @Override
    @GetMapping("/doctor/id/{id}")
    public ResponseEntity<JSONObject> getDoctorById(@PathVariable int id) {
        logger.info("getting Doctor with id : { " + id + " }");
        return new ResponseEntity<>(JsonFactory.toGetDoctorObject(doctorService.getDoctorById(id, true)), HttpStatus.OK);
    }


    @Override
    @GetMapping("/doctors/name/{name}")
    public ResponseEntity<JSONObject> getDoctorByName(@PathVariable String name) {
        logger.info("getting Doctor with name : { " + name + " }");
        return new ResponseEntity<>(JsonFactory.toGetDoctorObject(doctorService.getDoctorByName(name, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/doctor/account/id/{id}")
    public ResponseEntity<JSONObject> getDoctorByAccountId(@PathVariable int id) {
        logger.info("getting Doctor with Account(id) : { " + id + " }");
        return new ResponseEntity<>(JsonFactory.toGetDoctorObject(doctorService.getDoctorByAccount(id, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/doctors/speci/{name}")
    public ResponseEntity<JSONArray> getDoctorsBySpecName(@PathVariable String name) {
        logger.info("getting Doctor with Specialization : { " + name + " }");
        return new ResponseEntity<>(JsonFactory.toGetDoctors(doctorService.getDoctorBySpecialization(name, true, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/doctors/workday/{name}")
    public ResponseEntity<JSONArray> getDoctorsByWorkDayName(@PathVariable String name) {
        logger.info("getting Doctor with WorkDay : { " + name + " }");
        return new ResponseEntity<>(JsonFactory.toGetDoctors(doctorService.getDoctorByDay(name, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/doctors/patient/{patientID}")
    public ResponseEntity<JSONArray> getPatientsForDoctor(@PathVariable int patientID) {
        logger.info("getting Doctor For Patient(id) : { " + patientID + " }");
        return new ResponseEntity<>(JsonFactory.toGetDoctors(doctorService.getDoctorByPatient(patientID, true, true)), HttpStatus.OK);
    }


    /**
     * Get Requests for Patient entity using By attribute
     **/


    @Override
    @GetMapping("/patient/id/{id}")
    public ResponseEntity<JSONObject> getPatientByID(@PathVariable int id) {
        logger.info("getting Patient with id : { " + id + " }");
        return new ResponseEntity<>(JsonFactory.toGetPatientObject(patientService.getPatientById(id, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/patients/name/{name}")
    public ResponseEntity<JSONArray> getPatientsByName(@PathVariable String name) {
        logger.info("getting Patient with name : { " + name + " }");
        return new ResponseEntity<>(JsonFactory.toGetPatients(patientService.getPatientsByName(name, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/patient/account/id/{id}")
    public ResponseEntity<JSONObject> getPatientByAccountId(@PathVariable int id) {
        logger.info("getting Patient with Account(id) : { " + id + " }");
        return new ResponseEntity<>(JsonFactory.toGetPatientObject(patientService.getPatientByAccount(id, true)), HttpStatus.OK);
    }


    @Override
    @GetMapping("/patients/sickness/{name}")
    public ResponseEntity<JSONArray> getPatientsBySicknessName(@PathVariable String name) {
        logger.info("getting Patient with Sickness : { " + name + " }");
        return new ResponseEntity<>(JsonFactory.toGetPatients(patientService.getPatientBySickness(name, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/patients/medicine/{name}")
    public ResponseEntity<JSONArray> getPatientsByMedicine(@PathVariable String name) {
        logger.info("getting Patient By Medicine(name) : { " + name + " }");
        return new ResponseEntity<>(JsonFactory.toGetPatients(patientService.getPatientByMedicine(name, true, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/patients/address/{address}")
    public ResponseEntity<JSONArray> getPatientsByAddress(@PathVariable Address address) {
        logger.info("getting Patient By Address : { " + address.toString() + " }");
        return new ResponseEntity<>(JsonFactory.toGetPatients(patientService.getPatientBytAddress(address, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/patients/dateOfBirth/{date}")
    public ResponseEntity<JSONArray> getPatientByDate(@PathVariable Date date) {
        logger.info("getting Patient By DateOfBirth : { " + date + " }");
        return new ResponseEntity<>(JsonFactory.toGetPatients(patientService.getPatientsByDate(date, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/patients/doctor/{id}")
    public ResponseEntity<JSONArray> getPatientsByDoctorId(@PathVariable int id) {
        logger.info("getting Patient By Doctor(id) : { " + id + " }");
        return new ResponseEntity<>(JsonFactory.toGetPatients(patientService.getPatientByDoctorId(id, true, true)), HttpStatus.OK);
    }


    /***  Get Requests for Appointments entity using By attribute   **/


    @Override
    @GetMapping("/appointment/id/{id}")
    public ResponseEntity<JSONObject> getAppointmentById(@PathVariable int id) {
        logger.info("getting Appointment with id : { " + id + " }");
        return new ResponseEntity<>(JsonFactory.toGetAppointmentObject(appointmentService.getAppointmentById(id, true)), HttpStatus.OK);
    }



    @Override
    @GetMapping("/appointments/doctor/{doctorID}/status/pending")
    public ResponseEntity<JSONArray> getAppointmentsByDoctorIdAndPendingStatusInDate(@PathVariable int doctorID) {
        logger.info("getting Appointment By Doctor(id) : { " + doctorID + " }");
        return new ResponseEntity<>(JsonFactory.toGetAppointments(appointmentService.getAppointmentsByDoctorIdAndStatus(doctorID, "PENDING", true, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/appointments/doctor/{doctorID}/status/accept")
    public ResponseEntity<JSONArray> getAppointmentsByDoctorIdAndAcceptStatusInDate(@PathVariable int doctorID) {
        logger.info("getting Appointment By Doctor(id) : { " + doctorID + " }");
        return new ResponseEntity<>(JsonFactory.toGetAppointments(appointmentService.getAppointmentsByDoctorIdAndStatus(doctorID, "ACCEPTED", true, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/appointments/doctor/{doctorID}/status/refuse")
    public ResponseEntity<JSONArray> getAppointmentsByDoctorIdAndRefuseStatusInDate(@PathVariable int doctorID) {
        logger.info("getting Appointment By Doctor(id) : { " + doctorID + " }");
        return new ResponseEntity<>(JsonFactory.toGetAppointments(appointmentService.getAppointmentsByDoctorIdAndStatus(doctorID, "REFUSED", true, true)), HttpStatus.OK);
    }



    @Override
    @GetMapping("/appointments/date/time")
    public ResponseEntity<JSONArray> getAppointmentByDate(
            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date) {

        Date sqlDate = Date.valueOf(date.toLocalDate());
        Time sqlTime = Time.valueOf(date.toLocalTime());
        logger.info("getting Appointment By Date AND Time : { " + sqlDate + " , " + sqlTime + " }");
        return new ResponseEntity<>(JsonFactory.toGetAppointments(appointmentService.getAppointmentByDateAndTime(sqlDate, sqlTime, true)), HttpStatus.OK);
    }



    @Override
    @GetMapping("/appointments/status/pending/patient/date")
    public ResponseEntity<JSONArray> getAppointmentsByPatientIdAndPendingState(
            @RequestParam("id") int patientId,
            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date date)  {

        Date sqlDate = new Date(date.getTime());


        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByPatientIdAndBeforeDateAndStatus(patientId, sqlDate, true, true);
            appointmentService.cancelationOfPreviosAppointment(appointments);
        } catch (Exception ex) {
        }

        logger.info("getting Appointment By Patient(id) : { " + patientId + " }");
        return new ResponseEntity<>(JsonFactory.toGetAppointments(appointmentService.getAppointmentsByPatientIdAndStatus(patientId, "PENDING", true, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/appointments/status/accept/patient/date")
    public ResponseEntity<JSONArray> getAppointmentsByPatientIdAndAcceptState(
            @RequestParam("id") int patientId,
            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date date)  {

        Date sqlDate = new Date(date.getTime());


        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByPatientIdAndBeforeDateAndStatus(patientId, sqlDate, true, true);
            appointmentService.cancelationOfPreviosAppointment(appointments);
        } catch (Exception ex) {
        }
        logger.info("getting Appointment By Patient(id) : { " + patientId + " }");
        return new ResponseEntity<>(JsonFactory.toGetAppointments(appointmentService.getAppointmentsByPatientIdAndStatus(patientId, "ACCEPTED", true, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/appointments/status/refuse/patient/date")
    public ResponseEntity<JSONArray> getAppointmentsByPatientIdAndRefuseState(
            @RequestParam("id") int patientId,
            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date date)  {

        Date sqlDate = new Date(date.getTime());

        logger.info("getting Appointment By Patient(id) : { " + patientId + " }");
        return new ResponseEntity<>(JsonFactory.toGetAppointments(appointmentService.getAppointmentsByPatientIdAndStatus(patientId, "REFUSED", true, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/appointments/status/pending/doctor/date")
    public ResponseEntity<JSONArray> getAppointmentsForDoctorAndByPendingStatusAndDate(
            @RequestParam("id") int doctorId,
            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date date) {

        Date sqlDate = new Date(date.getTime());


        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByDoctorIdAndBeforeDateAndStatus(doctorId, sqlDate, true, true);
            appointmentService.cancelationOfPreviosAppointment(appointments);
        } catch (Exception ex) {
        }
        String statusName = "PENDING";
        logger.info("getting Appointment By Status(name) : { " + statusName + " }");
        return new ResponseEntity<>(JsonFactory.toGetAppointments(appointmentService.getAppointmentsByDoctorIdAndStatus(doctorId, statusName, true, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/appointments/status/accept/doctor/date")
    public ResponseEntity<JSONArray> getAppointmentsForDoctorAndByAcceptStatusAndDate(
            @RequestParam("id") int doctorId,
            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date date) {
        /*http://HOST_NAME:PORT/api/get/appointments/status/accept/doctor/date/?date=yyyy-MM-dd&id=0*/
        Date sqlDate = new Date(date.getTime());

        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByDoctorIdAndBeforeDateAndStatus(doctorId, sqlDate, true, true);
            appointmentService.cancelationOfPreviosAppointment(appointments);
        } catch (Exception ex) {
        }

        String statusName = "ACCEPTED";

        logger.info("getting Appointment By Status(name) : { " + statusName + " }");
        return new ResponseEntity<>(JsonFactory.toGetAppointments(appointmentService.getAppointmentsByDoctorIdAndStatus(doctorId, statusName, true, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/appointments/status/refuse/doctor/date")
    public ResponseEntity<JSONArray> getAppointmentsForDoctorAndByRefusedStatusAndDate(
            @RequestParam("id") int doctorId,
            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date date) {

        String statusName = "REFUSED";

        logger.info("getting Appointment By Status(name) : { " + statusName + " }");
        return new ResponseEntity<>(JsonFactory.toGetAppointments(appointmentService.getAppointmentsByDoctorIdAndStatus(doctorId, statusName, true, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/appointments/doctor/date/status/pending")
    public ResponseEntity<JSONArray> getAppointmentsByDoctorIdAndPendingStatusInDate(
            @RequestParam("id") int doctorId,
            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date date) {

        Date sqlDate = new Date(date.getTime());
        String name = "PENDING";
        logger.info("getting Appointment By DoctorId( " + doctorId + " ) And Date( " + sqlDate + " ) with Status( " + name + " ) ");
        return new ResponseEntity<>(
                JsonFactory.toGetAppointments(
                        appointmentService.getAppointmentsByDoctorIdAndDateAndStatus(
                                doctorId, sqlDate, name, true, true))
                , HttpStatus.OK);
    }

    @Override
    @GetMapping("/appointments/doctor/date/status/accept")
    public ResponseEntity<JSONArray> getAppointmentsByDoctorIdAndAcceptStatusInDate(
            @RequestParam("id") int doctorId,
            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date date) {


        Date sqlDate = new Date(date.getTime());
        String name = "ACCEPTED";
        logger.info("getting Appointment By DoctorId( " + doctorId + " ) And Date( " + sqlDate + " ) with Status( " + name + " ) ");
        return new ResponseEntity<>(JsonFactory.toGetAppointments(appointmentService.getAppointmentsByDoctorIdAndDateAndStatus(doctorId, sqlDate, name, true, true)), HttpStatus.OK);
    }


    @Override
    @GetMapping("/appointments/doctor/date/status/refuse")
    public ResponseEntity<JSONArray> getAppointmentsByDoctorIdAndRefuseStatusInDate(
            @RequestParam("id") int doctorId,
            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date date) {
        Date sqlDate = new Date(date.getTime());
        String name = "REFUSED";
        logger.info("getting Appointment By DoctorId( " + doctorId + " ) And Date( " + sqlDate + " ) with Status( " + name + " ) ");
        return new ResponseEntity<>(JsonFactory.toGetAppointments(appointmentService.getAppointmentsByDoctorIdAndDateAndStatus(doctorId, sqlDate, name, true, true)), HttpStatus.OK);
    }

    /**
     * Other Get Requests By Id
     **/


    @Override
    @GetMapping("/role/id/{id}")
    public ResponseEntity<JSONObject> getRoleById(@PathVariable int id) {
        logger.info("getting Role with id : { " + id + " }");
        return new ResponseEntity<>(JsonFactory.toGetRoleObject(roleService.getRoleById(id, true)), HttpStatus.OK);
    }


    @Override
    @GetMapping("/account/id/{id}")
    public ResponseEntity<JSONObject> getAccountById(@PathVariable int id) {
        logger.info("getting Account with id : { " + id + " }");
        return new ResponseEntity<>(JsonFactory.toGetAccountObject(accountService.getAccountById(id, true)), HttpStatus.OK);
    }


    @Override
    @GetMapping("/specialization/id/{id}")
    public ResponseEntity<JSONObject> getSpecializationById(@PathVariable int id) {
        logger.info("getting Specialization with id : { " + id + " }");
        return new ResponseEntity<>(JsonFactory.toGetSpecializationObject(specializationService.getSpecializationById(id, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/attachment/info/id/{id}")
    public ResponseEntity<JSONObject> getAttachmentInfoById(@PathVariable int id) {
        logger.info("getting Attachment info with id : { " + id + " }");
        return new ResponseEntity<>(JsonFactory.toGetAttachmentObject(attachmentService.getAttachmentById(id, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/attachment/download/id/{id}")
    public byte[] downloadAttachment(@PathVariable int id) {
        logger.info("Download Attachment with id : { " + id + " }");
        byte[] file = attachmentService.getAttachmentById(id, true).getBytesFile();
        logger.info("Sending byte array with length : {  " + file + "  } , {  " + file.length + "  }");
        return file;
    }

    @Override
    @GetMapping("/attachment/url/id/{id}/attachment.pdf")
    public ResponseEntity<Resource> getAttachmentURL(@PathVariable int id) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control","no-cache, no-store, must-revalidate");
        headers.add("Pragma","no-cache");
        headers.add("Expires","0");

        Attachment attachment = attachmentService.getAttachmentById(6,true);
        byte [] a = attachment.getBytesFile() ;
        String path = "src\\main\\java\\ClinicManagement\\temp\\attach.pdf";
        File file = new File(path);
        FileOutputStream outputStream = null;

            outputStream = new FileOutputStream(file);
            outputStream.write(a);
            outputStream.flush();
            outputStream.close();

            InputStreamResource resource=new InputStreamResource(
                    new FileInputStream(file));

            return  ResponseEntity.ok()
                    .header(String.valueOf(headers))
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);


    }


    @Override
    @GetMapping("/medicine/id/{id}")
    public ResponseEntity<JSONObject> getMedicineById(@PathVariable int id) {
        logger.info("getting Medicine with id : { " + id + " }");
        return new ResponseEntity<>(JsonFactory.toGetMedicineObject(medicineService.getMedicineById(id, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/sickness/id/{id}")
    public ResponseEntity<JSONObject> getSicknessById(@PathVariable int id) {
        logger.info("getting Sickness with id : { " + id + " }");
        return new ResponseEntity<>(JsonFactory.toGetSicknessObject(sicknessService.getSicknessById(id, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/session/id/{id}")
    public ResponseEntity<JSONObject> getSessionById(@PathVariable int id) {
        logger.info("getting Session with id : { " + id + " }");
        return new ResponseEntity<>(JsonFactory.toGetSessionObject(sessionService.getSessionById(id, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/medTime/id/{id}")
    public ResponseEntity<JSONObject> getMedTimeById(@PathVariable int id) {

        logger.info("getting MedTime with id : { " + id + " }");
        return new ResponseEntity<>(JsonFactory.toGetMedTimeObject(medicineTimeService.getMedTimeById(id, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/status/id/{id}")
    public ResponseEntity<JSONObject> getStatusById(int id) {
        logger.info("getting Status with id : { " + id + " }");
        return new ResponseEntity<>(JsonFactory.toGetStatusObject(statusService.getStatusById(id, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/workDay/id/{id}")
    public ResponseEntity<JSONObject> getWorkDayById(@PathVariable int workDayID) {
        logger.info("getting workDay(id) : { " + workDayID + " }");
        return new ResponseEntity<>(JsonFactory.toGetWorkDaysObject(workDaysService.getWorkDayById(workDayID, true)), HttpStatus.OK);
    }


    @Override
    @GetMapping("/medicines/patient/{patientID}")
    public ResponseEntity<JSONArray> getMedicinesForPatient(@PathVariable int patientID) {
        logger.info("getting Medicines By Patient(id) : { " + patientID + " }");
        return new ResponseEntity<>(JsonFactory.toGetMedicines(medicineService.getMedicinesForPatient(patientID, true, true,true)), HttpStatus.OK);
    }


    @Override
    @GetMapping("/sicknesses/patient/{patientID}")
    public ResponseEntity<JSONArray> getSicknessesForPatient(@PathVariable int patientID) {
        logger.info("getting Sicknesses For Patient(id) : { " + patientID + " }");
        return new ResponseEntity<>(JsonFactory.toGetSicknesses(sicknessService.getSicknessesForPatient(patientID, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/attachments/session/{sessionID}")
    public ResponseEntity<JSONArray> getAttachmentsForSession(@PathVariable int sessionID) {
        logger.info("getting Attachments For Session(id) : { " + sessionID + " }");
        return new ResponseEntity<>(JsonFactory.toGetAttachments(attachmentService.getAttachmentsForSession(sessionID, true, true)), HttpStatus.OK);
    }


    @Override
    @GetMapping("/report/patient/{patientID}/doctor/{doctorID}")
    public ResponseEntity<JSONArray> getReportsForPatient(@PathVariable int patientID, @PathVariable int doctorID) {
        logger.info("getting Report For Patient(id) write by Doctor(id) : { " + patientID + " , " + doctorID + " }");
        return new ResponseEntity<>(JsonFactory.toGetSessions(patientService.getReportsForPatient(patientID, doctorID, true, true)), HttpStatus.OK);
    }


    @Override
    @GetMapping("/sessions/patient/{patientID}")
    public ResponseEntity<JSONArray> getSessionsByPatientId(@PathVariable int patientID) {
        logger.info("getting Sessions For Patient(id) : { " + patientID + " }");
        return new ResponseEntity<>(JsonFactory.toGetSessions(sessionService.getSessionByPatientId(patientID, true)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/analyses/patient/{patientID}")
    public ResponseEntity<JSONArray> getAnalysesByPatientId(@PathVariable int patientID) {
        logger.info("getting Analyses For Patient(id) : { " + patientID + " }");
        return new ResponseEntity<>(JsonFactory.toGetAnalysesRates(sessionService.getSessionByPatientId(patientID,true)),HttpStatus.OK);
    }

    @Override
    @GetMapping("/medicineTimes/patient/{patientID}")
    public ResponseEntity<JSONArray> getMedTimesForPatient(@PathVariable int patientID) {
        logger.info("getting MedicineTimes ..");
        return new ResponseEntity<>(JsonFactory.toGetMedTimes(medicineTimeService.getMedTimesForPatient(patientID, true)), HttpStatus.OK);
    }


    @Override
    @GetMapping("/clinic/info")
    public ResponseEntity<JSONObject> getClinicInfo() {
        JSONObject jsonObject = null;
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src\\main\\resources\\static\\info.json"));
            jsonObject = (JSONObject) obj;

            jsonObject.put("total_doctors", doctorService.getDoctors(true).size());
            jsonObject.put("total_patients", patientService.getPatients(true).size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("getting Clinic info ..");
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }


    @Override
    @GetMapping("/patientMed/medicineId/patientId")
    public ResponseEntity<JSONObject> getPatientMedicineByPatientAndMed(@RequestParam("medicineID") int medicineID,@RequestParam("patientID") int patientID)
    {
        logger.info("getting patient medicine with { "+ patientID+" }");
        return new ResponseEntity<>(JsonFactory.toGetPatientMedObject(patientMedicineService.getByPatientAndMedicine(patientID,medicineID,true)),HttpStatus.OK);
    }

}