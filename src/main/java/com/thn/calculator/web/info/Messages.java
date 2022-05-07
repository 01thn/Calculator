package com.thn.calculator.web.info;

public enum Messages {
    INPUT_DATA("Check your input data. Login and name gotta consist at least 3 char. Password at least 6 char and no special chars"),
    COLLISION("User with such login exists"),
    WRONG_PASS("Something went wrong. Try to reset password"),
    PASS_REQS("Password must consist at least 6 char and no special chars"),
    USER_NOT_FOUND("Looks like user does not exist"),
    CREATE_ACC("Create new account");

    private String text;

    Messages(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
