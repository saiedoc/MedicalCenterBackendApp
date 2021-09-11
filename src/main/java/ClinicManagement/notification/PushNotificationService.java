package ClinicManagement.notification;

import ClinicManagement.entity.Appointment;
import ClinicManagement.repositories.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class PushNotificationService {


    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    private FCMService fcmService;


    private final Logger logger = LoggerFactory.getLogger(PushNotificationService.class);


    public PushNotificationService() { }
    
    public void sendPushNotificationWithoutData(PushNotificationRequest request) {
        try {
            fcmService.sendMessageWithoutData(request);
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
    }

    public void sendPushNotificationToToken(PushNotificationRequest request) {
        try {
            fcmService.sendMessageToToken(request);
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
    }


    public void sendPushNotificationToDoctor(Appointment appointment)
    {
        int num = appointmentRepository.findByAppointmentAndStatusName("PENDING",true).size();
        PushNotificationRequest request = new PushNotificationRequest();
        request.setTitle("Booking");
        request.setMessage("New Appointment is Booking now \n Total : " + num);
        request.setToken(appointment.getDoctor().getToken().getTokenBody());
        sendPushNotificationToToken(request);
    }
}