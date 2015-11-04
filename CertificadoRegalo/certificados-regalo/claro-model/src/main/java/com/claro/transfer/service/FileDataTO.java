/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.service;

import java.io.InputStream;
import java.io.Serializable;

public class FileDataTO
implements Serializable {
    private static final long serialVersionUID = -8568305888184280851L;
    private InputStream data;
    private int size;
    private String name;

    public FileDataTO() {
    }

    public FileDataTO(InputStream data, int size) {
        this.data = data;
        this.size = size;
    }

    public InputStream getData() {
        return this.data;
    }

    public void setData(InputStream data) {
        this.data = data;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static long getSerialVersionUID() {
        return -8568305888184280851L;
    }
}

