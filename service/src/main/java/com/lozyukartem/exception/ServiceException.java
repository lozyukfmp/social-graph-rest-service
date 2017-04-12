package com.lozyukartem.exception;

import lombok.Data;

@Data
public class ServiceException extends Exception {

    private ServiceErrorCode code;
    private Object[] params;
    private String message;

    public ServiceException(Throwable t, ServiceErrorCode code, Object... params) {
        super(t);
        this.code = code;
        this.params = params;
        this.message = String.format(code.toString(), params);
    }

}
