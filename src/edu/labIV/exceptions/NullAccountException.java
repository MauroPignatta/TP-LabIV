package edu.labIV.exceptions;

public class NullAccountException extends AccountException {

    public NullAccountException() {
        super();
    }

    public NullAccountException(String message){
        super(message);
    }
}
