package edu.labIV.exception;

public class InvalidUserBirthDateException extends UserException{

    public InvalidUserBirthDateException(String birthDate){
        idError = 9;
        setError("Error " + idError + " :" + birthDate + " no es una fecha valida.");
    }
}
