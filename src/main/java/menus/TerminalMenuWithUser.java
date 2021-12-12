package menus;

import model.Account;

public interface TerminalMenuWithUser extends TerminalMenu {
    default void viewMenu(Account account) {

    }
}
