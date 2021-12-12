package menus.editAccountMenu;

import menus.TerminalMenu;
import menus.loginMenu.LoginMenu;
import model.Account;

import static utilities.ClearConsole.cleanConsole;

public class EditAccountMenu implements TerminalMenu {
    Account account;
    EditAccountController editAccountController;
    static TerminalMenu editAccoutMenuInstance;

    public static TerminalMenu getInstance(Account account) {
        if(editAccoutMenuInstance==null) {
            editAccoutMenuInstance = new EditAccountMenu(account);
        }
        return editAccoutMenuInstance;
    }


    private EditAccountMenu(Account account) {
        this.account = account;
    }

    public void viewMenu() {

        cleanConsole();
        System.out.println("What would you like to do with your account?" );
        editAccountController = new EditAccountController(account);
    }
}
