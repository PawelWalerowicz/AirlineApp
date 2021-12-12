package menus.userMenu;

import menus.Singleton;
import menus.TerminalMenu;
import menus.TerminalMenuWithUser;
import menus.mainMenu.MainMenu;
import model.Account;

import static utilities.ClearConsole.cleanConsole;

public class UserMenu implements TerminalMenuWithUser {
    private static TerminalMenuWithUser userMenuInstance;

    public static TerminalMenuWithUser getInstance() {
        if(userMenuInstance==null) {
            userMenuInstance = new UserMenu();
        }
        return userMenuInstance;
    }


    private UserMenu() {
    }

    public void viewMenu(Account account) {
        cleanConsole();
        System.out.println("Welcome again " + account.getName() + " " + account.getSurname()+". How can we help you?" );
        UserMenuController userMenuController = UserMenuController.getInstance();
        userMenuController.viewOptions(account);

    }

}
