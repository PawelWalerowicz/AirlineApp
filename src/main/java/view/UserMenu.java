package view;

import controller.UserController;
import model.Account;
import utilities.ResourcesIndex;

import static utilities.ClearConsole.cleanConsole;
import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.InputOutputTools.showMenuOptions;

public class UserMenu {
    Account account;
    UserController uc;

    public UserMenu(Account account) {
        this.account = account;
        uc = new UserController(account);
    }

    public void viewUserMenu() {
        cleanConsole();
        System.out.println("Welcome again " + account.getName() + " " + account.getSurname()+". How can we help you?" );
        uc.viewUserOptions();
    }

}
