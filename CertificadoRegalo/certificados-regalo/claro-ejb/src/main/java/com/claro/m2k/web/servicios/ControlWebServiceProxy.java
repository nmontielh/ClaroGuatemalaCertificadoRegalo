/*
 * Decompiled with CFR 0_102.
 */
package com.claro.m2k.web.servicios;

import com.claro.m2k.web.servicios.ControlWebService;
import com.claro.m2k.web.servicios.ControlWebServiceService;
import java.net.URL;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

public class ControlWebServiceProxy {
    protected Descriptor _descriptor;

    public ControlWebServiceProxy() {
        this._descriptor = new Descriptor(this);
    }

    public ControlWebServiceProxy(URL wsdlLocation, QName serviceName) {
        this._descriptor = new Descriptor(this, wsdlLocation, serviceName);
    }

    public Descriptor _getDescriptor() {
        return this._descriptor;
    }

    public String ejecutaServicio(String xml) {
        return this._getDescriptor().getProxy().ejecutaServicio(xml);
    }

    public class Descriptor {
        private ControlWebServiceService _service;
        private ControlWebService _proxy;
        private Dispatch<Source> _dispatch;
        final /* synthetic */ ControlWebServiceProxy this$0;

        public Descriptor(ControlWebServiceProxy controlWebServiceProxy) {
            this.this$0 = controlWebServiceProxy;
            this._service = null;
            this._proxy = null;
            this._dispatch = null;
            this.init();
        }

        public Descriptor(ControlWebServiceProxy controlWebServiceProxy, URL wsdlLocation, QName serviceName) {
            this.this$0 = controlWebServiceProxy;
            this._service = null;
            this._proxy = null;
            this._dispatch = null;
            this._service = new ControlWebServiceService(wsdlLocation, serviceName);
            this.initCommon();
        }

        public void init() {
            this._service = null;
            this._proxy = null;
            this._dispatch = null;
            this._service = new ControlWebServiceService();
            this.initCommon();
        }

        private void initCommon() {
            this._proxy = this._service.getControlWebService();
        }

        public ControlWebService getProxy() {
            return this._proxy;
        }

        public Dispatch<Source> getDispatch() {
            if (this._dispatch == null) {
                QName portQName = new QName("http://servicios.web.m2k.sds.telcel.com", "ControlWebService");
                this._dispatch = this._service.createDispatch(portQName, Source.class, Service.Mode.MESSAGE);
                String proxyEndpointUrl = this.getEndpoint();
                Dispatch<Source> bp = this._dispatch;
                String dispatchEndpointUrl = (String)bp.getRequestContext().get("javax.xml.ws.service.endpoint.address");
                if (!dispatchEndpointUrl.equals(proxyEndpointUrl)) {
                    bp.getRequestContext().put("javax.xml.ws.service.endpoint.address", proxyEndpointUrl);
                }
            }
            return this._dispatch;
        }

        public String getEndpoint() {
            BindingProvider bp = (BindingProvider)this._proxy;
            return (String)bp.getRequestContext().get("javax.xml.ws.service.endpoint.address");
        }

        public void setEndpoint(String endpointUrl) {
            Dispatch<Source> bp = (Dispatch<Source>)this._proxy;
            bp.getRequestContext().put("javax.xml.ws.service.endpoint.address", endpointUrl);
            if (this._dispatch != null) {
                bp = this._dispatch;
                bp.getRequestContext().put("javax.xml.ws.service.endpoint.address", endpointUrl);
            }
        }
    }

}

