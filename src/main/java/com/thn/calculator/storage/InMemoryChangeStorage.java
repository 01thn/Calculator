package com.thn.calculator.storage;

import com.thn.calculator.entity.UserStorage;

public class InMemoryChangeStorage implements ChangeStorage{
    @Override
    public boolean find(String login) {
        return UserStorage.getInstance().userExists(login);
    }

    @Override
    public void save(String login, String newPass) {

    }
}
