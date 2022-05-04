package com.thn.calculator.storage;

public interface RegisterStorage {
    void save(String login, String name, String password);

    boolean find(String login);
}
