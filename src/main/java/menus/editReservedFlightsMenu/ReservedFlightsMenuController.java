package menus.editReservedFlightsMenu;

import database.aerial.FlightDatabaseController;
import menus.Singleton;
import menus.TerminalMenuWithUser;
import menus.userMenu.UserMenu;
import model.Account;
import model.ReservedFlight;

import java.util.Map;

import static utilities.ClearConsole.cleanConsole;
import static utilities.InputOutputTools.*;
import static utilities.ResourcesIndex.LIST_OF_EDIT_FLIGHT_MENU_OPTIONS;

public class ReservedFlightsMenuController implements Singleton {
    private static ReservedFlightsMenuController reservedFlightsMenuControllerInstance;
    TerminalMenuWithUser reservedFlightsMenu = ReservedFlightsMenu.getInstance();
    private Account account;

    public static ReservedFlightsMenuController getInstance() {
        if (reservedFlightsMenuControllerInstance == null) {
            reservedFlightsMenuControllerInstance = new ReservedFlightsMenuController();
        }
        return reservedFlightsMenuControllerInstance;
    }

    private ReservedFlightsMenuController() {
    }

    public void viewOptions(Account account) {
        this.account = account;
        System.out.println("All your reserved flights: ");
        FlightDatabaseController flightDatabaseController = FlightDatabaseController.getInstance();
        Map<Integer, ReservedFlight> allFlights = flightDatabaseController.getAllFlightsForAccount(account);
        flightDatabaseController.printAllFlights(allFlights);
        System.out.println("Please choose a number of a flight you would like to edit or press Q to quit: ");
        try {
            int flightNumber = readUserNotNullIntegerInput(allFlights.size()); //todo: get an option to quit with q
            EditFlight(allFlights.get(flightNumber));
        } catch (NumberFormatException exc) {
            System.out.println("Wrong input, please try again.\n");
            reservedFlightsMenu.viewMenu();
        }
    }

    private void EditFlight(ReservedFlight reservedFlight) {
        cleanConsole();
        System.out.println("Chosen flight:");
        System.out.println(reservedFlight.toString());
        System.out.println("What would you like to change?");
        int amountOfOptions = printMenuOptions(LIST_OF_EDIT_FLIGHT_MENU_OPTIONS);
        try {
            int option = readUserIntegerInput(amountOfOptions);
            changeView(option);
        } catch (NumberFormatException exc) {
            System.out.println("Wrong input, please try again.\n");
            reservedFlightsMenu.viewMenu();
        }
    }

    public void changeView(int option) {
        switch (option) {
            case 0:
                //todo: add option to change amount of tickiets for chosen Flight
                System.out.println("Work in progress0");
                break;
            case 1:
                //todo: add option to search flight with set departure and destination (just dates)
                System.out.println("Work in progress1");
            case 2:
                //todo: add option to delete reserved flight
                System.out.println("Work in progress2");
                break;
            case 3:
                TerminalMenuWithUser userMenu = UserMenu.getInstance();
                userMenu.viewMenu(account);
                break;
            default:
                System.out.println("Something went wrong");
                break;
        }
    }


}
