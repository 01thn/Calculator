package com.thn.calculator.entity;

public class Operation {
    long id;
    long userId;
    double var1;
    double var2;
    String operation;
    double result;

    public Operation(long id, long userId, double var1, double var2, String operation, double result) {
        this.id = id;
        this.userId = userId;
        this.var1 = var1;
        this.var2 = var2;
        this.operation = operation;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public double getVar1() {
        return var1;
    }

    public double getVar2() {
        return var2;
    }

    public String getOperation() {
        return operation;
    }

    public double getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", userId=" + userId +
                ", var1=" + var1 +
                ", var2=" + var2 +
                ", operation='" + operation + '\'' +
                ", result=" + result +
                '}';
    }
}
