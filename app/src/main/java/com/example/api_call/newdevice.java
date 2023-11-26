package com.example.api_call;

public class newdevice {
    String UserName;
    String Password;
    String Devide_id;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDevide_id() {
        return Devide_id;
    }

    public void setDevide_id(String devide_id) {
        Devide_id = devide_id;
    }

    public newdevice(String userName, String password, String devide_id) {
        UserName = userName;
        Password = password;
        Devide_id = devide_id;
    }
}
