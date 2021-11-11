package view;

import controllers.account.CreateAccountController;

import static utilities.ClearConsole.cleanConsole;

public class CreateAccountMenu {

    public void viewMenu() {
        cleanConsole();
        System.out.println("Please enter following information to create an account (or press \"Q\" to quit):");
        CreateAccountController createAccountController = new CreateAccountController();
    }
}
