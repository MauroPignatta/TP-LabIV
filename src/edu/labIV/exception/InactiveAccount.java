package edu.labIV.exception;

public class InactiveAccount extends AccountException {

    public InactiveAccount() {
        idError = 5;
        setError("Error " + idError + ": La cuenta no esta activa");
    }
}
