package com.example.userserver.users;

public class UserRequest {
    private String username;
    private String email;
    private String plainPassword;

    public UserRequest(String username, String email, String plainPassword) {
        this.username = username;
        this.email = email;
        this.plainPassword = plainPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPlainPassword() {
        return plainPassword;
    }
}
