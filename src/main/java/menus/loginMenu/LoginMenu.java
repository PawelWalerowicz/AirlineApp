package menus.loginMenu;

import menus.Singleton;
import menus.TerminalMenu;
import menus.mainMenu.MainMenu;

import static utilities.ClearConsole.cleanConsole;

public class LoginMenu implements TerminalMenu, Singleton {
    private static TerminalMenu loginMenuInstance;

    public static TerminalMenu getInstance() {
        if(loginMenuInstance==null) {
            loginMenuInstance = new LoginMenu();
        }
        return loginMenuInstance;
    }

    private LoginMenu() {
    }

    public void viewMenu() {
        cleanConsole();
        System.out.println("Please enter following information to log into your account (or press \"Q\" to quit):");
        LoginController loginController = LoginController.getInstance();
        loginController.login();
    }
}