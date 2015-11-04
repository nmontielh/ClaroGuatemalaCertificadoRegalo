/*
 * Decompiled with CFR 0_102.
 */
package com.claro.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class LoadProperties {
    private static LoadProperties loadProperties;

    public static LoadProperties getInstance() {
        if (loadProperties == null) {
            loadProperties = new LoadProperties();
        }
        return loadProperties;
    }

    private LoadProperties() {
    }

    public String getPropertie(String propertie) {
        String fileProperties = "promociones.properties";
        Properties props = new Properties();
        try {
            props.load(LoadProperties.class.getResourceAsStream(fileProperties));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return props.getProperty(propertie);
    }
}

