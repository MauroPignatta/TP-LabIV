package edu.labIV.exceptions;

@SuppressWarnings("serial")
public class InvalidAccountException extends AccountException{

    public InvalidAccountException(String s) {
        super(s);
    }
}
