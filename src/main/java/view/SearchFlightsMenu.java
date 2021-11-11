package view;

import controllers.aerial.SearchFlightsController;

import static utilities.ClearConsole.cleanConsole;

public class SearchFlightsMenu {

    public void viewMenu() {
        cleanConsole();
        System.out.println("Please enter following information to view available flights (or press \"Q\" to quit):");
        SearchFlightsController searchFlightsController = new SearchFlightsController();
    }
}
