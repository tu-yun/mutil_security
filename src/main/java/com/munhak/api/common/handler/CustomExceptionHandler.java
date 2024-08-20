package com.munhak.api.common.handler;

import com.munhak.api.common.exception.CustomApiException;
import com.munhak.api.common.exception.CustomForbiddenException;
import com.munhak.api.common.exception.CustomValidationException;
import com.munhak.api.dto.common.ResponseDto;
import com.munhak.api.enums.ResultCodeType;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ResponseDto<>(ResultCodeType.FAIL, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomForbiddenException.class)
    public ResponseEntity<?> forbiddenException(CustomForbiddenException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ResponseDto<>(ResultCodeType.FAIL, e.getMessage(), null), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> validationApiException(CustomValidationException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ResponseDto<>(ResultCodeType.FAIL, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    //@RequestBody, @ModelAttribute validation Exception
    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException e, WebRequest request) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ResponseDto<>(ResultCodeType.FAIL, e.getMessage(), null, e.getBindingResult().getFieldErrors()), HttpStatus.BAD_REQUEST);
    }

    //@RequestParam, @PathVariable validation Exception
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationExceptions(ConstraintViolationException e, WebRequest request) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ResponseDto<>(ResultCodeType.FAIL, e.getMessage(), null, e.getConstraintViolations()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> allApiException(Exception e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ResponseDto<>(ResultCodeType.FAIL, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
