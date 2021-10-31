package model;

import controller.MainMenuController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static utilities.ClearConsole.newChapter;

public class Account {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;

    public Account(int id, String name, String surname, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public Account(String name, String surname, String email, String password) {
        this.id = 0;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean hasId() {
        if (getId() == 0) {
            return false;
        } else return true;
    }

    public String toString() {
        return id + ". " + name + " " + surname + ", email: " + email + ", password: " + password;
    }
}
