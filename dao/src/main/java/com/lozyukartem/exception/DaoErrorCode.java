package com.lozyukartem.exception;

import lombok.Data;

public enum DaoErrorCode {
    SG_DAO_000("Unable to get by id"),
    SG_DAO_001("Unable to get list of object"),
    SG_DAO_002("Unable to add object"),
    SG_DAO_003("Unable to update object"),
    SG_DAO_004("Unable to delete object"),
    SG_DAO_005("Unable to create hql");
    private final String value;

    DaoErrorCode(String s) {
        value = s;
    }

    public boolean equalsValue(String value2) {
        return (value2 != null) && value.equals(value2);
    }

    public String toString() {
        return value;
    }
}
