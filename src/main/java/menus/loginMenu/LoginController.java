package menus.loginMenu;

import menus.Singleton;
import menus.TerminalMenu;
import menus.TerminalMenuWithUser;
import menus.mainMenu.MainMenuController;
import model.Account;
import menus.mainMenu.MainMenu;
import menus.userMenu.UserMenu;

import java.util.Scanner;

import static database.account.AccountDatabaseController.getAccountFromDatabase;
import static utilities.ClearConsole.cleanConsole;
import static utilities.InputValidator.*;

public class LoginController implements Singleton {
    Scanner scanner;
    boolean proceed = true;

    private static LoginController loginController;

    public static LoginController getInstance() {
        if(loginController==null) {
            loginController = new LoginController();
        }
        return loginController;
    }

    private LoginController(){;
    }

    public void login() {
        scanner = new Scanner(System.in);
        String email = askForEmail();
        String password = askForPassword();

        if (proceed) {
            Account account = getAccountFromDatabase(email, password);
            TerminalMenuWithUser userMenu = UserMenu.getInstance();
            userMenu.viewMenu(account);
        } else {
            cleanConsole();
            TerminalMenu mainMenu = MainMenu.getInstance();
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
