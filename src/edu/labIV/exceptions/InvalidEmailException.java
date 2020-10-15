package edu.labIV.exceptions;

@SuppressWarnings("serial")
public class InvalidEmailException extends InvalidAccountException {

    public InvalidEmailException(String email) {
        super("Cuenta invalida: " + email + " no es un email valido.");
    }

}
