package menus.mainMenu;

import menus.TerminalMenu;

import static utilities.InputOutputTools.printTextFromFile;
import static utilities.ResourcesIndex.MAIN_MENU_WELCOME_MESSAGE;


public class MainMenu implements TerminalMenu {
    private static MainMenu mainMenuInstance;

    public static TerminalMenu getInstance() {
        if(mainMenuInstance==null) {
            mainMenuInstance = new MainMenu();
        }
        return mainMenuInstance;
    }

    private MainMenu() {

    }

    public void viewMenu() {
        System.out.println();
        printTextFromFile(MAIN_MENU_WELCOME_MESSAGE);
        MainMenuController mainMenuController = new MainMenuController();

    }

}
