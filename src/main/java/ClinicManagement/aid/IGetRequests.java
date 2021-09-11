package ClinicManagement.aid;


import ClinicManagement.entity.Address;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public interface IGetRequests {

    ResponseEntity<List<JSONArray>> getSpecializations();

    ResponseEntity<JSONArray> getDoctors();

    ResponseEntity<JSONArray> getRoles();

    ResponseEntity<JSONArray> getAccounts();

    ResponseEntity<JSONArray> getMedicines();

    ResponseEntity<JSONArray> getPatients();

    ResponseEntity<JSONArray> getSicknesses();

    ResponseEntity<JSONArray> getAppointments();

    ResponseEntity<JSONArray> getMedTimesForPatient(int id);

    ResponseEntity<JSONArray> getStatuses();


    ResponseEntity<JSONArray> getWorkDays();

    ResponseEntity<JSONObject> getDoctorById(int id);

    ResponseEntity<JSONObject> getDoctorByName(String name);

    ResponseEntity<JSONArray> getDoctorsBySpecName(String name);

    ResponseEntity<JSONArray> getDoctorsByWorkDayName(String name);

    ResponseEntity<JSONObject> getDoctorByAccountId(int id);

    ResponseEntity<JSONArray> getPatientsForDoctor(int patientID);


    ResponseEntity<JSONObject> getWorkDayById(int workDayID);

    ResponseEntity<JSONObject> getPatientByID(int id);

    ResponseEntity<JSONArray> getPatientsByName(String name);

    ResponseEntity<JSONObject> getPatientByAccountId(int id);

    ResponseEntity<JSONArray> getPatientsBySicknessName(String name);

    ResponseEntity<JSONArray> getPatientsByMedicine(String name);

    ResponseEntity<JSONArray> getPatientsByAddress(Address address);

    ResponseEntity<JSONArray> getPatientByDate(Date date);

    ResponseEntity<JSONArray> getPatientsByDoctorId(int id);

    ResponseEntity<JSONObject> getAppointmentById(int id);

    ResponseEntity<JSONArray> getAppointmentsByDoctorIdAndPendingStatusInDate(int doctorID);

    ResponseEntity<JSONArray> getAppointmentsByDoctorIdAndAcceptStatusInDate(int doctorID);

    ResponseEntity<JSONArray> getAppointmentsByDoctorIdAndRefuseStatusInDate(int doctorID);


    ResponseEntity<JSONArray> getAppointmentByDate( LocalDateTime date);

    ResponseEntity<JSONArray> getAppointmentsByPatientIdAndPendingState(int patientId, java.util.Date date);

    ResponseEntity<JSONArray> getAppointmentsByPatientIdAndAcceptState(int patientId, java.util.Date date);

    ResponseEntity<JSONArray> getAppointmentsByPatientIdAndRefuseState(int patientId, java.util.Date date);

    ResponseEntity<JSONArray> getAppointmentsForDoctorAndByPendingStatusAndDate(int doctorId, java.util.Date date);

    ResponseEntity<JSONArray> getAppointmentsForDoctorAndByAcceptStatusAndDate(int doctorId, java.util.Date date);

    ResponseEntity<JSONArray> getAppointmentsForDoctorAndByRefusedStatusAndDate(int doctorId, java.util.Date date);

    ResponseEntity<JSONArray> getAppointmentsByDoctorIdAndPendingStatusInDate(int doctorId, java.util.Date date);

    ResponseEntity<JSONArray> getAppointmentsByDoctorIdAndAcceptStatusInDate(int doctorId, java.util.Date date);

    ResponseEntity<JSONArray> getAppointmentsByDoctorIdAndRefuseStatusInDate(int doctorId, java.util.Date date);

    ResponseEntity<JSONObject> getRoleById(int id);

    ResponseEntity<JSONObject> getAccountById(int id);

    ResponseEntity<JSONObject> getSpecializationById(int id);

    ResponseEntity<JSONObject> getAttachmentInfoById(int id);

    byte[] downloadAttachment(int id);

    ResponseEntity<Resource> getAttachmentURL(int id) throws FileNotFoundException, IOException;

    ResponseEntity<JSONObject> getMedicineById(int id);

    ResponseEntity<JSONObject> getSicknessById(int id);

    ResponseEntity<JSONObject> getSessionById(int id);

    ResponseEntity<JSONObject> getMedTimeById(int id);

    ResponseEntity<JSONObject> getStatusById(int id);


    ResponseEntity<JSONArray> getMedicinesForPatient(int patientID);

    ResponseEntity<JSONArray> getSicknessesForPatient(int patientID);

    ResponseEntity<JSONArray> getAttachmentsForSession(int sessionID);

    ResponseEntity<JSONArray> getReportsForPatient(int patientID, int doctorID);

    ResponseEntity<JSONArray> getSessionsByPatientId(int patient_id);

    ResponseEntity<JSONArray> getAnalysesByPatientId(int patientID);

    ResponseEntity<JSONObject> getClinicInfo();

    ResponseEntity<JSONObject> getPatientMedicineByPatientAndMed(int medicineID, int patientID);
}
