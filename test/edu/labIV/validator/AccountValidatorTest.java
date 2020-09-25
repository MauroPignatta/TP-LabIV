package edu.labIV.validator;

import edu.labIV.entity.Account;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class AccountValidatorTest {

    Account account = new Account("aB12345678", "mauroj.pignatta@gmail.com", false);
    AccountValidator accountValidator = new AccountValidator();

    @Test
    void validateAccountTrue() {
        assertTrue(accountValidator.validateAccount(account));
    }

    @Test
    void validateAccountFalse() {
        Account invalidAccount = new Account("1234","mailinvalido",false);
        assertFalse(accountValidator.validateAccount(invalidAccount));
    }

    @Test
    void validateEmail(){
        assertTrue(accountValidator.validateEmail(account.getEmail()));
    }

    @Test
    void validatePassword(){
        assertTrue(accountValidator.validatePass(account.getPassword()));
    }

}