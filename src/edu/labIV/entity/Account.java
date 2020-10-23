package edu.labIV.entity;

public class Account {

    private int id;
    private String password;
    private String email;
    private boolean active;
    private int availableTries;

    public static final int TRIES = 5;


    public Account(String email, String password, boolean active, int availableTries) {
        this.password = password;
        this.email = email;
        this.active = active;
        this.availableTries = availableTries;
    }

    public Account(){

    }

    public boolean compare(Account account){
        //TODO eliminar por trigger en mapper/dao
        boolean isSameAccount = false;
        isSameAccount = account.getPassword().equals(this.password);
        isSameAccount &= account.getEmail().equals(this.email);
        isSameAccount &= account.isActive() == this.active;
        isSameAccount &= account.getAvailableTries() == this.availableTries;
        return isSameAccount;
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

    public int getAvailableTries() {
        return availableTries;
    }

    public void setAvailableTries(int availableTries) {
        this.availableTries = availableTries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
