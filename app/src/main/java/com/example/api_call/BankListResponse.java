package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BankListResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("ifsc")
    @Expose
    private String ifsc;

    @SerializedName("branch")
    @Expose
    private String branch;

    @SerializedName("accountName")
    @Expose
    private String accountName;

    @SerializedName("serviceId")
    @Expose
    private String serviceId;

    @SerializedName("serviceName")
    @Expose
    private String serviceName;

    @SerializedName("charges")
    @Expose
    private String charges;

    private Map<String, String> services;

    public BankListResponse(Integer id, String bankName, String ifsc, String branch, String accountName, String serviceId, String serviceName, String charges) {
        this.id = id;
        this.bankName = bankName;
        this.ifsc = ifsc;
        this.branch = branch;
        this.accountName = accountName;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.charges = charges;
        this.services = new HashMap<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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

    public Map<String, String> getServices() {
        return services;
    }

    public void addService(String serviceName, String charge) {
        services.put(serviceName, charge);
    }

    public static List<BankListResponse> createModifiedList(List<BankListResponse> originalList) {
        List<BankListResponse> modifiedList = new ArrayList<>();

        // Group the objects by BankName
        Map<String, List<BankListResponse>> groupedByBankName = originalList.stream()
                .collect(Collectors.groupingBy(BankListResponse::getBankName));

        // Create modified objects with common BankName and added services
        for (Map.Entry<String, List<BankListResponse>> entry : groupedByBankName.entrySet()) {
            String bankName = entry.getKey();
            List<BankListResponse> bankResponses = entry.getValue();

            // Create a modified response with the fields from the first item in the group
            BankListResponse modifiedResponse = new BankListResponse(
                    bankResponses.get(0).getId(),
                    bankResponses.get(0).getBankName(),
                    bankResponses.get(0).getIfsc(),
                    bankResponses.get(0).getBranch(),
                    bankResponses.get(0).getAccountName(),
                    bankResponses.get(0).getServiceId(),
                    bankResponses.get(0).getServiceName(),
                    bankResponses.get(0).getCharges()
            );

            // Add services from all items in the group
            for (BankListResponse originalResponse : bankResponses) {
                modifiedResponse.addService(originalResponse.getServiceName(), originalResponse.getCharges());
            }

            modifiedList.add(modifiedResponse);
        }

        return modifiedList;
    }
}
