package controllers.account;

import model.Account;
import view.EditAccountMenu;
import view.MainMenu;
import view.SearchFlightsMenu;
import view.UserMenu;

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
            UserMenu userMenu = new UserMenu(account);
            userMenu.viewMenu();
        }
    }

    public void changeView(int option) {
        switch(option) {
            case 0:
                EditAccountMenu editAccountMenu = new EditAccountMenu(account);
                editAccountMenu.viewMenu();
                break;
            case 1:
                SearchFlightsMenu searchFlightsMenu = new SearchFlightsMenu();
                break;
            case 2:
                System.out.println("TODO: View flights menu");
                break;
            case 3:
                cleanConsole();
                MainMenu mainMenu = new MainMenu();
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
