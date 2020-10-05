package edu.labIV.exceptions;

public class InvalidEmailException extends InvalidAccountException {

    public InvalidEmailException(String email) {
        super("Cuenta invalida: " + email + " no es un email valido.");
    }

}
