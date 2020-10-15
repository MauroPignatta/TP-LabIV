package edu.labIV.exceptions;

@SuppressWarnings("serial")
public class ExistingAccountException extends AccountException{

    public ExistingAccountException(String mail) {
        super("Ya existe una cuenta vinculada a " + mail);
    }
}
