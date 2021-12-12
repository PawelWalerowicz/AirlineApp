package menus.userMenu;

import database.aerial.FlightDatabaseController;
import menus.TerminalMenu;
import model.Account;
import menus.editAccountMenu.EditAccountMenu;
import menus.mainMenu.MainMenu;
import menus.searchFlightMenu.SearchFlightsMenu;

import static utilities.ClearConsole.cleanConsole;
import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.InputOutputTools.printMenuOptions;
import static utilities.ResourcesIndex.LIST_OF_USER_MENU_OPTIONS;

public class UserMenuController {
    Account account;

    public UserMenuController(Account account) {
        this.account = account;
        viewOptions();
    }

    public void viewOptions() {
        int amountOfOptions = printMenuOptions(LIST_OF_USER_MENU_OPTIONS);
        try {
            int option = readUserIntegerInput(amountOfOptions);
            changeView(option);
        } catch (NumberFormatException exc) {
            System.out.println("Wrong input, please try again.\n");
            TerminalMenu userMenu = UserMenu.getInstance(account);
            userMenu.viewMenu();
        }
    }

    public void changeView(int option) {
        switch(option) {
            case 0:
                TerminalMenu editAccountMenu = EditAccountMenu.getInstance(account);
                editAccountMenu.viewMenu();
                break;
            case 1:
                FlightDatabaseController flightDatabaseController = new FlightDatabaseController(account);
                flightDatabaseController.printAllFlights();     //todo: create EditYourFlightsMenu
                break;
            case 2:
                TerminalMenu searchFlightsMenu = SearchFlightsMenu.getInstance(account);
                searchFlightsMenu.viewMenu();
                break;
            case 3:
                cleanConsole();
                TerminalMenu mainMenu = MainMenu.getInstance();
                mainMenu.viewMenu();
                break;
            case 4:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Something went wrong.");
                break;
        }
    }


}
