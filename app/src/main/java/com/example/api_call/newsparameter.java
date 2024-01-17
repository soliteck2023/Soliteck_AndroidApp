package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  newsparameter {
    @SerializedName("NewsId")
    @Expose
    private String NewsId;

    @SerializedName("Title")
    @Expose
    private String Title;

    @SerializedName("Discription")
    @Expose
    private String Discription;
    @SerializedName("NewsPath")
    @Expose
    private String NewsPath;
    @SerializedName("IsActive")
    @Expose
    private boolean IsActive;
    @SerializedName("Role")
    @Expose
    private String Role;
    @SerializedName("UserName")
    @Expose
    private String UserName;
    @SerializedName("UserId")
    @Expose
    private String UserId;
    @SerializedName("Users")
    @Expose
    private String Users;

    @SerializedName("RoleId")
    @Expose
    private String RoleId;
    @SerializedName("Id")
    @Expose
    private int Id;

    @SerializedName("Type")
    @Expose
    private String Type;

    public newsparameter(String newsId, String title, String discription, String newsPath, boolean isActive, String role, String userName, String userId, String users, String roleId, int id, String type) {
        NewsId = newsId;
        Title = title;
        Discription = discription;
        NewsPath = newsPath;
        IsActive = isActive;
        Role = role;
        UserName = userName;
        UserId = userId;
        Users = users;
        RoleId = roleId;
        Id = id;
        Type = type;
    }

    public String getNewsId() {
        return NewsId;
    }

    public void setNewsId(String newsId) {
        NewsId = newsId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    public String getNewsPath() {
        return NewsPath;
    }

    public void setNewsPath(String newsPath) {
        NewsPath = newsPath;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUsers() {
        return Users;
    }

    public void setUsers(String users) {
        Users = users;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
