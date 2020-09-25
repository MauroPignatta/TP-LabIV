package edu.labIV.validator;

import edu.labIV.entity.Account;

import java.util.regex.Pattern;

public class AccountValidator {

    public boolean validateAccount(Account account){

        boolean valid = validatePass(account.getPassword());
        valid &= validateEmail(account.getEmail());
        return valid;
    }

    public boolean validatePass(String password){
        return password != null && Pattern.matches("\"^[a-zA-Z@#$%^&+=](?=.[0-9])(?=.[a-z])(?=.*[A-Z]).{8,}[a-zA-Z0-9]$\"\n", password);
    }

    public boolean validateEmail(String email){
        return email != null && Pattern.matches("^([a-zA-Z0-9-.]+)@([a-zA-Z0-9-.]+).([a-zA-Z]{2,5})$",email);
    }
}
