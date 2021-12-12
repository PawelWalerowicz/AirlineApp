package menus.createAccountMenu;

import menus.TerminalMenu;
import menus.editAccountMenu.EditAccountMenu;
import model.Account;

import static utilities.ClearConsole.cleanConsole;

public class CreateAccountMenu implements TerminalMenu {
    static TerminalMenu createAccountMenuInstance;

    public static TerminalMenu getInstance() {
        if(createAccountMenuInstance ==null) {
            createAccountMenuInstance = new CreateAccountMenu();
        }
        return createAccountMenuInstance;
    }

    private CreateAccountMenu() {

    }


    public void viewMenu() {
        cleanConsole();
        System.out.println("Please enter following information to create an account (or press \"Q\" to quit):");
        CreateAccountController createAccountController = new CreateAccountController();
    }
}
