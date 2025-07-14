package com.example.bibliogest.dto;

public class authresponse {

    private String token;
    private String rol;

    public authresponse(String token) {
        this.token = token;
    }

    public authresponse(String token, String rol) {
        this.token = token;
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
