package controller;

import view.CreateAccountMenu;
import view.LoginMenu;
import view.MainMenu;

import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.InputOutputTools.showMenuOptions;
import static utilities.ResourcesIndex.LIST_OF_MAIN_MENU_OPTIONS;


public class MainMenuController {

    public void viewMainMenuOptions() {
        int amountOfOptions = showMenuOptions(LIST_OF_MAIN_MENU_OPTIONS);
        try {
            int option = readUserIntegerInput(amountOfOptions);
            changeViewMainMenu(option);
        } catch (NumberFormatException exc) {
            System.out.println("Wrong input, please try again.\n");
            MainMenu mm = new MainMenu();
            mm.viewMainMenu();
        }
    }

    public void changeViewMainMenu(int option) {

        switch (option) {
            case 0:
                CreateAccountMenu cam = new CreateAccountMenu();
                cam.viewCreateAccountMenu();
                break;
            case 1:
                LoginMenu lm = new LoginMenu();
                lm.viewLoginMenu();
                break;
            case 2:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Something went wrong");
                break;
        }
    }


}
