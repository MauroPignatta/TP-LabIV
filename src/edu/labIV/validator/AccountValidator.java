package edu.labIV.validator;

import edu.labIV.entity.Account;
import edu.labIV.exception.*;
import java.util.regex.Pattern;

public class AccountValidator {

    public void validateAccount(Account account) throws AccountException {
        if(account == null)
            throw new NullAccountException();
        validatePass(account.getPassword());
        validateEmail(account.getEmail());
    }

    public void validateExistingAccount(Account account) throws ExistingAccountException {
        if (account != null)
            throw new ExistingAccountException(account.getEmail());
    }

    public void validatePass(String password) throws InvalidPasswordException {
        if (password == null)
            throw new NullPointerException();

        if (!Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$", password))
            throw new InvalidPasswordException(password);
    }

    public void validateEmail(String email) throws InvalidEmailException{
        if (email == null)
            throw new NullPointerException();

        if (!Pattern.matches("^([a-zA-Z0-9-.]+)@([a-zA-Z0-9-.]+).([a-zA-Z]{2,5})$",email))
            throw new InvalidEmailException(email);
    }

    public void validateIsActive(Account account) throws InactiveAccount {
        if(!account.isActive()){
            throw new InactiveAccount();
        }
    }

    public void validateCorrectPassword(String password, String password1) throws WrongPasswordExcepcion {
        if(!password.equals(password1)){
            throw new WrongPasswordExcepcion();
        }
    }
}
