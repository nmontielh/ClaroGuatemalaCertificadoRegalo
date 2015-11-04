/*
 * Decompiled with CFR 0_102.
 */
package com.claro.m2k.web.servicios;

import com.claro.m2k.web.servicios.ControlWebService;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name="ControlWebServiceService", targetNamespace="http://servicios.web.m2k.sds.telcel.com", wsdlLocation="META-INF/wsdl/ControlWebService.wsdl")
public class ControlWebServiceService
extends Service {
    private static final URL CONTROLWEBSERVICESERVICE_WSDL_LOCATION;
    private static final Logger logger;

    static {
        logger = Logger.getLogger(ControlWebServiceService.class.getName());
        URL url = null;
        try {
            url = ControlWebServiceService.class.getResource("/META-INF/wsdl/ControlWebService.wsdl");
            if (url == null) {
                throw new MalformedURLException("/META-INF/wsdl/ControlWebService.wsdl does not exist in the module.");
            }
        }
        catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'META-INF/wsdl/ControlWebService.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        CONTROLWEBSERVICESERVICE_WSDL_LOCATION = url;
    }

    public ControlWebServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ControlWebServiceService() {
        super(CONTROLWEBSERVICESERVICE_WSDL_LOCATION, new QName("http://servicios.web.m2k.sds.telcel.com", "ControlWebServiceService"));
    }

    @WebEndpoint(name="ControlWebService")
    public ControlWebService getControlWebService() {
        return (ControlWebService)super.getPort(new QName("http://servicios.web.m2k.sds.telcel.com", "ControlWebService"), ControlWebService.class);
    }

    @WebEndpoint(name="ControlWebService")
    public /* varargs */ ControlWebService getControlWebService(WebServiceFeature ... features) {
        return (ControlWebService)super.getPort(new QName("http://servicios.web.m2k.sds.telcel.com", "ControlWebService"), ControlWebService.class, features);
    }
}

