package com.thn.calculator.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class UserStorage {
    private static UserStorage instance;
    private List<User> storage;

    private UserStorage() {
        this.storage = new ArrayList<>();
    }

    public static UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }

    public UserStorage addUser(User user){
        storage.add(user);
        return instance;
    }

    public boolean userExists(String login){
        return storage.stream()
                .map(User::getLogin)
                .anyMatch(user -> user.equals(login));
    }

    public boolean authUser(String login, String password){
        return storage.stream()
                .filter(user -> user.getLogin().equals(login))
                .anyMatch(user -> user.getPassword().equals(password));
    }

    @Override
    public String toString() {
        return "UserStorage{" +
                "storage=" + storage +
                '}';
    }
}
