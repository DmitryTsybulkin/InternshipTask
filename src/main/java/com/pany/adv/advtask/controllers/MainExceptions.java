package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.dto.ExceptionDTO;
import com.pany.adv.advtask.exceptions.*;
import com.pany.adv.advtask.service.convertors.ExceptionDTOConverter;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestControllerAdvice
public class MainExceptions {

    private final ExceptionDTOConverter converter;

    @Autowired
    public MainExceptions(ExceptionDTOConverter converter) {
        this.converter = converter;
    }

    // not found

    @ExceptionHandler(ResourceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDTO> exceptionNotFound(WebRequest request) {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.NOT_FOUND.value());
        exception.setErrorMessage("Resource by address: " + request.getDescription(false) + " is not found.");
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDTO> exceptionFileNotFound() {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.NOT_FOUND.value());
        exception.setErrorMessage("File is not found.");
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntitiesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDTO> exceptionNotFoundPhotos() {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.NOT_FOUND.value());
        exception.setErrorMessage("No entities found.");
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.NOT_FOUND);
    }

    // bad request

    // TODO: 18.03.2018 how to handle???

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ExceptionDTO> exceptionForbidden() {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.FORBIDDEN.value());
        exception.setErrorMessage("Access denied.");
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MissingParametersException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDTO> exceptionBadRequest0(WebRequest request) {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exception.setErrorMessage("Bad request by address: " + request.getDescription(false) +
                " because one or more parameters are missing.");
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDTO> exceptionBadRequest1(WebRequest request) {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exception.setErrorMessage("Bad request by address: " + request.getDescription(false));
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDTO> exceptionBadRequest2(WebRequest request) {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exception.setErrorMessage("Bad request by address: " + request.getDescription(false));
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDTO> exceptionBadRequest3(WebRequest request) {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exception.setErrorMessage("Bad request by address: " + request.getDescription(false));
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDTO> exceptionDuplicateEntity() {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exception.setErrorMessage("Entity is already exists.");
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateFileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDTO> exceptionDuplicateFile() {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exception.setErrorMessage("File is already exists.");
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotImageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDTO> exceptionNotImage() {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exception.setErrorMessage("File is not image.");
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateLoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDTO> exceptionDuplicateLogin() {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exception.setErrorMessage("User with such login already exists.");
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDTO> ioException() {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exception.setErrorMessage("Input or output error.");
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.BAD_REQUEST);
    }

    // unauthorized

    @ExceptionHandler(UnauthrorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ExceptionDTO> exceptionUnauthorized() {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.UNAUTHORIZED.value());
        exception.setErrorMessage("User is not unauthorized");
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.UNAUTHORIZED);
    }

    //??????

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResponseEntity<ExceptionDTO> exceptionUnsupportedType() {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        exception.setErrorMessage("Unsupported media type");
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(ConversionNotSupportedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDTO> exceptionServer(WebRequest request) {
        APIException exception = new APIException();
        exception.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exception.setErrorMessage("Internal server error by address: " + request.getDescription(false));
        return new ResponseEntity<ExceptionDTO>(converter.toDto(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
