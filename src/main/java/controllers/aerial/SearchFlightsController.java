package controllers.aerial;

import model.Airport;
import model.Route;

import java.text.SimpleDateFormat;
import java.util.*;

import static controllers.aerial.AerialDatabaseController.getRoutesFromDatabase;
import static model.Airport.getAirportsInCity;
import static model.Airport.getAirportsInCountry;
import static model.Country.isCity;
import static model.Country.isCountry;
import static model.Route.DESTINATION_AIRPORT_IATA_POSITION;
import static model.Route.SOURCE_AIRPORT_IATA_POSITION;
import static utilities.InputValidator.*;

public class SearchFlightsController {
    boolean proceed = true;
    Scanner scanner;

    SimpleDateFormat sdf = new SimpleDateFormat("E, dd-MM-yyyy, HH:mm");
    SimpleDateFormat sdf2 = new SimpleDateFormat("E, dd-MM-yyyy, HH:mm:ss:SS");

    public SearchFlightsController() {
        searchFlights();
    }

    public void searchFlights() {
        List<Airport> startAirports = askForAirports("Departure"); //todo: later extend a tool to search by country and list all airports
        List<Airport> endAirports = askForAirports("Landing");
        System.out.println("Searching for connections.");
        List<Route> availableRoutes = searchForFlightsInDatabase(startAirports, endAirports);
        if (availableRoutes.size() > 0) {
            System.out.println("Listing all " + availableRoutes.size() + " connections:");
            for (Route route : availableRoutes) {
                System.out.println(route.toString());

            }
        }


//        Calendar earliestDate = askForEarliestDate();
//        Calendar latestDate = askForLatestDate(earliestDate);

//        Calendar randomDate = createFakeDate(earliestDate, latestDate);
//        System.out.println(sdf.format(randomDate.getTime()));

        //todo: create specific class for route with price and time of departure/landing

    }

    private Calendar askForEarliestDate() {
        System.out.println("Please enter earliest possible date for departure (date format DD-MM-YYYY), or \"Q\" to quit");
        return askForDate();
    }

    private Calendar askForLatestDate(Calendar earliestDate) {
        System.out.println("Please enter latest possible date for departure (date format DD-MM-YYYY), or \"Q\" to quit");
        Calendar latestDate = null;
        do {
            latestDate = askForDate();
            if (latestDate.before(earliestDate)) {
                System.out.println("Latest date cannot be set before earliest date (" + earliestDate.getTime().toString() + "). Please try again.");
            }
        } while (!latestDate.after(earliestDate));

        return latestDate;
    }

    private Calendar askForDate() {
        scanner = new Scanner(System.in);
        Calendar chosenDate = new GregorianCalendar();
        boolean proceed = false;
        String date = null;
        do {
            date = scanner.nextLine();
            proceed = checkForQuit(date);
            if (!isDateValid(date)) {
                System.out.println("Please enter correct date (date format DD-MM-YYYY).");
            }
            if (!isDateAfterNow(date)) {
                System.out.println("Chosen date cannot be current day or before. Please try again.");
            }
        } while (proceed & (!isDateValid(date) || !isDateAfterNow(date)));

        if (proceed) {
            chosenDate = getDateFromString(date);
        }

        return chosenDate;

    }

    private boolean isDateAfterNow(String date) {
        if (isDateValid(date)) {
            Calendar departureDate = getDateFromString(date);
            Calendar currentDate = new GregorianCalendar();
            if (departureDate.after(currentDate)) {
                return true;
            } else {
                return false;
            }
        } else return false;

    }

    private Calendar getDateFromString(String date) {
        String[] dayMonthYearAsString = date.split("-");
        Calendar calendar = new GregorianCalendar();
        int[] dayMonthYearAsInteger = new int[3];
        for (int i = 0; i < dayMonthYearAsString.length; i++) {
            dayMonthYearAsInteger[i] = Integer.parseInt(dayMonthYearAsString[i]);
        }
        calendar.set(dayMonthYearAsInteger[2], dayMonthYearAsInteger[1] - 1, dayMonthYearAsInteger[0], 0, 0, 0);
        return calendar;
    }

    private List<Route> searchForFlightsInDatabase(List<Airport> startAirports, List<Airport> endAirports) {
        List<Route> allRoutesOnPath = new ArrayList<>();
        List<String[]> allRoutesStartingInStartAirports = new ArrayList<>();

        for (Airport airport:startAirports) {
            List<String[]> allRoutes = getRoutesFromDatabase();
            String startAirportIATA = airport.getIATA().toString();
            for (String[] route : allRoutes) {
                if (route[SOURCE_AIRPORT_IATA_POSITION].equals(startAirportIATA)) {
                    allRoutesStartingInStartAirports.add(route);
                }
            }
        }

        for(Airport endAirport:endAirports) {
            for(String[] route:allRoutesStartingInStartAirports) {
                String endAirportIATA = endAirport.getIATA().toString();
                if(route[DESTINATION_AIRPORT_IATA_POSITION].equals(endAirportIATA)) {
                    Airport startAirport = new Airport(route[SOURCE_AIRPORT_IATA_POSITION]);
                    Route currentRoute = new Route (startAirport,endAirport);
                    allRoutesOnPath.add(currentRoute);
                }
            }
        }

        System.out.println("Found " + allRoutesOnPath.size() + " connections.");
        return allRoutesOnPath;
    }

    private List<Airport> askForAirports(String departureOrLanding) {

        scanner = new Scanner(System.in);
        List<Airport> airports = new ArrayList<>();
        String input = null;
        if (proceed) {
            do {
                System.out.print(departureOrLanding + " airport (name or IATA code), or " + departureOrLanding.toLowerCase() + " city or country (or Q to quit): ");
                input = scanner.nextLine();
                proceed = checkForQuit(input);
            } while (proceed && !isAirportOrCountryValid(input));
        }
        if (proceed) {
            if (isCountry(input) || isCity(input)) {
                System.out.println("Listing all airports in " + input + ", it may take a while, please be patient.");
                if(isCountry(input)) {
                    airports = getAirportsInCountry(input);
                } else {
                    airports = getAirportsInCity(input);
                }
            } else {
                Airport airport = new Airport(input);
                airports.add(airport);
            }
        }
        System.out.println(airports.size() + " airports found in " + input);
        return airports;
    }

}
