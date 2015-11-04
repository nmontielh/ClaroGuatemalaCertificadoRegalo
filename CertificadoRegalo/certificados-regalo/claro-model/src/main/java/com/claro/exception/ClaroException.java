/*
 * Decompiled with CFR 0_102.
 */
package com.claro.exception;

public class ClaroException
extends Exception {
    private static final long serialVersionUID = 2856040899762181825L;
    private String errorId;
    private String errorMessage;

    public ClaroException(String errorId, String s) {
        this.errorId = errorId;
        this.errorMessage = s;
    }

    public ClaroException(String errorId, String s, Exception e) {
        this.errorId = errorId;
        this.errorMessage = s;
        this.setStackTrace(e.getStackTrace());
    }

    public String getErrorId() {
        return this.errorId;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public String toString() {
        return "( " + this.errorId + "-" + this.errorMessage + " )";
    }
}

