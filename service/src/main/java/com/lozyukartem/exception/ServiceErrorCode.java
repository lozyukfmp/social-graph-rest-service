package com.lozyukartem.exception;

public enum ServiceErrorCode {
    SG_SERVICE_000("Unable to get all objects"),
    SG_SERVICE_001("Unable to get object by id"),
    SG_SERVICE_002("Unable to save object"),
    SG_SERVICE_003("Unable to update object"),
    SG_SERVICE_004("Unable to delete object");

    private final String value;

    ServiceErrorCode(String s) {
        value = s;
    }
    public boolean equalsValue(String value2) {
        return (value2 != null) && value.equals(value2);
    }
    public String toString() {
        return value;
    }
}
