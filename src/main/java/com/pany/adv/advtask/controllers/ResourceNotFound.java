package com.pany.adv.advtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ResourceNotFound {

    @ExceptionHandler(APIException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exceptionNotFound(APIException e) {
        return e.getMessage();
    }
//
//    @ExceptionHandler(ExceptionAPI.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String exceptionBadRequest(ExceptionAPI e) {
//        return e.getMessage();
//    }
//
//    @ExceptionHandler(ExceptionAPI.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public String exceptionUnauthorized(ExceptionAPI e) {
//        return e.getMessage();
//    }
//
//    @ExceptionHandler(ExceptionAPI.class)
//    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//    public String exceptionUnsupportedType(ExceptionAPI e) {
//        return e.getMessage();
//    }
//
//    @ExceptionHandler(ExceptionAPI.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String exceptionServer(ExceptionAPI e) {
//        return e.getMessage();
//    }

}
