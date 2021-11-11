package view;

import controllers.LoginController;

import static utilities.ClearConsole.cleanConsole;

public class LoginMenu {


    public void viewMenu() {
        cleanConsole();
        System.out.println("Please enter following information to log into your account (or press \"Q\" to quit):");
        LoginController loginController = new LoginController();
    }
}