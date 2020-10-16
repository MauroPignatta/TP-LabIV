package edu.labIV.exception;

public class WrongPasswordExcepcion extends AccountException {

    public WrongPasswordExcepcion() {
        idError = 6;
        setError("Error "+ idError +": Contraseña incorrecta");
    }
}
