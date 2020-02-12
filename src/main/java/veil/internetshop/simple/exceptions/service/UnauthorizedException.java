package veil.internetshop.simple.exceptions.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends ServiceException{

    public UnauthorizedException(){
    }

    public UnauthorizedException(String message){
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause){
        super(message, cause);
    }

    public UnauthorizedException(Throwable cause){
        super(cause);
    }

    public UnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
