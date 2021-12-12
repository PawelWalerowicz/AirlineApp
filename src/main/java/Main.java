import menus.TerminalMenu;
import menus.mainMenu.MainMenu;

public class Main {
    public static void main(String[] args) {
        TerminalMenu mainMenu = MainMenu.getInstance();
        mainMenu.viewMenu();
    }


}



