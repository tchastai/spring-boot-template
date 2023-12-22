package com.spring.template.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class DFException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;
    private final String entityName;
    public static java.net.URI DEFAULT_TYPE;
    private final String errorKey;

    public DFException(String defaultMessage, String entityName, String errorKey) {
        this(DEFAULT_TYPE, defaultMessage, entityName, errorKey);
    }

    public DFException(URI type, String defaultMessage, String entityName, String errorKey) {
        super(type, defaultMessage, Status.BAD_REQUEST, (String)null, (URI)null, (ThrowableProblem)null, getAlertParameters(defaultMessage));
        this.entityName = entityName;
        this.errorKey = errorKey;
    }

    public DFException(Status status, URI type, String defaultMessage, String entityName, String errorKey) {
        super(type, defaultMessage, status, (String)null, (URI)null, (ThrowableProblem)null, getAlertParameters(defaultMessage));
        this.entityName = entityName;
        this.errorKey = errorKey;
    }

    public DFException(Status status, URI type, String defaultMessage, String entityName, String errorKey, String title) {
        super(type, title, status, (String)null, (URI)null, (ThrowableProblem)null, getAlertParameters(defaultMessage));
        this.entityName = entityName;
        this.errorKey = errorKey;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public String getErrorKey() {
        return this.errorKey;
    }

    private static Map<String, Object> getAlertParameters(String defaultMessage) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("message", defaultMessage);
        return parameters;
    }
}
