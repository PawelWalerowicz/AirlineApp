package view;

import controller.LoginController;

import static utilities.ClearConsole.cleanConsole;

public class LoginMenu {
    LoginController lc = new LoginController();

    public void viewLoginMenu() {
        cleanConsole();
        System.out.println("Please enter following information to log into your account (or press \"Q\" to quit):");
        lc.login();
    }
}