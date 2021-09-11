package ClinicManagement.service;


import ClinicManagement.controller.DeleteController;
import ClinicManagement.entity.Appointment;
import ClinicManagement.entity.Status;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.undertow.UndertowWebServer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class AppointmentService {


    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private StatusService statusService;

    private final Logger logger = LoggerFactory.getLogger(AppointmentService.class);


    public Appointment addAppointment(Appointment appointment) {

        try {
            return repository.save(appointment);
        } catch (Exception ex) {
            throw new ReplicatedException();
        }

    }

    public Appointment updateAppointment(Appointment appointment) {
        try {
            return repository.save(appointment);
        } catch (Exception ex) {
            throw new ReplicatedException();
        }
    }

    public Appointment getAppointmentById(int id, boolean isActive) {
        try {
            return repository.findByAppointmentIdAndIsActive(id, isActive).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException();
        }

    }

    public List<Appointment> getAppointmentsByStatus(String name, boolean isActive) {
        List<Appointment> appointments = repository.findByAppointmentAndStatusName(name, isActive);
        if (appointments.isEmpty()) throw new NotFoundException();
        return appointments;
    }

    public List<Appointment> getAppointments(boolean isActive) {
        List<Appointment> appointments = repository.findByAppointmentAndStatusName("ACCEPT", isActive);
        if (appointments.isEmpty()) throw new NotFoundException();
        return appointments;
    }

    public List<Appointment> getAppointmentByTime(Time time, boolean isActive) {
        List<Appointment> appointments = repository.findByTimeOfBookingAndIsActive(time, isActive);
        if (appointments.isEmpty()) throw new NotFoundException();
        return appointments;
    }

    public List<Appointment> getAppointmentByDateAndStatus(Date date, String name, boolean isActive) {
        List<Appointment> appointments = repository.findByDateAndStatusName(date, name, isActive);
        if (appointments.isEmpty())  logger.info("there are no booked appointment yet");
        return appointments;
    }

    public List<Appointment> getAppointmentByDate(Date date, boolean isActive) {
        List<Appointment> appointments = repository.findByDateOfBookingAndIsActive(date, isActive);
        if (appointments.isEmpty()) throw new NotFoundException();
        return appointments;
    }

    public List<Appointment> getAppointmentByDateAndTime(Date date, Time time, boolean isActive) {
        List<Appointment> appointments = repository.findByDateOfBookingAndTimeOfBookingAndIsActive(date, time, isActive);
        if (appointments.isEmpty()) throw new NotFoundException();
        return appointments;
    }

    public List<Appointment> getAppointmentsByPatientIdAndStatus(int patient_id, String status, boolean isDoctorActive, boolean isPatientActive) {
        List<Appointment> appointments = repository.findByPatientIdAndStatus(patient_id, status, isDoctorActive, isPatientActive);
        if (appointments.isEmpty()) throw new NotFoundException();
        return appointments;
    }

    public List<Appointment> getAppointmentsByDoctorIdAndStatus(int doctor_id, String status, boolean isAppointmentActive, boolean isDoctorActive) {
        List<Appointment> appointments = repository.findByDoctorIdAndStatus(doctor_id, status, isAppointmentActive, isDoctorActive);
        if (appointments.isEmpty()) throw new NotFoundException();
        return appointments;
    }

    public List<Appointment> getAppointmentsByDoctorIdAndDateAndStatus(int doctor_id, Date date, String status, boolean isAppointmentActive, boolean isDoctorActive) {
        List<Appointment> appointments = repository.findByDoctorIdAndDate(doctor_id, date, status, isAppointmentActive, isDoctorActive);
        if (appointments.isEmpty()) throw new NotFoundException();
        return appointments;
    }

    public void activeAppointment(int id, boolean isActive) {
        try {
            Appointment appointment = repository.findById(id).get();
            appointment.setActive(isActive);
            repository.save(appointment);
        } catch (NoSuchElementException ex) {
            throw new NotFoundException("Can't active or non-active because not found object");
        }
    }

    public void cancelationOfPreviosAppointment(List<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            activeAppointment(appointment.getAppointmentId(), false);
        }
    }

    public List<Appointment> getAppointmentsByDoctorIdAndBeforeDateAndStatus(int doctor_id, Date date, boolean isAppointmentActive, boolean isDoctorActive) {
        List<Appointment> appointments = repository.findByDoctorIdAndBeforeDate(doctor_id, date,isAppointmentActive, isDoctorActive);
        return appointments;
    }


    public List<Appointment> getAppointmentsByPatientIdAndBeforeDateAndStatus(int patientId, Date date, boolean isPatientActive, boolean isAppointmentActive) {
        List<Appointment> appointments = repository.findByPatientIdAndBeforeDate(patientId, date,isAppointmentActive, isPatientActive);
        return appointments;
    }

}
