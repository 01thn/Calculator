package com.thn.calculator.storage;

import com.thn.calculator.entity.UserStorage;

public class InMemoryAuthStorage implements AuthStorage {
    @Override
    public boolean isAuthenticated(String login, String password) {
        return UserStorage.getInstance().authUser(login, password);
    }
}
