package edu.labIV.entity;

import java.time.LocalDate;

public class User {

    private int id;
    private String name;
    private String lastname;
    private LocalDate birthdate;
    private String status;

    public User() {
    }

    public User(String name, String lastname, LocalDate birthdate) {
        this.name = name;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.status = UserStatus.OFFLINE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
