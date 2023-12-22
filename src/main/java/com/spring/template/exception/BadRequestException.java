package com.spring.template.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zalando.problem.Status;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends DFException {
    public static java.net.URI DEFAULT_TYPE;
    public BadRequestException(String msg, String entityName, String errorKey) {
        super(Status.BAD_REQUEST, DEFAULT_TYPE, msg, entityName, errorKey);
    }
}
