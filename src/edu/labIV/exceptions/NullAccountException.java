package edu.labIV.exceptions;

@SuppressWarnings("serial")
public class NullAccountException extends AccountException {

    public NullAccountException() {
        super();
    }

    public NullAccountException(String message){
        super(message);
    }
}
