package com.example.api_call;

public class Operaterlistwise {
    String operatior_name;
    String image_url;

    public Operaterlistwise(String operatior_name, String image_url) {
        this.operatior_name = operatior_name;
        this.image_url = image_url;
    }

    public String getOperatior_name() {
        return operatior_name;
    }

    public void setOperatior_name(String operatior_name) {
        this.operatior_name = operatior_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}


