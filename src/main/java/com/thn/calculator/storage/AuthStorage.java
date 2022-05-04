package com.thn.calculator.storage;

public interface AuthStorage {
    boolean isAuthenticated(String login, String password);
    Long getId(String login);
}
