/*
 * Decompiled with CFR 0_102.
 */
package com.claro.exception;

public class CAException
extends Exception {
    private static final long serialVersionUID = -8171021823861709766L;
    private int errorId;
    private String errorMessage;

    public CAException(int i, String s) {
        this.errorId = i;
        this.errorMessage = s;
    }

    public CAException(int i, String s, Exception e) {
        this.errorId = i;
        this.errorMessage = s;
        this.setStackTrace(e.getStackTrace());
    }

    public int getErrorId() {
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

