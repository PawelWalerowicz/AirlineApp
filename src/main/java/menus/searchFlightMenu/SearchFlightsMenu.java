package menus.searchFlightMenu;

import menus.Singleton;
import menus.TerminalMenu;
import menus.TerminalMenuWithUser;
import model.Account;

import static utilities.ClearConsole.cleanConsole;

public class SearchFlightsMenu implements TerminalMenu, TerminalMenuWithUser, Singleton {
    private static TerminalMenu searchFlightsMenuInstance;

    public static TerminalMenu getInstance() {
        if(searchFlightsMenuInstance==null) {
            searchFlightsMenuInstance = new SearchFlightsMenu();
        }
        return searchFlightsMenuInstance;
    }

    private SearchFlightsMenu() {
    }

    public void viewMenu(Account account) {
        viewMenuHeader();
        SearchFlightsController searchFlightsController = SearchFlightsController.getInstance();
        searchFlightsController.searchFlights(account);

    }

    public void viewMenu() {
        viewMenuHeader();
        SearchFlightsController searchFlightsController = SearchFlightsController.getInstance();
        searchFlightsController.searchFlights();
    }

    private void viewMenuHeader() {
        cleanConsole();
        System.out.println("Please enter following information to view available flights (or press \"Q\" to quit):");
    }

}
