package edu.labIV.exceptions;

public class InvalidPasswordException extends InvalidAccountException {

    public InvalidPasswordException(String password) {
        super("Cuenta invalida: " + password + " no es una contraseña valida.");
    }
}
