package controller;

import controller.MainMenuController;
import model.Account;

import java.io.*;
import java.util.Scanner;

import static utilities.ClearConsole.newChapter;

public class AccountDatabaseController {
    private static final String USER_DATABASE = "f:\\java\\AirlineApp\\src\\main\\resources\\UserDatabase.txt";

    public static void saveAccountToDatabase(Account account) {
        account.setId(getNumberOfAccountsInDatabase() + 1);
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("f:\\java\\AirlineApp\\src\\main\\resources\\UserDatabase.txt", true));
            writer.println(account.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getNumberOfAccountsInDatabase() {
        Scanner scanner = null;
        int accountsInDatabase = 0;
        try {
            scanner = new Scanner(new FileInputStream("f:\\java\\AirlineApp\\src\\main\\resources\\UserDatabase.txt"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            accountsInDatabase++;
        }
        return accountsInDatabase;
    }


    public static Account getAccountFromDatabase(String email, String password) {
        MainMenuController mainMenuController = new MainMenuController();
        Account account = null;
        Scanner scanner = null;
        boolean accountFound = false;
        boolean passwordCorrect = true;
        try {
            scanner = new Scanner(new FileInputStream(USER_DATABASE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            String user = scanner.nextLine().toString();
            user = user.replace(",", "");
            String[] userAttributes = user.split(" ");
            if (userAttributes[4].toLowerCase().equals(email.toLowerCase()) && userAttributes[6].equals(password)) {
                accountFound = true;
                passwordCorrect = true;
                account = new Account(Integer.parseInt(userAttributes[0].substring(0, 1)), userAttributes[1], userAttributes[2], userAttributes[3], userAttributes[4]);
            } else if (userAttributes[4].equals(email) && !userAttributes[6].equals(password)) {
                accountFound = true;
                passwordCorrect = false;
            }
        }
        if (!accountFound) {
            System.out.println("Account not found.");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newChapter();
            mainMenuController.showMenu();
        }
        if (accountFound && !passwordCorrect) {
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

    public static void editAccountInDatabase(int id, Account account) {

//        user = user.replace(",", "");
//        String[] userAttributes = user.split(" ");
//        if (userAttributes[4].toLowerCase().equals(email.toLowerCase()) && userAttributes[6].equals(password)) {
//            accountFound = true;
//            passwordCorrect = true;
//            account = new Account(Integer.parseInt(userAttributes[0].substring(0, 1)), userAttributes[1], userAttributes[2], userAttributes[3], userAttributes[4]);
//
//        }
    }
}