package com.example.postserver.posts.uploader;

/**
 * UploaderInfo
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/25/24
 */
public class UploaderInfo {
    private final int userId;
    private final String username;
    private final String email;

    public UploaderInfo(int userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
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
