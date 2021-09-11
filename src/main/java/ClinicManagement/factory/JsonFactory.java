package ClinicManagement.factory;

import ClinicManagement.entity.*;
import ClinicManagement.service.DoctorService;
import ClinicManagement.service.PatientService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;


public class JsonFactory {

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;


    private static <T> List<T> convertToList(Set<T> set) {
        if (set == null) return List.of();
        return List.copyOf(set);
    }



    public static JSONObject toLogin(Account account) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("role_id", account.getRole().getRoleId());
        jsonObject.put("account_id", account.getAccountId());
        jsonObject.put("name", account.getRole().getName());
        return jsonObject;
    }

    /**
     * Doctor Gets
     **/
    public static JSONObject toGetDoctorObject(Doctor doctor) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("doctor_id", doctor.getDoctorId());
        jsonObject.put("name", doctor.getName());
        jsonObject.put("phoneNumber", doctor.getPhoneNumber());
        jsonObject.put("email", doctor.getEmail());
        jsonObject.put("account_id",doctor.getAccount().getAccountId());
        jsonObject.put("dateOfBirth", doctor.getDateOfBirth());
        jsonObject.put("gender", doctor.getGender());
        jsonObject.put("specializations",toGetSpecializations(doctor.getSpecializations()));
        jsonObject.put("specDoctor",toGetSpecsDoctor(convertToList(doctor.getSpecDoctor())));
        jsonObject.put("workTimeDoctor",toGetWorkTimesDoctor(convertToList(doctor.getWorkTimeDoctors())));
        return jsonObject;
    }
    public static JSONArray toGetDoctors(List<Doctor> doctors) {
        JSONArray jsonArray = new JSONArray();
        for (Doctor x : doctors) {
            JSONObject jsonObject = toGetDoctorObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


    /**
     * Specialization Gets
     **/
    public static JSONObject toGetSpecializationObject(Specialization specialization) {
        JSONObject jsonSpecialization = new JSONObject();
        jsonSpecialization.put("spec_id", specialization.getSpecId());
        jsonSpecialization.put("name", specialization.getName());
        return jsonSpecialization;
    }
    public static JSONArray toGetSpecializations(List<Specialization> specializations) {
        JSONArray jsonSpecializations = new JSONArray();
        for (Specialization x : specializations) {
            JSONObject jsonSpecialization = toGetSpecializationObject(x);
            jsonSpecializations.add(jsonSpecialization);
        }
        return jsonSpecializations;
    }


    /**
     * Role Gets
     **/
    public static JSONObject toGetRoleObject(Role role) {
        JSONObject jsonRole = new JSONObject();
        jsonRole.put("role_id", role.getRoleId());
        jsonRole.put("role_name", role.getName());
        return jsonRole;
    }
    public static JSONArray toGetRoles(List<Role> roles) {
        JSONArray jsonRoles = new JSONArray();
        for (Role x : roles) {
            JSONObject jsonRole = toGetRoleObject(x);

            jsonRoles.add(jsonRole);
        }
        return jsonRoles;
    }

    /**
     * Account Gets
     **/
    public static JSONObject toGetAccountObject(Account account) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("account_id", account.getAccountId());
        jsonObject.put("username", account.getUsername());
        jsonObject.put("password", account.getPassword());
        return jsonObject;
    }
    public static JSONArray toGetAccounts(List<Account> accounts) {
        JSONArray jsonArray = new JSONArray();
        for (Account x : accounts) {
            JSONObject jsonObject = toGetAccountObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


    /**
     * Patient Gets
     **/
    public static JSONObject toGetPatientObject(Patient patient) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("patient_id", patient.getPatientId());
        jsonObject.put("name", patient.getName());
        jsonObject.put("gender",patient.getGender());
        jsonObject.put("phoneNumber", patient.getPhoneNumber());
        jsonObject.put("address", toGetAddressObject(patient.getAddress()));
        jsonObject.put("account_id", patient.getAccount().getAccountId());
        jsonObject.put("dateOfBirth", patient.getDateOfBirth());
        jsonObject.put("email",patient.getEmail());
        jsonObject.put("sec_phoneNumber", patient.getSec_phoneNumber());
        jsonObject.put("patientSickness",toGetPatientSicknesses(convertToList(patient.getPatientSicknesses())));
        jsonObject.put("patientMedicine",toGetPatientMedicines(convertToList(patient.getPatientMedicines())));
        return jsonObject;
    }
    public static JSONArray toGetPatients(List<Patient> patients) {

        JSONArray jsonArray = new JSONArray();
        for (Patient x : patients) {
            JSONObject jsonObject = toGetPatientObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


    /**
     * Medicine Gets
     **/
    public static JSONObject toGetMedicineObject(Medicine medicine) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("medicine_id", medicine.getMedicineId());
        jsonObject.put("name", medicine.getName());
        return jsonObject;
    }
    public static JSONArray toGetMedicines(List<Medicine> medicines) {
        JSONArray jsonArray = new JSONArray();
        for (Medicine x : medicines) {

            JSONObject jsonObject = toGetMedicineObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


    /**
     * Sickness Gets
     **/
    public static JSONObject toGetSicknessObject(Sickness sickness) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sickness_id", sickness.getSicknessId());
        jsonObject.put("name", sickness.getName());
        return jsonObject;
    }
    public static JSONArray toGetSicknesses(List<Sickness> sicknesses) {
        JSONArray jsonArray = new JSONArray();
        for (Sickness x : sicknesses) {
            JSONObject jsonObject = toGetSicknessObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


    /**
     * Appointment Gets
     **/
    public static JSONObject toGetAppointmentObject(Appointment appointment) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appointment_id", appointment.getAppointmentId());
        jsonObject.put("dateOfBooking", appointment.getDateOfBooking());
        jsonObject.put("timeOfBooking", appointment.getTimeOfBooking());
        jsonObject.put("status",appointment.getStatus().getName());
        jsonObject.put("doctor_id", appointment.getDoctor().getDoctorId());
        jsonObject.put("patient_id", appointment.getPatient().getPatientId());
        jsonObject.put("doctor_name", appointment.getDoctor().getName());
        jsonObject.put("patient_name", appointment.getPatient().getName());
        return jsonObject;
    }
    public static JSONArray toGetAppointments(List<Appointment> appointments) {
        JSONArray jsonArray = new JSONArray();
        for (Appointment x : appointments) {
            JSONObject jsonObject = toGetAppointmentObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


    /**
     * Attachments Gets
     **/
    public static JSONObject toGetAttachmentObject(Attachment attachment) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("attachment_id", attachment.getAttachmentId());
        jsonObject.put("name",attachment.getName());
        jsonObject.put("extension", attachment.getExtension());
        jsonObject.put("session_id", attachment.getSession().getSessionId());
        return jsonObject;
    }
    public static JSONArray toGetAttachments(List<Attachment> attachments) {
        JSONArray jsonArray = new JSONArray();
        for (Attachment x : attachments) {
            JSONObject jsonObject = toGetAttachmentObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


    /**
     * Sessions Gets
     **/
    public static JSONObject toGetSessionObject(Session session) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("session_id", session.getSessionId());
        jsonObject.put("date",session.getSessionDate());
        jsonObject.put("report", session.getReport());
        jsonObject.put("patient_id", session.getPatient().getPatientId());
        jsonObject.put("doctor_id", session.getDoctor().getDoctorId());
        jsonObject.put("analysesRate",toGetAnalysesRateObject(session.getAnalysesRate()));
        return jsonObject;
    }
    public static JSONArray toGetSessions(List<Session> sessions) {

        JSONArray jsonArray = new JSONArray();
        for (Session x : sessions) {
            JSONObject jsonObject = toGetSessionObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


    /**
     * WorkTime Doctor Gets
     */
    public static JSONObject toGetWorkTimeDoctorObject(WorkTimeDoctor workTimeDoctor) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("wtDoctor_id", workTimeDoctor.getWtDoctorId());
        jsonObject.put("doctor_id", workTimeDoctor.getDoctor().getDoctorId());
        jsonObject.put("workDay_id", workTimeDoctor.getWorkDays().getWorkDayId());
        jsonObject.put("timeIn", workTimeDoctor.getTimeIN());
        jsonObject.put("timeOut", workTimeDoctor.getTimeOUT());

        return jsonObject;
    }
    public static JSONArray toGetWorkTimesDoctor(List<WorkTimeDoctor> workTimesDoctor) {

        JSONArray jsonArray = new JSONArray();
        for (WorkTimeDoctor x : workTimesDoctor) {
            JSONObject jsonObject = toGetWorkTimeDoctorObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     * MedTime Gets
     **/
    public static JSONObject toGetMedTimeObject(MedicineTime medicineTime) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("med_time_id", medicineTime.getMedicineTimeId());
        jsonObject.put("med_id",medicineTime.getPatientMedicine().getMedicine().getMedicineId()) ;
        jsonObject.put("med_name",medicineTime.getPatientMedicine().getMedicine().getName()) ;
        jsonObject.put("time", medicineTime.getMedTime());
        jsonObject.put("patient_med_id", medicineTime.getPatientMedicine().getPatientMedicineId());
        return jsonObject;
    }
    public static JSONArray toGetMedTimes(List<MedicineTime> medicineTimes) {

        JSONArray jsonArray = new JSONArray();
        for (MedicineTime x : medicineTimes) {
            JSONObject jsonObject = toGetMedTimeObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     * Status Gets
     **/
    public static JSONObject toGetStatusObject(Status status) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status_id", status.getStatusId());
        jsonObject.put("name", status.getName());
        return jsonObject;
    }
    public static JSONArray toGetStatuses(List<Status> statuses) {
        JSONArray jsonArray = new JSONArray();
        for (Status x : statuses) {
            JSONObject jsonObject = toGetStatusObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;

    }

    /**
     * PatientMedicine Gets
     **/
    public static JSONObject toGetPatientMedObject(PatientMedicine patientMedicine) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("patientMed_id", patientMedicine.getPatientMedicineId());
        jsonObject.put("patient_id", patientMedicine.getPatient().getPatientId());
        jsonObject.put("medicine",toGetMedicineObject(patientMedicine.getMedicine()));
        jsonObject.put("medicineTime",toGetMedTimes(convertToList(patientMedicine.getMedicineTimes())));
        return jsonObject;
    }
    public static JSONArray toGetPatientMedicines(List<PatientMedicine> patientMedicines) {
        JSONArray jsonArray = new JSONArray();
        for (PatientMedicine x : patientMedicines) {
            JSONObject jsonObject = toGetPatientMedObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;

    }


    /**
     * Address Gets
     **/
    public static JSONObject toGetAddressObject(Address address){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("address_id",address.getAddressId()) ;
        jsonObject.put("province",address.getProvince());
        jsonObject.put("city",address.getCity());
        jsonObject.put("avenue",address.getAvenue());
        return jsonObject;
    }
    public static JSONArray toGetAddresses(List<Address> addresses) {
        JSONArray jsonArray = new JSONArray();
        for (Address x : addresses) {
            JSONObject jsonObject = toGetAddressObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;

    }

    /**
     * WorkDay Gets
     * **/
    public static JSONObject toGetWorkDaysObject(WorkDays workDays) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workDay_id",workDays.getWorkDayId());
        jsonObject.put("name",workDays.getName());
        return jsonObject;
    }
    public static JSONArray toGetWorkDays(List<WorkDays> workDays) {
        JSONArray jsonArray = new JSONArray();
        for (WorkDays x : workDays) {
            JSONObject jsonObject = toGetWorkDaysObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     *  Analyses Rate Gets
     **/

    public static JSONObject toGetAnalysesRateObject(AnalysesRate analysesRate) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("analysesRate_id",analysesRate.getAnalysesRateId());
        jsonObject.put("oxygenation",analysesRate.getOxygenation());
        jsonObject.put("blood_pressure_low",analysesRate.getBloodPressureL());
        jsonObject.put("blood_pressure_high",analysesRate.getBloodPressureH());
        jsonObject.put("blood_glucose",analysesRate.getBloodGlucose());
        return jsonObject;
    }
    public static JSONArray toGetAnalysesRates(List<Session> sessions) {
        JSONArray jsonArray = new JSONArray();
        for (Session x : sessions) {
            JSONObject jsonObject = toGetAnalysesRateObject(x.getAnalysesRate());
            jsonObject.put("session_date",x.getSessionDate());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     * Spec Doctor Gets
     * **/
    public static JSONObject toGetSpecDoctorObject(SpecDoctor specDoctor) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("specDoctor_id",specDoctor.getSpecDoctorId());
        jsonObject.put("doctor_id",specDoctor.getDoctor().getDoctorId());
        jsonObject.put("spec_id",specDoctor.getSpecialization().getSpecId());
        return jsonObject;
    }
    public static JSONArray toGetSpecsDoctor(List<SpecDoctor> specDoctors) {
        JSONArray jsonArray = new JSONArray();
        for (SpecDoctor x : specDoctors) {
            JSONObject jsonObject = toGetSpecDoctorObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


     /**
 * Patient Sickness Gets
 * **/
    public static JSONObject toGetPatientSicknessObject(PatientSickness patientSickness) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("patientSickness_id",patientSickness.getPatientSicknessId());
        jsonObject.put("sickness_id",patientSickness.getSickness().getSicknessId());
        jsonObject.put("name",patientSickness.getSickness().getName());
        return jsonObject;
    }
    public static JSONArray toGetPatientSicknesses(List<PatientSickness> patientSicknesses) {
        JSONArray jsonArray = new JSONArray();
        for (PatientSickness x : patientSicknesses) {
            JSONObject jsonObject = toGetPatientSicknessObject(x);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /** Token Gets ...**/
    public static JSONObject toGetTokenObject(Token t) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token_id",t.getTokenId());
        jsonObject.put("body",t.getTokenBody());
        return jsonObject;
    }

}
