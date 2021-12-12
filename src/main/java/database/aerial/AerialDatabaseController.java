package database.aerial;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utilities.InputOutputTools.loadDatabaseIntoScanner;
import static utilities.ResourcesIndex.*;

public class AerialDatabaseController {

    private static final int CITY_POSITION = 2;

    public static List<String[]> getCountriesFromDatabase() {
        return getItemsFromDatabase(COUNTRIES_DATABASE_FILE);
    }

    public static List<String[]> getAirportsFromDatabase() {
        return getItemsFromDatabase(AIRPORTS_DATABASE_FILE);
    }

    public static List<String[]> getAirlinesFromDatabase() {
        return getItemsFromDatabase(AIRLINES_DATABASE_FILE);
    }

    public static List<String[]> getRoutesFromDatabase() {
        return getItemsFromDatabase(ROUTES_DATABASE_FILE);
    }

    public static List<String[]> getFlightsFromDatabase() {
        return getItemsFromDatabase(FLIGHTS_DATABASE_FILE);
    }

    private static List<String[]> getItemsFromDatabase(String databasePath) {
        Scanner scanner = loadDatabaseIntoScanner(databasePath);
        List<String[]> allItems = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String item = scanner.nextLine();
            String[] partedItem = item.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            String[] partedItemWithoutQuotes = removeQuotes(partedItem);
            allItems.add(partedItemWithoutQuotes);
        }
        return allItems;
    }

    public static List<String> getCitiesFromDatabase() {
        Scanner scanner = loadDatabaseIntoScanner(AIRPORTS_DATABASE_FILE);
        List<String> allItems = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String item = scanner.nextLine();
            String[] partedItem = item.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            String[] partedItemWithoutQuotes = removeQuotes(partedItem);
            allItems.add(partedItemWithoutQuotes[CITY_POSITION]);
        }
        return allItems;
    }

    public static String[] removeQuotes(String[] strings) {
        String[] stringsWithoutQuotes = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            stringsWithoutQuotes[i] = strings[i].replace("\"", "");
        }
        return stringsWithoutQuotes;
    }
}

