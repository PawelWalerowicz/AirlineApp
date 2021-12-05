package controllers;

import view.CreateAccountMenu;
import view.LoginMenu;
import view.MainMenu;
import view.SearchFlightsMenu;

import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.InputOutputTools.printMenuOptions;
import static utilities.ResourcesIndex.LIST_OF_MAIN_MENU_OPTIONS;


public class MainMenuController {

    public MainMenuController() {
        viewOptions();
    }

    public void viewOptions() {
        int amountOfOptions = printMenuOptions(LIST_OF_MAIN_MENU_OPTIONS);
        try {
            int option = readUserIntegerInput(amountOfOptions);
            changeView(option);
        } catch (NumberFormatException exc) {
            System.out.println("Wrong input, please try again.\n");
            MainMenu mainMenu = new MainMenu();
            mainMenu.viewMenu();
        }
    }

    public void changeView(int option) {
        switch (option) {
            case 0:
                CreateAccountMenu createAccountMenu = new CreateAccountMenu();
                createAccountMenu.viewMenu();
                break;
            case 1:
                LoginMenu loginMenu = new LoginMenu();
                loginMenu.viewMenu();
                break;
            case 2:
                SearchFlightsMenu searchFlightsMenu = new SearchFlightsMenu();
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
