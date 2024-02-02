package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class  newsparameter {
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
    private boolean isActive;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("Users")
    @Expose
    private String Users;

    @SerializedName("RoleId")
    @Expose
    private String RoleId;
    @SerializedName("users")
    @Expose
    private String users;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("roleId")
    @Expose
    private String roleId;
    @SerializedName("type")
    @Expose
    private List<type> type;

    public newsparameter(String newsId, String title, String discription, String newsPath, boolean isActive, String role, String userName, String userId, String users, String roleId, String users1, int id, String roleId1, List<com.example.api_call.type> type) {
        this.newsId = newsId;
        this.title = title;
        this.discription = discription;
        this.newsPath = newsPath;
        this.isActive = isActive;
        this.role = role;
        this.userName = userName;
        this.userId = userId;
        Users = users;
        RoleId = roleId;
        this.users = users1;
        this.id = id;
        this.roleId = roleId1;
        this.type = type;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getUsers() {
        return Users;
    }

    public void setUsers(String users) {
        Users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public List<com.example.api_call.type> getType() {
        return type;
    }

    public void setType(List<com.example.api_call.type> type) {
        this.type = type;
    }
}
