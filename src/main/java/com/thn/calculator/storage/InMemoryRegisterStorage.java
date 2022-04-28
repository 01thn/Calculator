package com.thn.calculator.storage;

import com.thn.calculator.entity.User;
import com.thn.calculator.entity.UserStorage;

public class InMemoryRegisterStorage implements RegisterStorage {

    public InMemoryRegisterStorage() {
    }

    @Override
    public void save(String login, String name, String password) {
        UserStorage.getInstance().addUser(new User(login, name, password));
    }

    @Override
    public boolean find(String login) {
        return UserStorage.getInstance().userExists(login);
    }
}
