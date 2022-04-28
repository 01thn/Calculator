package com.thn.calculator.storage;

public interface RegisterStorage {
    void save(String login, String name, String password);

    default boolean isValid(String login, String name, String password) {
        String regex = "([a-zA-Z]|\\d){6,}";
        return login.length() > 3 && name.length() > 3 && password.matches(regex);
    }

    boolean find(String login);
}
