package com.thn.calculator.storage;

import com.thn.calculator.entity.Operation;

import java.util.List;

public interface OperationStorage {
    void save(long userId, double var1, double var2, String operation, double result);

    List<Operation> getStory(long userId);

}
