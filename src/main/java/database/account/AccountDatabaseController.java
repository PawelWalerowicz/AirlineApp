package database.account;

import menus.Singleton;
import menus.TerminalMenu;
import menus.createAccountMenu.CreateAccountController;
import model.Account;
import menus.mainMenu.MainMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utilities.ClearConsole.cleanConsole;
import static utilities.InputOutputTools.clearFile;
import static utilities.InputOutputTools.loadDatabaseIntoScanner;
import static utilities.ResourcesIndex.USER_DATABASE_FILE;

public class AccountDatabaseController implements Singleton {
    private static AccountDatabaseController accountDatabaseControllerInstance;

    public static AccountDatabaseController getInstance() {
        if(accountDatabaseControllerInstance==null) {
            accountDatabaseControllerInstance = new AccountDatabaseController();
        }
        return accountDatabaseControllerInstance;
    }

    private AccountDatabaseController() {}

    public static void saveAccountToDatabase(Account account) {
        account.setId(getNumberOfAccountsInDatabase() + 1);
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(USER_DATABASE_FILE, true));
            writer.println(account);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getNumberOfAccountsInDatabase() {
        Scanner scanner;
        int accountsInDatabase = 0;
        try {
            scanner = new Scanner(new FileInputStream(USER_DATABASE_FILE));

            while (scanner.hasNextLine()) {
                scanner.nextLine();
                accountsInDatabase++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return accountsInDatabase;
    }


    public static Account getAccountFromDatabase(String email, String password) {
        Account account = null;
        boolean accountFound = false;
        boolean passwordCorrect = true;

        Scanner scanner = loadDatabaseIntoScanner(USER_DATABASE_FILE);

        while (scanner.hasNextLine()) {
            String user = scanner.nextLine();
            Account currentAccount = convertDatabaseStringToAccount(user);
            if (currentAccount.getEmail().equals(email.toLowerCase()) && currentAccount.getPassword().equals(password)) {
                accountFound = true;
                passwordCorrect = true;
                account = currentAccount;
            } else if (currentAccount.getEmail().equals(email) && !currentAccount.getPassword().equals(password)) {
                accountFound = true;
                passwordCorrect = false;
            }
        }

        handleIncorrectLoginInput(accountFound, passwordCorrect);

        return account;
    }

    public static void editAccountInDatabase(int id, Account account) {
        List<String> allAccounts = new ArrayList<>();

        Scanner scanner = loadDatabaseIntoScanner(USER_DATABASE_FILE);

        while (scanner.hasNextLine()) {
            String user = scanner.nextLine();
            Account currentAccount = convertDatabaseStringToAccount(user);
            if (currentAccount.getId() == id) {
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
        rewriteDatabase(allAccounts);
    }

    public static Account getAccountById(int id) {
        Account account = null;
        Scanner scanner = loadDatabaseIntoScanner(USER_DATABASE_FILE);
        while (scanner.hasNextLine()) {
            String user = scanner.nextLine();
            Account currentAccount = convertDatabaseStringToAccount(user);
            if (currentAccount.getId() == id) {
                account = currentAccount;
            }
        }
        return account;
    }

    public static void deleteAccountFromDatabase(Account account) {
        Scanner scanner;
        List<String> allAccounts = new ArrayList<>();
        int id = account.getId();

        try {
            scanner = new Scanner(new FileInputStream(USER_DATABASE_FILE));
            int currentId = 0;
            while (scanner.hasNextLine()) {
                String user = scanner.nextLine();
                Account currentAccount = convertDatabaseStringToAccount(user);
                if (currentAccount.getId() != id) {
                    currentId++;
                    currentAccount.setId(currentId);
                    allAccounts.add(currentAccount.toString());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        rewriteDatabase(allAccounts);

    }

    private static Account convertDatabaseStringToAccount(String userAsString) {
        String[] userAttributes = getUserAttributesFromDatabaseString(userAsString);
        return getAccountFromAttributes(userAttributes);
    }

    private static String[] getUserAttributesFromDatabaseString(String userAsString) {
        String[] parameters = userAsString
                .replace(",", "")
                .split(" ");
        parameters[0] = parameters[0].replace(".","");
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

    private static void rewriteDatabase(List<String> allAccounts) {
        try {
            clearFile(USER_DATABASE_FILE);
            PrintWriter writer = new PrintWriter(new FileWriter(USER_DATABASE_FILE, true));
            for (String allAccount : allAccounts) {
                writer.println(allAccount);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isInDatabase(String email) {
        Scanner scanner;
        boolean isInDatabase = false;
        try {
            scanner = new Scanner(new FileInputStream(USER_DATABASE_FILE));

            while (scanner.hasNextLine()) {
                String user = scanner.nextLine();
                Account currentAccount = convertDatabaseStringToAccount(user);
                if (currentAccount.getEmail().equals(email.toLowerCase())) {
                    isInDatabase = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return isInDatabase;

    }

    private static void handleIncorrectLoginInput(boolean accountFound, boolean passwordCorrect) {
        TerminalMenu mainMenu = MainMenu.getInstance();
        if (!accountFound) {
            System.out.println("Account not found.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cleanConsole();
            mainMenu.viewMenu();
        }

        if (accountFound && !passwordCorrect) {
            System.out.println("Incorrect password.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cleanConsole();
            mainMenu.viewMenu();
        }
    }


}