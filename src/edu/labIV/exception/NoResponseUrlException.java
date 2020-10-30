package edu.labIV.exception;
;

public class NoResponseUrlException extends PostException {

    public NoResponseUrlException(String url) {
        idError = 11;
        setError("Error " + idError + ": No se obtuvo respuesta. URL: " + url);
    }
}
