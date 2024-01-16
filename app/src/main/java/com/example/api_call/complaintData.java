package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class complaintData {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("imagePath")
    @Expose
    private String imagePath;

    @SerializedName("closeBy")
    @Expose
    private String closeBy;

    @SerializedName("currentOwner")
    @Expose
    private String currentOwner;

    @SerializedName("createdDate")
    @Expose
    private String createdDate;

    @SerializedName("updatedDate")
    @Expose
    private String updatedDate;

    @SerializedName("complaintType")
    @Expose
    private String complaintType;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("conciliatorComment")
    @Expose
    private String conciliatorComment;

    public complaintData(String id, String description, String imagePath, String closeBy, String currentOwner, String createdDate, String updatedDate, String complaintType, String status, String conciliatorComment) {
        this.id = id;
        this.description = description;
        this.imagePath = imagePath;
        this.closeBy = closeBy;
        this.currentOwner = currentOwner;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.complaintType = complaintType;
        this.status = status;
        this.conciliatorComment = conciliatorComment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCloseBy() {
        return closeBy;
    }

    public void setCloseBy(String closeBy) {
        this.closeBy = closeBy;
    }

    public String getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(String currentOwner) {
        this.currentOwner = currentOwner;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConciliatorComment() {
        return conciliatorComment;
    }

    public void setConciliatorComment(String conciliatorComment) {
        this.conciliatorComment = conciliatorComment;
    }
}
