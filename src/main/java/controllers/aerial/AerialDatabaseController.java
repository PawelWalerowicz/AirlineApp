package controllers.aerial;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utilities.InputOutputTools.loadDatabaseIntoScanner;
import static utilities.ResourcesIndex.AIRPORTS_DATABASE_FILE;
import static utilities.ResourcesIndex.COUNTRIES_DATABASE_FILE;

public class AerialDatabaseController {

    public static List<String[]> getCountriesFromDatabase() {
        return getItemsFromDatabase(COUNTRIES_DATABASE_FILE);
    }

    public static List<String[]> getAirportsFromDatabase() {
        return getItemsFromDatabase(AIRPORTS_DATABASE_FILE);
    }

    private static List<String[]> getItemsFromDatabase(String databasePath) {
        Scanner scanner = loadDatabaseIntoScanner(databasePath);
        List<String[]> allItems = new ArrayList<String[]>();
        while (scanner.hasNextLine()) {
            String item = scanner.nextLine().toString();
            String[] splitedItem = item.replace("\"", "").split(",");
            allItems.add(splitedItem);
        }
        return allItems;
    }
}

