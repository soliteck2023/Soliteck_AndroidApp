package com.example.api_call;

public class servicelist {

    private String serviceName;
    private String charges;

    public servicelist(String serviceName, String charges) {
        this.serviceName = serviceName;
        this.charges = charges;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }
}
