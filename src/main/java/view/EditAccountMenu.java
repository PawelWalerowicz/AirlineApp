package view;

import controllers.account.EditAccountController;
import model.Account;

import static utilities.ClearConsole.cleanConsole;

public class EditAccountMenu {
    Account account;
    EditAccountController editAccountController;

    public EditAccountMenu(Account account) {
        this.account = account;
    }

    public void viewMenu() {

        cleanConsole();
        System.out.println("What would you like to do with your account?" );
        editAccountController = new EditAccountController(account);
    }
}
