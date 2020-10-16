package edu.labIV.exception;

@SuppressWarnings("serial")
public class InvalidPasswordException extends AccountException {

    public InvalidPasswordException(String password) {
        idError = 3;
        setError("Error " + idError + ": Cuenta invalida: " + password + " no es una contraseña valida.");
    }
}
