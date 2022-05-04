package com.thn.calculator.storage;

public interface ChangeStorage {
    boolean find(String login);
    void save(String login, String newPass);
}
