package com.xxx.springboot05webmvc.dto;

public class LoginDto {
    private String username;
    private String password;
    private Long timestamp;

    public LoginDto() {
    }

    public LoginDto(String username, String password, Long timestamp) {
        this.username = username;
        this.password = password;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
