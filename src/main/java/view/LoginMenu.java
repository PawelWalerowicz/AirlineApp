package view;

import controller.LoginController;

import static utilities.ClearConsole.cleanConsole;
import static utilities.ClearConsole.newChapter;

public class LoginMenu {
    LoginController lc = new LoginController();

    public void viewLoginMenu() {
        cleanConsole(); //todo: find a way to clean console after choosing correct menu, meanwhile:
        newChapter();
        System.out.println("Please enter following information to log into your account:");
        lc.login();
    }
}