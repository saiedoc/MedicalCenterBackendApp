package ClinicManagement.notification;

public class PushNotificationRequest {

    private String title;
    private String message;
    private String topic;
    private String token;

    public PushNotificationRequest() {
    }

    public PushNotificationRequest(String title, String message, String topic, String token) {
        this.title = title;
        this.message = message;
        this.topic = topic;
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "PushNotificationRequest{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", topic='" + topic + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}