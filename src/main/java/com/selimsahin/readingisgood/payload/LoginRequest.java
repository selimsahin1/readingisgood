package com.selimsahin.readingisgood.payload;

import javax.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull(message = "asdasdasd")
    private String username;

    @NotNull(message = "sadasdasd")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
