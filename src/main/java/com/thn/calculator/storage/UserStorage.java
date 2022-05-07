package com.thn.calculator.storage;

public interface UserStorage {
    boolean isAuthenticated(String login, String password);

    Long getId(String login);

    boolean find(String login);

    void changePass(String login, String newPass);

    void save(String login, String name, String password);
}
