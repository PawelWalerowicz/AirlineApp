package view;

import controller.MainMenuController;

import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.InputOutputTools.showMenuOptions;
import static utilities.ResourcesIndex.LIST_OF_MAIN_MENU_OPTIONS;

public class MainMenu {
    MainMenuController mmc = new MainMenuController();

    public void viewMainMenu() {
        System.out.println("Welcome to AirAir reservation service.");
        System.out.println("Please choose one of the following options:");
        int amountOfOptions = showMenuOptions(LIST_OF_MAIN_MENU_OPTIONS);
        int option = readUserIntegerInput(amountOfOptions);
        mmc.changeViewMainMenu(option);
    }

}
