package com.example.robertoluizveigajunior.cartoys.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
/**
 * Created by robertoluizveigajunior on 18/03/17.
 */

public class User implements Serializable {
    @SerializedName("usuario")
    private String login;

    @SerializedName("senha")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
