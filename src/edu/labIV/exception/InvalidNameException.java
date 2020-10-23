package edu.labIV.exception;

public class InvalidNameException extends UserException{

    public InvalidNameException(String name){
        idError = 7;
        setError("Error " + idError + " :" + name + " no es un nombre valido.");
    }
}
