package com.thn.calculator.storage;

import com.thn.calculator.entity.User;

public class InMemoryUserStorage implements UserStorage {
    @Override
    public boolean isAuthenticated(String login, String password) {
        return com.thn.calculator.entity.UserStorage.getInstance().authUser(login, password);
    }

    @Override
    public Long getId(String login) {
        return null;
    }

    @Override
    public boolean find(String login) {
        return com.thn.calculator.entity.UserStorage.getInstance().userExists(login);
    }

    @Override
    public void changePass(String login, String newPass) {

    }

    @Override
    public void save(String login, String name, String password) {
        com.thn.calculator.entity.UserStorage.getInstance().addUser(new User(login, name, password));
    }
}
