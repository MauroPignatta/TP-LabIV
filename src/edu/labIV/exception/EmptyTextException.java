package edu.labIV.exception;

public class EmptyTextException extends PostException {

    public EmptyTextException() {
        idError = 14;
        setError("Error " + idError + ": El texto esta vacio.");
    }
}
