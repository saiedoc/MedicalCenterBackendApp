package ClinicManagement.exception;

import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;

public class NotFoundException extends APIException {

    private String ex ;

    public NotFoundException(String message) {
        super(message);
        System.out.println(message);
        this.ex=message;
    }
    public NotFoundException()
    {
        super("Not Found in DataBase");
        this.ex="Not Found in DataBase";
        System.out.println(ex);
    }


    @Override
    public String toString() {
        return "NotFoundException{" +
                "ex='" + ex + '\'' +
                '}';
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
