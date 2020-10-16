package edu.labIV.exception;

@SuppressWarnings("serial")
public class ExistingAccountException extends AccountException{

    public ExistingAccountException(String mail) {
        idError = 1;
        setError("Error "+ idError +": Ya existe una cuenta vinculada a " + mail);
    }
}
