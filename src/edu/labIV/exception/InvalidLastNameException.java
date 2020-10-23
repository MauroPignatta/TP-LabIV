package edu.labIV.exception;

public class InvalidLastNameException extends UserException {

    public InvalidLastNameException(String lastName){
        idError = 8;
        setError("Error " + idError + " :" + lastName + " no es un apellido valido.");
    }
}
