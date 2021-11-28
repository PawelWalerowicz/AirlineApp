package view;

import controllers.account.UserMenuController;
import model.Account;

import static utilities.ClearConsole.cleanConsole;

public class UserMenu {
    Account account;
    UserMenuController userMenuController;

    public UserMenu(Account account) {
        this.account = account;
    }

    public void viewMenu() {
        cleanConsole();
        System.out.println("Welcome again " + account.getName() + " " + account.getSurname()+". How can we help you?" );
        UserMenuController userMenuController = new UserMenuController(account);

    }

}
