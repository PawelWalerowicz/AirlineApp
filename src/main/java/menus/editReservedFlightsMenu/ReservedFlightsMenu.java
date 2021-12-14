package menus.editReservedFlightsMenu;

import menus.TerminalMenuWithUser;
import model.Account;
import model.Flight;

import java.util.Map;

public class ReservedFlightsMenu implements TerminalMenuWithUser {
    private static TerminalMenuWithUser editReservedFlightsMenuInstance;

    public static TerminalMenuWithUser getInstance() {
        if(editReservedFlightsMenuInstance==null) {
            editReservedFlightsMenuInstance = new ReservedFlightsMenu();
        }
        return editReservedFlightsMenuInstance;
    }

    private ReservedFlightsMenu() {
    }

    public void viewMenu(Account account) {
        ReservedFlightsMenuController reservedFlightsMenuController = ReservedFlightsMenuController.getInstance();
        reservedFlightsMenuController.viewOptions(account);


    }
}
