import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import view.MainMenu;
import view.SearchFlightsMenu;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        MainMenu mainMenu = new MainMenu();
        mainMenu.viewMenu();

//
//        SearchFlightsMenu searchFlightsMenu = new SearchFlightsMenu();
//        searchFlightsMenu.viewMenu();
    }
}
