package view;

import controller.CreateAccountController;

import static utilities.ClearConsole.cleanConsole;

public class CreateAccountMenu {
    CreateAccountController cac = new CreateAccountController();

    public void viewCreateAccountMenu() {
        cleanConsole();
        System.out.println("Please enter following information to create an account (or press \"Q\" to quit):");
        cac.createAccount();
    }
}
