package controllers.account;

import model.Account;
import view.EditAccountMenu;
import view.MainMenu;
import view.UserMenu;

import java.util.Scanner;

import static utilities.InputOutputTools.printMenuOptions;
import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.InputValidator.*;
import static utilities.ResourcesIndex.LIST_OF_EDIT_ACCOUNT_MENU_OPTIONS;

//TODO: add option to change preferred currency
public class EditAccountController {
    Account account;
    UserMenu userMenu;
    EditAccountMenu editAccountMenu;
    MainMenu mainMenu = new MainMenu();

    public EditAccountController(Account account) {
        this.account = account;
        viewOptions();
    }

    public void viewOptions() {
        int amountOfOptions = printMenuOptions(LIST_OF_EDIT_ACCOUNT_MENU_OPTIONS);
        try {
            int option = readUserIntegerInput(amountOfOptions);
            changeView(option);
        } catch (NumberFormatException exc) {
            System.out.println("Wrong input, please try again.\n");
            editAccountMenu.viewMenu();
        }

    }

    public void changeView(int option) {
        userMenu = new UserMenu(account);
        switch (option) {
            case 0:
                changeName();
                userMenu.viewMenu();
                break;
            case 1:
                changeSurname();
                userMenu.viewMenu();
                break;
            case 2:
                changeEmail();
                userMenu.viewMenu();
                break;
            case 3:
                changePassword();
                userMenu.viewMenu();
                break;
            case 4:
                deleteAccount();
                mainMenu.viewMenu();
                break;
            case 5:
                userMenu.viewMenu();
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
        boolean proceed;
            do {
                name = scanner.nextLine();
                proceed = checkForQuit(name);
            } while (proceed && !isNameValid(name));

        if (!proceed) {
            editAccountMenu = new EditAccountMenu(account);
        } else {
            account.setName(name);
            AccountDatabaseController.editAccountInDatabase(account.getId(), account);
            System.out.println("Name changed to " + name + ".");
        }
    }

    private void changeSurname() {
        Scanner scanner = new Scanner(System.in);
        String surname = account.getSurname();
        boolean proceed;
        System.out.print("Current surname is " + surname + ", please enter new surname or \"Q\" to discard changes: ");
        do {
            surname = scanner.nextLine();
            proceed = checkForQuit(surname);
        } while (proceed && !isNameValid(surname));

        if (!proceed) {
            editAccountMenu = new EditAccountMenu(account);
        } else {
            account.setSurname(surname);
            AccountDatabaseController.editAccountInDatabase(account.getId(), account);
            System.out.println("Surname changed to " + surname + ".");
        }
    }

    private void changeEmail() {
        Scanner scanner = new Scanner(System.in);
        String email = account.getEmail();
        boolean proceed;
        System.out.print("Current email is " + email + ", please enter new email or \"Q\" to discard changes: ");
        do {
            email = scanner.nextLine();
            proceed = checkForQuit(email);
        } while (proceed && !isEmailValid(email));

        if (!proceed) {
            editAccountMenu = new EditAccountMenu(account);
        } else {
            account.setEmail(email);
            AccountDatabaseController.editAccountInDatabase(account.getId(), account);
            System.out.println("Email changed to " + email + ".");
        }
    }


    private void changePassword() {
        Scanner scanner = new Scanner(System.in);
        String password;
        boolean proceed;
        System.out.print("Please enter new password or \"Q\" to discard changes: ");
        do {
            password = scanner.nextLine();
            proceed = checkForQuit(password);
        } while (!isPasswordValid(password));
        String confirmPassword;
        if (proceed) {
            do {
                System.out.print("Repeat password: ");
                confirmPassword = scanner.nextLine();
                proceed = checkForQuit(confirmPassword);
            } while (proceed && !passwordConfirmation(password, confirmPassword));
        }

        if (!proceed) {
            editAccountMenu = new EditAccountMenu(account);
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
        boolean proceed;
        do {
            password = scanner.nextLine();
            proceed = checkForQuit(password);
        } while (!isPasswordValid(password));

        if (!proceed) {
            mainMenu.viewMenu();
        } else {
            AccountDatabaseController.deleteAccountFromDatabase(account);
            System.out.println("Account deleted. Goodbye.");
        }
    }
}
