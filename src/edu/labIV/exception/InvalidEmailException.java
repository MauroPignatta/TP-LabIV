package edu.labIV.exception;

@SuppressWarnings("serial")
public class InvalidEmailException extends AccountException {

    public InvalidEmailException(String email) {
        idError = 2;
        setError("Error " + idError + ": Cuenta invalida: " + email + " no es un email valido.");
    }

}
