package com.example.api_call;

public class Userdeatails {
    String UserName;
    String Mobile_no;

    public Userdeatails(String userName, String mobile_no) {
        UserName = userName;
        Mobile_no = mobile_no;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMobile_no() {
        return Mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        Mobile_no = mobile_no;
    }
}
