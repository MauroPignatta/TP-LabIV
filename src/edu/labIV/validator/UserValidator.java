package edu.labIV.validator;

import edu.labIV.entity.User;
import edu.labIV.exception.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class UserValidator {

    public void validateUser(User user) throws UserException {
        if(user == null)
            throw new NullPointerException();
        validateName(user.getName());
        validateLastName(user.getLastname());
        validateBirthDate(user.getBirthdate());
    }

    private void validateBirthDate(LocalDate birthdate) throws InvalidUserBirthDateException {
        String format = "YYYY-MM-DD";
        try {
            DateFormat df = new SimpleDateFormat(format);
            df.setLenient(false);
            df.parse(birthdate.toString());

        } catch (ParseException e) {
            throw new InvalidUserBirthDateException(birthdate.toString());
        }
    }

    private void validateLastName(String lastname) throws InvalidLastNameException {
        if(validateString(lastname)){
            throw new InvalidLastNameException(lastname);
        }
    }

    private boolean validateString(String string) {
        boolean isValid = false;
        if(string == null)
            throw new NullPointerException();

        if (!Pattern.matches("^([a-zA-Z])+$", string))
            isValid = true;

        return isValid;
    }

    private void validateName(String name) throws InvalidNameException {

        if(validateString(name)){
            throw new InvalidNameException(name);
        }
    }
}
