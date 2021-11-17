package controllers.aerial;

import model.Airport;
import model.Country;
import model.Route;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import static controllers.aerial.AerialDatabaseController.getAirportsFromDatabase;
import static controllers.aerial.AerialDatabaseController.getRoutesFromDatabase;
import static model.Airport.reciteAirportsInCountry;
import static model.Country.isCountry;
import static utilities.InputValidator.*;

public class SearchFlightsController {
    boolean proceed = true;
    Scanner scanner;

    public SearchFlightsController() {
        searchFlights();
    }

    public void searchFlights() {
        List<Airport> startAirports = askForAirports("Departure"); //todo: later extend a tool to search by country and list all airports
        List<Airport> endAirports = askForAirports("Landing");
        System.out.println("Airports in " + startAirports.get(0).getCountry() + " :");
        for(Airport airport:startAirports) {
            System.out.println(airport.toString());
        }
        System.out.println();
        System.out.println("Airports in " + endAirports.get(0).getCountry() + " :");
        for(Airport airport:endAirports) {
            System.out.println(airport.toString());
        }
//        Calendar earliestDate = askForDate();
//        Calendar latestDate = askForDate();

        List<Route> availableRoutes = searchForFlightsInDatabase(startAirports, endAirports);


    }


    private List<Route> searchForFlightsInDatabase(List<Airport> startAirpots, List<Airport> endAirports) {
        List<Route> allRoutesOnPath = new ArrayList<>();
        Route route = new Route(startAirpots.get(0),endAirports.get(0));
        route.exist();
        allRoutesOnPath.add(route);
        return allRoutesOnPath;
    }

    private List<Airport> askForAirports(String departureOrLanding) {
        scanner = new Scanner(System.in);
        List<Airport> airports = new ArrayList<>();
        String input = null;
        if (proceed) {
            do {
                System.out.print(departureOrLanding + " airport (name or IATA code), or departure country (or Q to quit): ");
                input = scanner.nextLine();
                proceed = checkForQuit(input);
            } while (proceed && !isAirportOrCountryValid(input));
        }
        if(proceed) {
            if(isCountry(input)) {
                airports = reciteAirportsInCountry(input);
            } else {
                Airport airport = new Airport(input);
                airports.add(airport);
            }
        }

        return airports;
    }

}
