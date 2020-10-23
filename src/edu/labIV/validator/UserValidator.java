package edu.labIV.validator;

import edu.labIV.entity.User;
import java.util.regex.Pattern;

public class AccountValidator {

	

    public void validateCorrectPassword(String password, String password1) throws WrongPasswordExcepcion {
        if(!password.equals(password1)){
            throw new WrongPasswordExcepcion();
        }
    }
}
