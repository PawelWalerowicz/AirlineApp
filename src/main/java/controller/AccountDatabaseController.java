package controller;

import model.Account;
import view.MainMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utilities.ClearConsole.newChapter;
import static utilities.InputOutputTools.clearFile;
import static utilities.ResourcesIndex.USER_DATABASE_FILE;

public class AccountDatabaseController {


    public static void saveAccountToDatabase(Account account) {
        account.setId(getNumberOfAccountsInDatabase() + 1);
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(USER_DATABASE_FILE, true));
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
            scanner = new Scanner(new FileInputStream(USER_DATABASE_FILE));

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
        MainMenu mm = new MainMenu();
        Account account = null;
        Scanner scanner = null;
        boolean accountFound = false;
        boolean passwordCorrect = true;
        try {
            scanner = new Scanner(new FileInputStream(USER_DATABASE_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            String user = scanner.nextLine().toString();
            Account currentAccount = getAccountFromDatabaseString(user);
            if (currentAccount.getEmail().equals(email.toLowerCase()) && currentAccount.getPassword().equals(password)) {
                accountFound = true;
                passwordCorrect = true;
                account = currentAccount;
            } else if (currentAccount.getEmail().equals(email) && !currentAccount.getPassword().equals(password)) {
                accountFound = true;
                passwordCorrect = false;
            }
        }
        if (!accountFound) {
            System.out.println("Account not found.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newChapter();
            mm.viewMainMenu();
        }
        if (accountFound && !passwordCorrect) {
            System.out.println("Incorrect password.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newChapter();
            mm.viewMainMenu();
        }
        return account;
    }

    public static void editAccountInDatabase(int id, Account account) {
        Scanner scanner = null;
        List allAccounts = new ArrayList();

        try {
            scanner = new Scanner(new FileInputStream(USER_DATABASE_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            String user = scanner.nextLine().toString();
            Account currentAccount = getAccountFromDatabaseString(user);
            if(currentAccount.getId()==id) {
                Account changedAccount = new Account(
                        id,
                        account.getName(),
                        account.getSurname(),
                        account.getEmail(),
                        account.getPassword()
                );
                allAccounts.add(changedAccount.toString());
            } else {
                allAccounts.add(user);
            }
        }

        rewriteDatabase(allAccounts,USER_DATABASE_FILE);

    }


    public static void deleteAccountFromDatabase(Account account) {
        Scanner scanner = null;
        List allAccounts = new ArrayList();
        int id = account.getId();

        try {
            scanner = new Scanner(new FileInputStream(USER_DATABASE_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int currentId=0;
        while (scanner.hasNextLine()) {
            String user = scanner.nextLine().toString();
            Account currentAccount = getAccountFromDatabaseString(user);
            if(currentAccount.getId()!=id) {
                currentId++;
                currentAccount.setId(currentId);
                allAccounts.add(currentAccount.toString());
            }
        }

        rewriteDatabase(allAccounts,USER_DATABASE_FILE);

    }

    private static String[] getUserAttributesFromDatabaseString(String userAsString) {
        String[] parameters =  userAsString
                .replace(",", "")
                .split(" ");
        parameters[0] = parameters[0].substring(0,parameters[0].length()-1);
        return parameters;
    }

    private static Account getAccountFromAttributes(String[] userAttributes) {
        return new Account(
                Integer.parseInt(userAttributes[0]),
                userAttributes[1],
                userAttributes[2],
                userAttributes[4],
                userAttributes[6]
        );
    }

    private static Account getAccountFromDatabaseString(String userAsString) {
        return getAccountFromAttributes(getUserAttributesFromDatabaseString(userAsString));
    }

    private static void rewriteDatabase(List<String> allAccounts, String filePath) {
        try {
            clearFile(filePath);
            PrintWriter writer = new PrintWriter(new FileWriter(filePath, true));
            for(int i=0;i<allAccounts.size();i++) {
                writer.println(allAccounts.get(i));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}