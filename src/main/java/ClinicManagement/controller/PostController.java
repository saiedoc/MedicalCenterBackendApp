package ClinicManagement.controller;

import ClinicManagement.aid.IPostRequests;
import ClinicManagement.entity.*;
import ClinicManagement.factory.JsonFactory;
import ClinicManagement.notification.PushNotificationRequest;
import ClinicManagement.notification.PushNotificationService;
import ClinicManagement.service.*;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.util.Set;

@RestController
@RequestMapping("api/post")
public class PostController implements IPostRequests {


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

    @Autowired
    private SpecDoctorService specDoctorService;

    @Autowired
    private PushNotificationService pushNotificationService;

    @Autowired
    private TokenService tokenService;


    private final Logger logger = LoggerFactory.getLogger(PostController.class);


    @Override
    @PostMapping("/login")
    public ResponseEntity<JSONObject> login(@RequestBody Account account) {
        System.out.println("Login ..");
        Account replay = accountService.login(account);
        return new ResponseEntity<>(JsonFactory.toLogin(accountService.login(account)), HttpStatus.OK);

    }


    @Override
    @PostMapping("/account")
    public ResponseEntity<JSONObject> addAccount(@RequestBody Account account) {
        Account a = accountService.addAccount(account);
        logger.info("posting account ..");
        return new ResponseEntity<>(JsonFactory.toGetAccountObject(a), HttpStatus.OK);
    }

    @Override
    @PostMapping("/doctor")
    public ResponseEntity<JSONObject> addDoctor(@RequestBody Doctor doctor) {

        Role role = roleService.getRoleByName("DOCTOR", true);
        doctor.getAccount().setRole(role);

        Set<SpecDoctor> sp = doctor.getSpecDoctor();

        doctor.setSpecDoctor(null);

        Doctor d = doctorService.addDoctor(doctor);

        if (sp != null) {
            for (SpecDoctor s : sp) {
                s.setDoctor(doctor);
                specDoctorService.updateSpecDoctor(s);
            }
        }

        logger.info("posting doctor ..");
        return new ResponseEntity<>(JsonFactory.toGetDoctorObject(d), HttpStatus.OK);
    }

    @Override
    @PostMapping("/patient")
    public ResponseEntity<JSONObject> addPatient(@RequestBody Patient patient) {

        Role role = roleService.getRoleByName("PATIENT", true);
        patient.getAccount().setRole(role);

        Set<PatientMedicine> pm = patient.getPatientMedicines();
        Set<PatientSickness> ps = patient.getPatientSicknesses();

        patient.setPatientMedicines(null);
        patient.setPatientSicknesses(null);

        Patient p = patientService.addPatient(patient);

        if (pm != null) {
            for (PatientMedicine m : pm) {
                m.setPatient(p);
                patientMedicineService.updatePatientMedicine(m);
            }
        }

        if (ps != null) {
            for (PatientSickness s : ps) {
                s.setPatient(p);
                patientSicknessService.updatePatientSickness(s);
            }
        }

        logger.info("posting patient ..");
        return new ResponseEntity<>(JsonFactory.toGetPatientObject(p), HttpStatus.OK);
    }

    @Override
    @PostMapping("/speci")
    public ResponseEntity<JSONObject> addSpecialization(@RequestBody Specialization specialization) {

        Specialization spec = specializationService.addSpecialization(specialization);
        logger.info("posting specialization ..");
        return new ResponseEntity<>(JsonFactory.toGetSpecializationObject(spec), HttpStatus.OK);
    }

    @Override
    @PostMapping("/role")
    public ResponseEntity<JSONObject> addRole(@RequestBody Role role) {
        Role r = roleService.addRole(role);
        logger.info("posting role ..");
        return new ResponseEntity<>(JsonFactory.toGetRoleObject(r), HttpStatus.OK);
    }

    @Override
    @PostMapping("/report")
    public ResponseEntity<JSONObject> writeReport(@RequestBody Session session) {
        Session s = sessionService.writeReport(session);
        logger.info("posting report ..");
        return new ResponseEntity<>(JsonFactory.toGetSessionObject(s), HttpStatus.OK);
    }

    @Override
    @PostMapping("/medicine")
    public ResponseEntity<JSONObject> addMedicine(@RequestBody Medicine medicine) {
        Medicine m = medicineService.addMedicine(medicine);
        logger.info("posting medicine ..");
        return new ResponseEntity<>(JsonFactory.toGetMedicineObject(m), HttpStatus.OK);
    }

    @Override
    @PostMapping("/sickness")
    public ResponseEntity<JSONObject> addSickness(@RequestBody Sickness sickness) {
        Sickness sc = sicknessService.addSickness(sickness);
        logger.info("posting sickness ..");
        return new ResponseEntity<>(JsonFactory.toGetSicknessObject(sc), HttpStatus.OK);
    }

    @Override
    @PostMapping("/attachment")
    public ResponseEntity<JSONObject> addAttachment(@RequestBody Attachment attachment) {
        System.out.println(attachment.getBytesFile());
        Attachment at = attachmentService.addAttachment(attachment);
        logger.info("posting attachment ..");
        return new ResponseEntity<>(JsonFactory.toGetAttachmentObject(at), HttpStatus.OK);
    }


    @Override
    @PostMapping("/appointment")
    public ResponseEntity<JSONObject> makeAppointment(@RequestBody JSONObject js) {

        Doctor doctor = doctorService.getDoctorById((Integer) js.get("doctor_id"), true);
        Patient patient = patientService.getPatientById((Integer) js.get("patient_id"), true);

        Appointment appointment = new Appointment().createAppointmentWith(doctor, patient, js);

        appointment.setStatus(statusService.getStatusByName("PENDING", true));
        Appointment ap = appointmentService.addAppointment(appointment);
        logger.info("posting appointment ..");
         pushNotificationService.sendPushNotificationToToken(createNotification(appointment));
        return new ResponseEntity<>(JsonFactory.toGetAppointmentObject(ap), HttpStatus.OK);
    }


    @Override
    @PostMapping("/wTdoctor")
    public ResponseEntity<JSONObject> addWorkTimeDoctor(@RequestBody WorkTimeDoctor workTimeDoctor) {
        WorkTimeDoctor wt = workTimeDoctorService.addWorkTimeDoctor(workTimeDoctor);
        logger.info("posting work time doctor ..");
        return new ResponseEntity<>(JsonFactory.toGetWorkTimeDoctorObject(wt), HttpStatus.OK);
    }

    @Override
    @PostMapping("/token")
    public ResponseEntity<JSONObject> addToken(@RequestBody JSONObject js) {

        int id = (int) js.get("profile_id");
        String role = (String) js.get("role");

        Token token = new Token();
        token.setTokenBody((String) js.get("token"));

        if(role.equals("DOCTOR")) {
            Doctor doctor = doctorService.getDoctorById(id, true);
            token.setDoctor(doctor);
        }
        else
        if(role.equals("PATIENT")){
            Patient patient = patientService.getPatientById(id,true);
            token.setPatient(patient);
        }



        Token t = tokenService.addToken(token);

        logger.info("posting token ..");
        return new ResponseEntity<>(JsonFactory.toGetTokenObject(t),HttpStatus.OK);

    }

    @Override
    @PostMapping("/medicineTime")
    public ResponseEntity<JSONObject> addMedTime(@RequestBody MedicineTime medicineTime) {
        MedicineTime medT = medicineTimeService.addMedTime(medicineTime);
        logger.info("posting medicine time ..");
        return new ResponseEntity<>(JsonFactory.toGetMedTimeObject(medT), HttpStatus.OK);
    }

    @Override
    @PostMapping("/workDay")
    public ResponseEntity<JSONObject> addWorkDay(@RequestBody WorkDays workDays) {
        WorkDays wrk = workDaysService.addWorkDay(workDays);
        logger.info("posting work Day ..");
        return new ResponseEntity<>(JsonFactory.toGetWorkDaysObject(wrk), HttpStatus.OK);

    }

    @PostMapping("/clinic/info")
    public ResponseEntity<JSONObject> addClinicInfo(@RequestBody JSONObject jsonObject) {
        try {

            FileWriter writer = new FileWriter("src\\main\\resources\\static\\info.json");
            writer.write(jsonObject.toJSONString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    private PushNotificationRequest createNotification(Appointment appointment) {
        String token = appointment.getDoctor().getToken().getTokenBody();
        try {
            if(token == null) throw new NullPointerException();
        }
        catch (NullPointerException np){
            logger.error("There is not token for the given doctor .. ");
            np.printStackTrace();
        }

        return new PushNotificationRequest("New Appointment", "You have  a new Appointment Today", null, token);
    }




}

