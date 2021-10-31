package view;

import controller.CreateAccountController;

import static utilities.ClearConsole.cleanConsole;
import static utilities.ClearConsole.newChapter;

public class CreateAccountMenu {
    CreateAccountController cac = new CreateAccountController();

    public void viewCreateAccountMenu() {
        cleanConsole(); //todo: find a way to clean console after choosing correct menu, meanwhile:
        newChapter();
        System.out.println("Please enter following information to create an account:");
        cac.createAccount();
    }
}
