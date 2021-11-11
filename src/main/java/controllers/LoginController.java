package controllers;

import model.Account;
import view.MainMenu;
import view.UserMenu;

import java.util.Scanner;

import static controllers.account.AccountDatabaseController.getAccountFromDatabase;
import static utilities.ClearConsole.cleanConsole;
import static utilities.InputValidator.*;

public class LoginController {
    Scanner scanner;
    boolean proceed = true;

    public LoginController() {
        scanner = new Scanner(System.in);
        login();
    }

    public void login() {

        String email = askForEmail();
        String password = askForPassword();

        if (proceed) {
            Account account = getAccountFromDatabase(email, password);
            UserMenu userMenu = new UserMenu(account);
            userMenu.viewMenu();
        } else {
            cleanConsole();
            MainMenu mainMenu = new MainMenu();
            mainMenu.viewMenu();
        }
    }

    private String askForEmail() {
        String email = null;
        if (proceed) {
            do {
                System.out.print("Email: ");
                email = scanner.nextLine();
                proceed = checkForQuit(email);
            } while (proceed && !isEmailValid(email));
        }

        return email;
    }

    private String askForPassword() {
        String password = null;
        if (proceed) {
            do {
                System.out.print("Password: ");
                password = scanner.nextLine();
                proceed = checkForQuit(password);
            } while (proceed && !isPasswordValid(password));
        }
        return password;
    }


}
