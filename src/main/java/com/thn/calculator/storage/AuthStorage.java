package com.thn.calculator.storage;

public interface AuthStorage {
    boolean isAuthenticated(String login, String password);
}
