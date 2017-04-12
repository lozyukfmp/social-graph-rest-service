package com.lozyukartem.exception;

import lombok.Data;

@Data
public class ConverterException extends Exception {
    private ConverterErrorCode code;
    private Object[] params;
    private String message;

    public ConverterException(Throwable t, ConverterErrorCode code, Object... params) {
        super(t);
        this.code = code;
        this.params = params;
        this.message = String.format(code.toString(), params);
    }
}
