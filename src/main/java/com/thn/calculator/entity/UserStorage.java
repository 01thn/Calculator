package com.thn.calculator.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// TODO transform into singleton

public class UserStorage {
    private Map<Long, ArrayList<Integer>> storage;

    public UserStorage() {
        storage = new HashMap<>();
    }

    public void addUser(User user) {
        storage.put(user.getId(), new ArrayList<Integer>());
    }

    @Override
    public String toString() {
        return "UserStorage{" +
                "storage=" + storage +
                '}';
    }
}
