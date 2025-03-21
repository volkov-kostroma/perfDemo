package models;

import lombok.Getter;

@Getter

public class User {
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "{\"login\": \"" + login + "\", \"password\": \"" + password + "\"}";
    }
}

