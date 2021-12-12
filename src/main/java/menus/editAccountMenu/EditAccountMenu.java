package menus.editAccountMenu;

import menus.Singleton;
import menus.TerminalMenu;
import menus.TerminalMenuWithUser;
import menus.loginMenu.LoginMenu;
import model.Account;

import static utilities.ClearConsole.cleanConsole;

public class EditAccountMenu implements TerminalMenuWithUser, Singleton {
    EditAccountController editAccountController;
    private static TerminalMenuWithUser editAccoutMenuInstance;

    public static TerminalMenuWithUser getInstance() {
        if(editAccoutMenuInstance==null) {
            editAccoutMenuInstance = EditAccountMenu.getInstance();
        }
        return editAccoutMenuInstance;
    }


    private EditAccountMenu() {
    }

    public void viewMenu(Account account) {
        cleanConsole();
        System.out.println("What would you like to do with your account?" );
        editAccountController = EditAccountController.getInstance();
        editAccountController.viewOptions(account);
    }
}
