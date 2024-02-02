package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class type {
    @SerializedName("newsId")
    @Expose
    private String newsId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("discription")
    @Expose
    private String discription;
    @SerializedName("newsPath")
    @Expose
    private String newsPath;
    @SerializedName("isActive")
    @Expose
    private String isActive;

    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("users")
    @Expose
    private String users;
    @SerializedName("roleId")
    @Expose
    private String roleId;
    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("id")
    @Expose
    private int id;

    public type(String newsId, String title, String discription, String newsPath, String isActive, String userName, String userId, String role, String users, String roleId, String type, int id) {
        this.newsId = newsId;
        this.title = title;
        this.discription = discription;
        this.newsPath = newsPath;
        this.isActive = isActive;
        this.userName = userName;
        this.userId = userId;
        this.role = role;
        this.users = users;
        this.roleId = roleId;
        this.type = type;
        this.id = id;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getNewsPath() {
        return newsPath;
    }

    public void setNewsPath(String newsPath) {
        this.newsPath = newsPath;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
