package menus.userMenu;

import menus.TerminalMenu;
import menus.mainMenu.MainMenu;
import model.Account;

import static utilities.ClearConsole.cleanConsole;

public class UserMenu implements TerminalMenu {
    private Account account;
    private UserMenuController userMenuController;
    private static UserMenu userMenuInstance;

    public static TerminalMenu getInstance(Account account) {
        if(userMenuInstance==null) {
            userMenuInstance = new UserMenu(account);
        }
        return userMenuInstance;
    }


    private UserMenu(Account account) {
        this.account = account;
    }

    public void viewMenu() {
        cleanConsole();
        System.out.println("Welcome again " + account.getName() + " " + account.getSurname()+". How can we help you?" );
        UserMenuController userMenuController = new UserMenuController(account);

    }

}
