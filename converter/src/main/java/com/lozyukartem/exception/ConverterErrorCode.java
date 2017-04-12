package com.lozyukartem.exception;

public enum ConverterErrorCode {
    SG_CONVERTER_000("Unable to convert entity to dto"),
    SG_CONVERTER_001("Unable to convert dto to entity"),
    SG_CONVERTER_002("Unable to convert dto list to entity list"),
    SG_CONVERTER_003("Unable to convert entity list to dto list");

    private final String value;

    ConverterErrorCode(String s) {
        value = s;
    }

    public boolean equalsValue(String value2) {
        return (value2 != null) && value.equals(value2);
    }

    public String toString() {
        return value;
    }
}
