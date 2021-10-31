package controller;

import model.Account;

import java.util.Scanner;

import static utilities.ClearConsole.newChapter;
import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.InputOutputTools.showMenuOptions;
import static utilities.InputValidator.*;

public class UserController {
    Account account;
    MainMenuController mmc = new MainMenuController();

    public UserController(Account account) {
        this.account = account;
    }

    private String LIST_OF_USER_MENU_OPTIONS = "f:\\java\\AirlineApp\\src\\main\\resources\\LoggedInUserOptions.csv";
    private String LIST_OF_EDIT_ACCOUNT_MENU_OPTIONS = "f:\\java\\AirlineApp\\src\\main\\resources\\EditAccountOptions.csv";

    public void showUserMenu() {
        newChapter();
        System.out.println("Welcome again " + account.getName() + " " + account.getSurname()+". How can we help you?" );
        int amountOfOptions = showMenuOptions(LIST_OF_USER_MENU_OPTIONS);
        int option = readUserIntegerInput(amountOfOptions);
        UserController userController = new UserController(account);
        changeViewLoggedInUser(option);
    }

    private void changeViewLoggedInUser(int option) {
        switch(option) {
            case 0:
                viewEditAccountMenu();
                break;
            case 1:
                System.out.println("TODO: View or Edit your flights menu");
                break;
            case 2:
                System.out.println("TODO: View flights menu");
                break;
            case 3:
                newChapter();
                mmc.viewMainMenu();
            case 4:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Something went wrong.");
                break;
        }
    }

    private void viewEditAccountMenu() {
        newChapter();
        System.out.println("What would you like to do with your account?" );
        int amountOfOptions = showMenuOptions(LIST_OF_EDIT_ACCOUNT_MENU_OPTIONS);
        int option = readUserIntegerInput(amountOfOptions);
        changeViewEditAccount(option);
    }

    private void changeViewEditAccount(int option) {
        switch(option) {
            case 0:
                changeName();
                showUserMenu();
                break;
            case 1:
                changeSurname();
                showUserMenu();
                break;
            case 2:
                changeEmail();
                showUserMenu();
                break;
            case 3:
                changePassword();
                showUserMenu();
                break;
            case 4:
                deleteAccount();
                mmc.viewMainMenu();
                break;
            case 5:
                showUserMenu();
                break;
            default:
                System.out.println("Something went wrong.");
                break;
        }

    }

    private void changeName() {
        Scanner scanner = new Scanner(System.in);
        String name=account.getName();
        System.out.print("Current name is " + name + ", please enter new name or \"Q\" to discard changes: ");
        do{
            name = scanner.nextLine();
            if(name.toLowerCase().equals("q")) {
                break;
            }
        } while(!isNameValid(name));

        if(name.toLowerCase().equals("q")) {
            viewEditAccountMenu();
        } else {
            account.setName(name);
            AccountDatabaseController.editAccountInDatabase(account.getId(),account);
            System.out.println("Name changed to " + name + ".");
        }
    }

    private void changeSurname() {
        Scanner scanner = new Scanner(System.in);
        String surname=account.getSurname();
        System.out.print("Current surname is " + surname + ", please enter new surname or \"Q\" to discard changes: ");
        do{
            surname = scanner.nextLine();
            if(surname.toLowerCase().equals("q")) {
                break;
            }
        } while(!isNameValid(surname));

        if(surname.toLowerCase().equals("q")) {
            viewEditAccountMenu();
        } else {
            account.setSurname(surname);
            AccountDatabaseController.editAccountInDatabase(account.getId(),account);
            System.out.println("Surname changed to " + surname + ".");
        }
    }

    private void changeEmail() {
        Scanner scanner = new Scanner(System.in);
        String email=account.getEmail();
        System.out.print("Current email is " + email + ", please enter new email or \"Q\" to discard changes: ");
        do{
            email = scanner.nextLine();
            if(email.toLowerCase().equals("q")) {
                break;
            }
        } while(!isEmailValid(email));

        if(email.toLowerCase().equals("q")) {
            viewEditAccountMenu();
        } else {
            account.setEmail(email);
            AccountDatabaseController.editAccountInDatabase(account.getId(),account);
            System.out.println("Email changed to " + email + ".");
        }
    }


    private void changePassword() {
        Scanner scanner = new Scanner(System.in);
        String password;
        System.out.print("Please enter new password or \"Q\" to discard changes: ");
        do{
            password = scanner.nextLine();
            if(password.toLowerCase().equals("q")) {
                break;
            }
        } while(!isPasswordValid(password));
        String confirmPassword=null;
        do{
            System.out.print("Repeat password: ");
            confirmPassword=scanner.nextLine();
        } while(!passwordConfirmation(password, confirmPassword));

        if(password.toLowerCase().equals("q")) {
            viewEditAccountMenu();
        } else {
            account.setPassword(password);
            AccountDatabaseController.editAccountInDatabase(account.getId(),account);
            System.out.println("Password changed.");
        }
    }

    private void deleteAccount() {
        Scanner scanner = new Scanner(System.in);
        String password;
        System.out.print("You are about to delete your account. If you really want to do that enter your password or \"Q\" to quit: ");
        do{
            password = scanner.nextLine();
            if(password.toLowerCase().equals("q")) {
                break;
            }
        } while(!isPasswordValid(password));

        if(password.toLowerCase().equals("q")) {
            MainMenuController mmc = new MainMenuController();
            mmc.viewMainMenu();
        } else {
            AccountDatabaseController.deleteAccountFromDatabase(account);
            System.out.println("Account deleted. Goodbye.");
        }
    }



}
