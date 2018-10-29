package com.bellalhrlux.retrofitpost;

public class Users {
    private String username;
    private String password;
    private int remainder;
    private String url_redirect;
    public Users()
    {

    }

    public Users(String username, String password, int remainder, String url_redirect) {
        this.username = username;
        this.password = password;
        this.remainder = remainder;
        this.url_redirect = url_redirect;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRemainder() {
        return remainder;
    }

    public String getUrl_redirect() {
        return url_redirect;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRemainder(int remainder) {
        this.remainder = remainder;
    }

    public void setUrl_redirect(String url_redirect) {
        this.url_redirect = url_redirect;
    }
}
