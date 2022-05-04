package com.thn.calculator.service;

public class UserValidateService {
    public static boolean loginAndNameValidate(String login, String name) {
        return login.length() > 3 && name.length() > 3;
    }
    public static boolean passwordValidate(String password){
        String regex = "([a-zA-Z]|\\d){6,}";
        return !password.matches(regex);
    }
}
