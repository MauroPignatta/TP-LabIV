package edu.labIV.entity;

public class Account {

    private String password;
    private String email;
    private boolean active;


    public Account(String email, String password, boolean active) {
        this.password = password;
        this.email = email;
        this.active = active;
    }

    public Account(){

    }

    public boolean compare(Account account){
        boolean b = false;
        b = account.getPassword().equals(this.password);
        b &= account.getEmail().equals(this.email);
        b &= account.isActive() == this.active;
        return b;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }
}
