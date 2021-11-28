import view.MainMenu;
import view.SearchFlightsMenu;

public class Main {
    public static void main(String[] args) {
        System.out.println();
//        MainMenu mainMenu = new MainMenu();
//        mainMenu.viewMenu();

        SearchFlightsMenu searchFlightsMenu = new SearchFlightsMenu();
        searchFlightsMenu.viewMenu();

    }
}