package controller;

import model.Account;
import view.MainMenu;
import view.UserMenu;

import java.util.Scanner;

import static controller.AccountDatabaseController.getAccountFromDatabase;
import static utilities.ClearConsole.cleanConsole;
import static utilities.InputValidator.*;

public class LoginController {

    public void login() {
        Scanner scanner = new Scanner(System.in);
        String email = null;
        String password = null;
        boolean proceed = true;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine();
            proceed = checkForQuit(email);
        } while (proceed && !isEmailValid(email));

        if (proceed) {
            do {
                System.out.print("Password: ");
                password = scanner.nextLine();
                proceed = checkForQuit(password);
            } while (proceed && !isPasswordValid(password));
        }
        if(proceed) {
            Account account = getAccountFromDatabase(email, password);
            UserMenu um = new UserMenu(account);
            um.viewUserMenu();
        } else {
            cleanConsole();
            MainMenu mm = new MainMenu();
            mm.viewMainMenu();
        }
    }
}
