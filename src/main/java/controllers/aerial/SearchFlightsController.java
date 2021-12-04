package controllers.aerial;

import model.Airport;
import model.Flight;
import model.Route;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static controllers.aerial.AerialDatabaseController.getRoutesFromDatabase;
import static model.Airport.getAirportsInCity;
import static model.Airport.getAirportsInCountry;
import static model.Country.isCity;
import static model.Country.isCountry;
import static model.Route.DESTINATION_AIRPORT_IATA_POSITION;
import static model.Route.SOURCE_AIRPORT_IATA_POSITION;
import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.InputValidator.*;
import static utilities.createFakeData.createFakeDate;



public class SearchFlightsController {
    boolean proceed = true;
    Scanner scanner;
    List<Route> availableRoutes;
    Calendar earliestDate;
    Calendar latestDate;

    SimpleDateFormat sdfWithTime = new SimpleDateFormat("E dd-MM-yyyy, HH:mm");
    SimpleDateFormat sdfWithoutTime = new SimpleDateFormat("E dd-MM-yyyy");

    public SearchFlightsController() {
        searchFlights();
    }

    public void searchFlights() {
        AirportSearchController asc = new AirportSearchController();
        List<Airport> startAirports = asc.askForAirports("Departure");
        List<Airport> endAirports = asc.askForAirports("Landing");
        proceed = asc.getProceed();
        availableRoutes = searchForFlightsInDatabase(startAirports, endAirports);

        DateController dc = new DateController();
        dc.setProceed(proceed);
        this.earliestDate = dc.askForEarliestDate();
        this.latestDate = dc.askForLatestDate(earliestDate);
        proceed = dc.getProceed();
        List<Flight> allFlights = generateFlights();
        Map<Integer,Flight> sortedFlights = sortFlightsByDate(allFlights);
        printFlights(sortedFlights);

    }

    private Map<Integer,Flight> sortFlightsByDate(List<Flight> allFlights) {
        List<Flight> sortedFlights = allFlights.stream().sorted(Comparator.comparing(Flight::getDepartureDate)).collect(Collectors.toList());
        Map<Integer,Flight> flightWithIndexes = new HashMap<>();
        int index=1;
        for(int i=index;i< sortedFlights.size();i++) {
            flightWithIndexes.put(i, sortedFlights.get(i));
        }
        return flightWithIndexes;
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

    private void printFlights(Map<Integer,Flight> flightList) {
        if(proceed) {
            System.out.println("All available flights between " + sdfWithoutTime.format(earliestDate.getTime()) + " and " + sdfWithoutTime.format(latestDate.getTime()) + ":\n");
            for(int i=1;i< flightList.size();i++) {
                System.out.println(i + ". " + flightList.get(i).toString());
            }
            System.out.println("Please choose flight number to reserve ticket.");
            int chosenOption = readUserIntegerInput(flightList.size());
            System.out.println("Thank you, ticket has been added to your account."); //todo: No, it's not, should add that up. Also give user an option to quit or search again
        }
    }


    private List<Route> searchForFlightsInDatabase(List<Airport> startAirports, List<Airport> endAirports) {
        List<Route> allRoutesOnPath = new ArrayList<>();
        List<String[]> allRoutesStartingInStartAirports = new ArrayList<>();

        if(proceed) {
            System.out.println("Searching for connections.");
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

            if(allRoutesOnPath.size()==0) {
                System.out.println("No direct flights found on this route. Please try again.");
                proceed=false;  //todo:here it get some NumberFormatExpception, not sure what i get to next
            } else {
                System.out.println("Found " + allRoutesOnPath.size() + " direct connetions.");
            }
        }
        return allRoutesOnPath;
    }

}
