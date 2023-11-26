package com.example.pdv.model;

public class Cadastro {

    private String login;

    private int senha;

    public Cadastro() {
    }

    public Cadastro (String login, int senha){
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }
}
