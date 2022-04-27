package com.thn.calculator.entity;

public class User {
    private static Long counter = 0L;
    private Long id;
    private String login;
    private String name;
    private String password;

    public User(String login, String name, String password) {
        counter++;
        this.id = counter;
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
