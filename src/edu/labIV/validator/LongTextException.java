package edu.labIV.validator;

import edu.labIV.entity.Post;
import edu.labIV.exception.PostException;

public class LongTextException extends PostException {
    public LongTextException(int length) {
        idError = 15;
        setError("Error " + idError + ": Se intento guardar una cadena de " +length+  " caracteres, " +
                "cuando el maximo es: " + Post.MAX_TEXT_LENGHT);
    }
}