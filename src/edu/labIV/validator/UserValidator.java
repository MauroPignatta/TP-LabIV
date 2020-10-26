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

    public void validateBirthDate(LocalDate birthdate) throws InvalidUserBirthDateException {
        String format = "yyyy-MM-dd";

        if(birthdate == null)
            throw new InvalidUserBirthDateException("null");

        if(birthdate.isAfter(LocalDate.now()))
            throw new InvalidUserBirthDateException(birthdate.toString());

        try {
            DateFormat df = new SimpleDateFormat(format);
            df.setLenient(false);
            df.parse(birthdate.toString());

        } catch (ParseException e) {
            throw new InvalidUserBirthDateException(birthdate.toString());
        }
    }

    public void validateLastName(String lastname) throws InvalidLastNameException {
        if(lastname == null)
            throw new InvalidLastNameException("null");
        if(validateString(lastname)){
            throw new InvalidLastNameException(lastname);
        }
    }

    public void validateName(String name) throws InvalidNameException {
        if(name == null)
            throw new InvalidNameException("null");

        if(validateString(name))
            throw new InvalidNameException(name);
    }

    private boolean validateString(String string) {
        boolean isValid = false;

        if (!Pattern.matches("^([a-zA-Z])+$", string))
            isValid = true;

        return isValid;
    }
}