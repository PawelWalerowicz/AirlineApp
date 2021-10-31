package controller;

import model.Account;
import view.EditAccountMenu;
import view.MainMenu;
import view.UserMenu;

import java.util.Scanner;

import static utilities.InputValidator.*;

public class EditAccountController {
    Account account;
    UserMenu um;
    EditAccountMenu eam;
    MainMenu mm = new MainMenu();

    public EditAccountController(Account account) {
        this.account = account;
    }

    public void changeViewEditAccount(int option) {
        um = new UserMenu(account);
        switch(option) {
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
        String name=account.getName();
        System.out.print("Current name is " + name + ", please enter new name or \"Q\" to discard changes: ");
        do{
            name = scanner.nextLine();
            if(name.toLowerCase().equals("q")) {
                break;
            }
        } while(!isNameValid(name));

        if(name.toLowerCase().equals("q")) {
            eam.viewEditAccountMenu();
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
            eam.viewEditAccountMenu();
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
            eam.viewEditAccountMenu();
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
            eam.viewEditAccountMenu();
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
            mm.viewMainMenu();
        } else {
            AccountDatabaseController.deleteAccountFromDatabase(account);
            System.out.println("Account deleted. Goodbye.");
        }
    }
}
