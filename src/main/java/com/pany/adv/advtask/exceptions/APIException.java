package com.pany.adv.advtask.exceptions;


import org.springframework.web.context.request.WebRequest;

public class APIException extends RuntimeException {

    private int errorCode;
    private String errorMessage;

    public APIException(int errorCode, String errorMessage, WebRequest request) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public APIException() {}

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}