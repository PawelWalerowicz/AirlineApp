package menus.userMenu;

import database.aerial.FlightDatabaseController;
import menus.Singleton;
import menus.TerminalMenu;
import menus.TerminalMenuWithUser;
import model.Account;
import menus.editAccountMenu.EditAccountMenu;
import menus.mainMenu.MainMenu;
import menus.searchFlightMenu.SearchFlightsMenu;

import static utilities.ClearConsole.cleanConsole;
import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.InputOutputTools.printMenuOptions;
import static utilities.ResourcesIndex.LIST_OF_USER_MENU_OPTIONS;

public class UserMenuController implements Singleton {
    private static UserMenuController userMenuControllerInstance;

    private Account account;

    public static UserMenuController getInstance() {
        if(userMenuControllerInstance ==null) {
            userMenuControllerInstance = new UserMenuController();
        }
        return userMenuControllerInstance;
    }

        private UserMenuController() {
    }

    public void viewOptions(Account account) {
        this.account = account;
        int amountOfOptions = printMenuOptions(LIST_OF_USER_MENU_OPTIONS);
        try {
            int option = readUserIntegerInput(amountOfOptions);
            changeView(option);
        } catch (NumberFormatException exc) {
            System.out.println("Wrong input, please try again.\n");
            TerminalMenuWithUser userMenu = UserMenu.getInstance();
            userMenu.viewMenu(account);
        }
    }

    public void changeView(int option) {
        switch(option) {
            case 0:
                TerminalMenuWithUser editAccountMenu = EditAccountMenu.getInstance();
                editAccountMenu.viewMenu(account);
                break;
            case 1:
                FlightDatabaseController flightDatabaseController = new FlightDatabaseController(account);
                flightDatabaseController.printAllFlights();     //todo: create EditYourFlightsMenu
                break;
            case 2:
                TerminalMenuWithUser searchFlightsMenu = (TerminalMenuWithUser) SearchFlightsMenu.getInstance();
                searchFlightsMenu.viewMenu(account);
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
