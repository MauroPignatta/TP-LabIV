package edu.labIV.exception;

@SuppressWarnings("serial")
public class NullAccountException extends AccountException {

    public NullAccountException() {
        idError = 4;
        setError("Error " + idError + ": La cuenta es nula");
    }
}
