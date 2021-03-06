package com.lozyukartem.exception;

import lombok.Data;

@Data
public class DaoException extends Exception {

    private DaoErrorCode code;
    private Object[] params;
    private String message;

    public DaoException(Throwable t, DaoErrorCode code, Object... params) {
        super(t);
        this.code = code;
        this.params = params;
        this.message = String.format(code.toString(), params);
    }

}
