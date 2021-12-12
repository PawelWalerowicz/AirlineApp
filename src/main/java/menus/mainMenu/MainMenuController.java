package menus.mainMenu;

import menus.Singleton;
import menus.TerminalMenu;
import menus.createAccountMenu.CreateAccountMenu;
import menus.loginMenu.LoginMenu;
import menus.searchFlightMenu.SearchFlightsMenu;
import menus.userMenu.UserMenuController;

import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.InputOutputTools.printMenuOptions;
import static utilities.ResourcesIndex.LIST_OF_MAIN_MENU_OPTIONS;


public class MainMenuController implements Singleton {
    private static MainMenuController mainMenuController;

    public static MainMenuController getInstance() {
        if(mainMenuController==null) {
            mainMenuController = new MainMenuController();
        }
        return mainMenuController;
    }

    private MainMenuController() {
    }

    public void viewOptions() {
        int amountOfOptions = printMenuOptions(LIST_OF_MAIN_MENU_OPTIONS);
        try {
            int option = readUserIntegerInput(amountOfOptions);
            changeView(option);
        } catch (NumberFormatException exc) {
            System.out.println("Wrong input, please try again.\n");
            TerminalMenu mainMenu = MainMenu.getInstance();
            mainMenu.viewMenu();
        }
    }

    public void changeView(int option) {
        switch (option) {
            case 0:
                TerminalMenu createAccountMenu = CreateAccountMenu.getInstance();
                createAccountMenu.viewMenu();
                break;
            case 1:
                TerminalMenu loginMenu = LoginMenu.getInstance();
                loginMenu.viewMenu();
                break;
            case 2:
                TerminalMenu searchFlightsMenu = SearchFlightsMenu.getInstance();
                searchFlightsMenu.viewMenu();
                break;
            case 3:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Something went wrong");
                break;
        }
    }


}
