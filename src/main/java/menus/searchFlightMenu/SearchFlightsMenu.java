package menus.searchFlightMenu;

import menus.TerminalMenu;
import menus.mainMenu.MainMenu;
import model.Account;

import static utilities.ClearConsole.cleanConsole;

public class SearchFlightsMenu implements TerminalMenu {
    Account account;

    private static TerminalMenu searchFlightsMenuInstance;

    public static TerminalMenu getInstance() {
        if(searchFlightsMenuInstance==null) {
            searchFlightsMenuInstance = new SearchFlightsMenu();
        }
        return searchFlightsMenuInstance;
    }


    public static TerminalMenu getInstance(Account account) {
        if(searchFlightsMenuInstance==null) {
            searchFlightsMenuInstance = new SearchFlightsMenu(account);
        }
        return searchFlightsMenuInstance;
    }

    private SearchFlightsMenu(Account account) {
        this.account = account;
    }

    private SearchFlightsMenu() {
    }

    public void viewMenu() {
        cleanConsole();
        System.out.println("Please enter following information to view available flights (or press \"Q\" to quit):");
        if(account == null) {
            SearchFlightsController searchFlightsController = new SearchFlightsController();
        } else {
            SearchFlightsController searchFlightsController = new SearchFlightsController(account);
        }
    }


}
