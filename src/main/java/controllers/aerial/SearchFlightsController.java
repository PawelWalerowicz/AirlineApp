package controllers.aerial;

import model.Airport;
import model.Flight;
import model.Route;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static controllers.aerial.AerialDatabaseController.getRoutesFromDatabase;

import static model.Route.DESTINATION_AIRPORT_IATA_POSITION;
import static model.Route.SOURCE_AIRPORT_IATA_POSITION;
import static utilities.ClearConsole.cleanConsole;
import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.createFakeData.createFakeDate;



public class SearchFlightsController {
    boolean proceed = true;
    List<Route> availableRoutes;
    Calendar earliestDate;
    Calendar latestDate;

    SimpleDateFormat sdfWithTime = new SimpleDateFormat("E dd-MM-yyyy, HH:mm");
    SimpleDateFormat sdfWithoutTime = new SimpleDateFormat("E dd-MM-yyyy");

    public SearchFlightsController() {
        searchFlights();
    }

    public void searchFlights() {
        searchForRoutes();
        askForDates();
        List<Flight> allFlights = generateFlights();
        Map<Integer,Flight> sortedFlights = MapAllFlights(allFlights);
        printFlights(sortedFlights);
    }

    private void searchForRoutes() {
        AirportSearchController airportSearchController = new AirportSearchController();
        List<Airport> departureAirport = airportSearchController.askForAirports("Departure");
        List<Airport> landingAirport = airportSearchController.askForAirports("Landing");
        proceed = airportSearchController.getProceed();
        availableRoutes = searchForFlightsInDatabase(departureAirport, landingAirport);
    }

    private void askForDates() {
        DateController dateController = new DateController();
        dateController.setProceed(proceed);
        earliestDate = dateController.askForEarliestDate();
        latestDate = dateController.askForLatestDate(earliestDate);
        proceed = dateController.getProceed();

    }

    private List<Flight> generateFlights() {
        List<Flight> allFlights = new ArrayList<>();
        if(proceed) {
            for(Route route:availableRoutes) {
                Calendar randomDate = createFakeDate(earliestDate, latestDate);
                Flight flight = new Flight(route,randomDate);
                allFlights.add(flight);
            }
        }
        return allFlights;
    }

    private Map<Integer,Flight> MapAllFlights(List<Flight> allFlights) {
        List<Flight> sortedFlights = sortFlights(allFlights);
        Map<Integer,Flight> flightWithIndexes = new HashMap<>();
        int index=0;
        for(int i=index;i< sortedFlights.size();i++) {
            flightWithIndexes.put(i+1, sortedFlights.get(i));
        }
        return flightWithIndexes;
    }

    private List<Flight> sortFlights(List<Flight> flightList) {
        return flightList.stream().sorted(Comparator.comparing(Flight::getDepartureDate)).collect(Collectors.toList());
    }


    private void printFlights(Map<Integer,Flight> flightList) {
        if(proceed) {
            cleanConsole();
            System.out.println("All available flights between " + sdfWithoutTime.format(earliestDate.getTime()) + " and " + sdfWithoutTime.format(latestDate.getTime()) + ":\n");
            flightList.forEach((key, value) -> System.out.println(key + ". " + value));

            System.out.println("Please choose flight number to reserve ticket: ");
            int chosenOption = readUserIntegerInput(flightList.size());
            System.out.println("Thank you, ticket has been added to your account."); //todo: No, it's not, should add that up. Also give user an option to quit or search again
        }
    }

    private List<Route> searchForFlightsInDatabase(List<Airport> departureAirport, List<Airport> landingAirport) {
        List<Route> allRoutesOnPath = new ArrayList<>();
        if(proceed) {
            System.out.println("Searching for routes...");
            List<String[]> allRoutesStartingInDepartureAirports = getAllRoutesStartingOnAirport(departureAirport);
            allRoutesOnPath = getAllRoutesOnPath(allRoutesStartingInDepartureAirports, landingAirport);

            if(allRoutesOnPath.isEmpty()) {
                System.out.println("No direct flights found on this route. Please try again.");
                cleanConsole();
                searchFlights();
            } else {
                System.out.println("Found " + allRoutesOnPath.size() + " direct connetions.");
            }
        }
        return allRoutesOnPath;
    }

    private List<String[]> getAllRoutesStartingOnAirport(List<Airport> departureAirports) {
        List<String[]> allRoutesStartingInDepartureAirports = new ArrayList<>();
        for (Airport airport:departureAirports) {
            List<String[]> allRoutes = getRoutesFromDatabase();
            String startAirportIATA = airport.getIATA().toString();
            for (String[] route : allRoutes) {
                if (route[SOURCE_AIRPORT_IATA_POSITION].equals(startAirportIATA)) {
                    allRoutesStartingInDepartureAirports.add(route);
                }
            }
        }
        return allRoutesStartingInDepartureAirports;
    }

    private List<Route> getAllRoutesOnPath(List<String[]> allRoutestWithSetDepartureAirports, List<Airport> landingAirports) {
        List<Route> allRoutesOnPath = new ArrayList<>();
        for(Airport endAirport:landingAirports) {
            for(String[] route:allRoutestWithSetDepartureAirports) {
                String endAirportIATA = endAirport.getIATA().toString();
                if(route[DESTINATION_AIRPORT_IATA_POSITION].equals(endAirportIATA)) {
                    Airport startAirport = new Airport(route[SOURCE_AIRPORT_IATA_POSITION]);
                    Route currentRoute = new Route (startAirport,endAirport);
                    allRoutesOnPath.add(currentRoute);
                }
            }
        }
        return allRoutesOnPath;
    }

}
