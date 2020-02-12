package veil.internetshop.simple.exceptions.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ResourceConflictException extends ServiceException{

    public ResourceConflictException(){
    }

    public ResourceConflictException(String message){
        super(message);
    }

    public ResourceConflictException(String message, Throwable cause){
        super(message, cause);
    }

    public ResourceConflictException(Throwable cause){
        super(cause);
    }

    public ResourceConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
