package view;

import controllers.aerial.SearchFlightsController;
import model.Account;

import static utilities.ClearConsole.cleanConsole;

public class SearchFlightsMenu {
    Account account;

    public SearchFlightsMenu(Account account) {
        this.account = account;
    }

    public SearchFlightsMenu() {
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
