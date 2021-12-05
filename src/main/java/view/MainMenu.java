package view;

import controllers.MainMenuController;

import static utilities.InputOutputTools.printTextFromFile;
import static utilities.ResourcesIndex.MAIN_MENU_WELCOME_MESSAGE;


public class MainMenu {

    public void viewMenu() {
        System.out.println();
        printTextFromFile(MAIN_MENU_WELCOME_MESSAGE);
        MainMenuController mainMenuController = new MainMenuController();

    }

}
