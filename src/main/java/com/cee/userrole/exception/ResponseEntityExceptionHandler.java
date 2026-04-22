package com.cee.userrole.exception;

import com.cee.userrole.entities.UserRoleEntity;
import com.cee.userrole.messages.GenericMessageBuilder;
import com.cee.userrole.messages.GenericResponseMessage;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResponseEntityExceptionHandler.class);
    static final String INTERNAL_ERROR = "Internal server error";
    static final String DUPLICATE_KEY_ERROR = "name: should be unique";

    @ExceptionHandler(value = { BadRequestException.class, ConstraintViolationException.class })
    protected ResponseEntity<GenericResponseMessage<UserRoleEntity>> handleBadRequest(Exception ex,
            WebRequest request) {
        logger.error(ex.getMessage(), ex);
        GenericMessageBuilder<UserRoleEntity> genericMessageBuilder = new GenericMessageBuilder<>();
        return genericMessageBuilder.setError(true).setMessage(ex.getMessage())
                .buildResponseEntity(HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(value = { DuplicateKeyException.class })
    protected ResponseEntity<GenericResponseMessage<UserRoleEntity>> hndleDuplicateKey(DuplicateKeyException ex,
            WebRequest request) {
        logger.error(ex.getMessage(), ex);
        GenericMessageBuilder<UserRoleEntity> genericMessageBuilder = new GenericMessageBuilder<>();
        return genericMessageBuilder.setError(true).setMessage(DUPLICATE_KEY_ERROR)
                .buildResponseEntity(HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<GenericResponseMessage<UserRoleEntity>> handleGenericException(Exception ex,
            WebRequest request) {
        logger.error(ex.getMessage(), ex);
        GenericMessageBuilder<UserRoleEntity> genericMessageBuilder = new GenericMessageBuilder<>();
        return genericMessageBuilder.setError(true).setMessage(INTERNAL_ERROR)
                .buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
