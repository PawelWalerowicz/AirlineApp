package view;

import controller.EditAccountController;
import model.Account;

import static utilities.ClearConsole.cleanConsole;
import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.InputOutputTools.showMenuOptions;
import static utilities.ResourcesIndex.LIST_OF_EDIT_ACCOUNT_MENU_OPTIONS;

public class EditAccountMenu {
    Account account;
    EditAccountController eac;

    public EditAccountMenu(Account account) {
        this.account = account;
    }

    public void viewEditAccountMenu() {
        eac = new EditAccountController(account);
        cleanConsole();
        System.out.println("What would you like to do with your account?" );
        eac.viewEditAccountOptions();
    }
}
