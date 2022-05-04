package com.thn.calculator.storage;

public interface OperationStorage {
    void save(long userId, double var1, double var2, String operation, double result);

}
