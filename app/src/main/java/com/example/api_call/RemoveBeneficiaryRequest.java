package com.example.api_call;

public class RemoveBeneficiaryRequest {
    private String senderId;
    private String recipientId;
    private String deviceId;
    private String token;

    public RemoveBeneficiaryRequest() {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.deviceId = deviceId;
        this.token = token;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
