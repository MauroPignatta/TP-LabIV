package edu.labIV.exception;



public class InvalidUrlException extends PostException {

    public InvalidUrlException(String url) {
        idError = 12;
        setError("Error " + idError + ": La url esta mal formada. URL: " + url);
    }
}
