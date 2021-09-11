package ClinicManagement.exception;

import org.springframework.http.HttpStatus;

public class ReplicatedException extends APIException {

    public ReplicatedException(String message) {
        super(message);
    }
    public ReplicatedException(){
        super("Replicated data");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
