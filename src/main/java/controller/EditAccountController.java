package controller;

import model.Account;
import view.EditAccountMenu;
import view.MainMenu;
import view.UserMenu;

import java.util.Scanner;

import static utilities.ClearConsole.cleanConsole;
import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.InputOutputTools.showMenuOptions;
import static utilities.InputValidator.*;
import static utilities.ResourcesIndex.LIST_OF_EDIT_ACCOUNT_MENU_OPTIONS;

public class EditAccountController {
    Account account;
    UserMenu um;
    EditAccountMenu eam;
    MainMenu mm = new MainMenu();

    public EditAccountController(Account account) {
        this.account = account;
    }

    public void viewEditAccountOptions() {
        int amountOfOptions = showMenuOptions(LIST_OF_EDIT_ACCOUNT_MENU_OPTIONS);
        try {
            int option = readUserIntegerInput(amountOfOptions);
            changeViewEditAccount(option);
        } catch (NumberFormatException exc) {
            System.out.println("Wrong input, please try again.\n");
            EditAccountMenu eam = new EditAccountMenu(account);
            eam.viewEditAccountMenu();
        }

    }


    public void changeViewEditAccount(int option) {
        um = new UserMenu(account);
        switch (option) {
            case 0:
                changeName();
                um.viewUserMenu();
                break;
            case 1:
                changeSurname();
                um.viewUserMenu();
                break;
            case 2:
                changeEmail();
                um.viewUserMenu();
                break;
            case 3:
                changePassword();
                um.viewUserMenu();
                break;
            case 4:
                deleteAccount();
                mm.viewMainMenu();
                break;
            case 5:
                um.viewUserMenu();
                break;
            default:
                System.out.println("Something went wrong.");
                break;
        }

    }

    private void changeName() {
        Scanner scanner = new Scanner(System.in);
        String name = account.getName();
        System.out.print("Current name is " + name + ", please enter new name or \"Q\" to discard changes: ");
        boolean proceed = true;
        if (proceed) {
            do {
                name = scanner.nextLine();
                proceed = checkForQuit(name);
            } while (proceed && !isNameValid(name));
        }
            if (!proceed) {
                eam = new EditAccountMenu(account);
                eam.viewEditAccountMenu();
            } else {
                account.setName(name);
                AccountDatabaseController.editAccountInDatabase(account.getId(), account);
                System.out.println("Name changed to " + name + ".");
        }
    }

    private void changeSurname() {
        Scanner scanner = new Scanner(System.in);
        String surname = account.getSurname();
        boolean proceed = true;
        System.out.print("Current surname is " + surname + ", please enter new surname or \"Q\" to discard changes: ");
            do {
                surname = scanner.nextLine();
                proceed = checkForQuit(surname);
            } while (proceed && !isNameValid(surname));

        if (!proceed) {
            eam = new EditAccountMenu(account);
            eam.viewEditAccountMenu();
        } else {
            account.setSurname(surname);
            AccountDatabaseController.editAccountInDatabase(account.getId(), account);
            System.out.println("Surname changed to " + surname + ".");
        }
    }

    private void changeEmail() {
        Scanner scanner = new Scanner(System.in);
        String email = account.getEmail();
        boolean proceed = true;
        System.out.print("Current email is " + email + ", please enter new email or \"Q\" to discard changes: ");
        do {
            email = scanner.nextLine();
            proceed = checkForQuit(email);
        } while (proceed && !isEmailValid(email));

        if (!proceed) {
            eam = new EditAccountMenu(account);
            eam.viewEditAccountMenu();
        } else {
            account.setEmail(email);
            AccountDatabaseController.editAccountInDatabase(account.getId(), account);
            System.out.println("Email changed to " + email + ".");
        }
    }


    private void changePassword() {
        Scanner scanner = new Scanner(System.in);
        String password;
        boolean proceed = true;
        System.out.print("Please enter new password or \"Q\" to discard changes: ");
        do {
            password = scanner.nextLine();
            proceed = checkForQuit(password);
        } while (!isPasswordValid(password));
        String confirmPassword = null;
        if(proceed) {
            do {
                System.out.print("Repeat password: ");
                confirmPassword = scanner.nextLine();
                proceed = checkForQuit(confirmPassword);
            } while (proceed && !passwordConfirmation(password, confirmPassword));
        }

        if (!proceed) {
            eam = new EditAccountMenu(account);
            eam.viewEditAccountMenu();
        } else {
            account.setPassword(password);
            AccountDatabaseController.editAccountInDatabase(account.getId(), account);
            System.out.println("Password changed.");
        }
    }

    private void deleteAccount() {
        Scanner scanner = new Scanner(System.in);
        String password;
        System.out.print("You are about to delete your account. If you really want to do that enter your password or \"Q\" to quit: ");
        boolean proceed = true;
        do {
            password = scanner.nextLine();
            proceed = checkForQuit(password);
        } while (!isPasswordValid(password));

        if (!proceed) {
            mm.viewMainMenu();
        } else {
            AccountDatabaseController.deleteAccountFromDatabase(account);
            System.out.println("Account deleted. Goodbye.");
        }
    }
}
