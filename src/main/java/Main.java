import view.MainMenu;
import view.SearchFlightsMenu;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static controllers.aerial.AerialDatabaseController.removeQuotes;
import static utilities.InputOutputTools.loadDatabaseIntoScanner;
import static utilities.ResourcesIndex.AIRPORTS_DATABASE_FILE;
import static utilities.ResourcesIndex.ROUTES_DATABASE_FILE;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        MainMenu mainMenu = new MainMenu();
        mainMenu.viewMenu();

//        SearchFlightsMenu searchFlightsMenu = new SearchFlightsMenu();
//        searchFlightsMenu.viewMenu();
    }
}