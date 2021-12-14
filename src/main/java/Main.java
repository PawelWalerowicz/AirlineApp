import menus.TerminalMenu;
import menus.mainMenu.MainMenu;
import model.Flight;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TerminalMenu mainMenu = MainMenu.getInstance();
        mainMenu.viewMenu();
    }


}



