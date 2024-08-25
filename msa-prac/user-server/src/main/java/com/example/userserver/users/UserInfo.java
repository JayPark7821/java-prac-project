package com.example.userserver.users;

import java.time.ZonedDateTime;

public class UserInfo {
    private final int userId;
    private final String username;
    private final String email;
    private final ZonedDateTime lastUploadDatetime;

    public UserInfo(User user) {
        this(user.getUserId(), user.getUsername(), user.getEmail(), user.getLastPostDatetime());
    }

    public UserInfo(int userId, String username, String email, ZonedDateTime lastUploadDatetime) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.lastUploadDatetime = lastUploadDatetime;
    }

    public UserInfo(int userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.lastUploadDatetime = null;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
