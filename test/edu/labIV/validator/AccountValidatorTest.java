package edu.labIV.validator;

import edu.labIV.entity.Account;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class AccountValidatorTest {

    Account account = new Account("Mariano", "adssdHDS1231@#&2", "mariano@kaimakamian.com", false);

    @Test
    void validateAccount() {

        AccountValidator accountValidator = new AccountValidator();
        assertTrue(accountValidator.validateAccount(account));

    }

    @Test
    void validateEmail(){
        boolean b = Pattern.matches("^([a-zA-Z0-9-.]+)@([a-zA-Z0-9-.]+).([a-zA-Z]{2,5})$", account.getEmail());
        assertTrue(b);
    }

    @Test
    void validatePassword(){
        boolean b = Pattern.matches("^[a-zA-Z@#$%^&+=](?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}[a-zA-Z0-9]$", account.getPassword());
        assertTrue(b);
    }

    @Test
    void validateUsername(){
        boolean b = Pattern.matches("^([a-zA-Z0-9]+){4,}$", account.getUsername());
        assertTrue(b);
    }
}