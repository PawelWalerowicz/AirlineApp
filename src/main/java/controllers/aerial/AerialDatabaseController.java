package controllers.aerial;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utilities.InputOutputTools.loadDatabaseIntoScanner;
import static utilities.ResourcesIndex.*;

public class AerialDatabaseController {

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

    private static List<String[]> getItemsFromDatabase(String databasePath) {
        Scanner scanner = loadDatabaseIntoScanner(databasePath);
        List<String[]> allItems = new ArrayList<String[]>();
        while (scanner.hasNextLine()) {
            String item = scanner.nextLine();

//            String[] splitedItem = item.replace("\"", "").split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            String[] splitedItem = item.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            String[] splitedItemWithoutQuotes = removeQuotes(splitedItem);
            allItems.add(splitedItemWithoutQuotes);
        }
        return allItems;
    }

    private static String[] removeQuotes(String[] strings) {
        String[] stringsWithoutQuotes = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            stringsWithoutQuotes[i] = strings[i].replace("\"", "");
        }
        return stringsWithoutQuotes;
    }
}

