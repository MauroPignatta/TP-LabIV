package edu.labIV.exception;

public class NullPostException extends PostException {

    public NullPostException() {
        idError = 10;
        setError("Error " + idError + ": El post es nulo.");
    }
}
