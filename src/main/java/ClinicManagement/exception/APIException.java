package ClinicManagement.exception;

import org.springframework.http.HttpStatus;

public abstract class APIException extends RuntimeException {

    public abstract HttpStatus getStatus();
    public  APIException(String message){
        super(message);
    }

}
