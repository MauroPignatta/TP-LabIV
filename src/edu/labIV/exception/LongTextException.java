package edu.labIV.exception;

import edu.labIV.entity.Post;

public class LongTextException extends PostException {
    public LongTextException(int length) {
        idError = 15;
        setError("Error " + idError + ": Se intento guardar una cadena de " +length+  " caracteres, " +
                "cuando el maximo es: " + Post.MAX_TEXT_LENGTH);
    }
}