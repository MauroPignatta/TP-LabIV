package edu.labIV.exception;

public class NullTextException extends PostException {

    public NullTextException() {
            idError = 13;
            setError("Error " + idError + ": El texto es nulo.");
    }
}
