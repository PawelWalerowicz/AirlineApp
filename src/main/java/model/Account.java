package model;

import controller.MainMenuController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

import static utilities.ClearConsole.newChapter;

public class Account {
    private static final String USER_DATABASE = "f:\\java\\AirlineApp\\src\\main\\resources\\UserDatabase.txt";
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
        if(getId()==0) {
            return false;
        } else return true;
    }

    public static Account getAccountFromDatabase(String email, String password) {
        MainMenuController mainMenuController = new MainMenuController();
        Account account = null;
        Scanner scanner = null;
        boolean accountFound=false;
        boolean passwordCorrect=true;
        try {
            scanner = new Scanner(new FileInputStream(USER_DATABASE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            String user = scanner.nextLine().toString();
            user = user.replace(",","");
            String[] userAttributes = user.split(" ");
            if (userAttributes[4].toLowerCase().equals(email.toLowerCase()) && userAttributes[6].equals(password)) {
                accountFound=true;
                passwordCorrect=true;
                account = new Account(Integer.parseInt(userAttributes[0].substring(0,1)), userAttributes[1], userAttributes[2], userAttributes[3], userAttributes[4]);
            } else if(userAttributes[4].equals(email) && !userAttributes[6].equals(password)) {
                accountFound=true;
                passwordCorrect=false;
            }
        }
        if(!accountFound) {
            System.out.println("Account not found.");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newChapter();
            mainMenuController.showMenu();
        }
        if(accountFound && !passwordCorrect) {
            System.out.println("Incorrect password.");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newChapter();
            mainMenuController.showMenu();
        }
        return account;
    }

    public static void editAccountInDatabase() {

    }


    public String toString() {
        return id + ". " + name + " " + surname + ", email: " + email + ", password: " + password;
    }
}
