package controllers.aerial;

import model.Account;
import model.Airport;
import model.Flight;
import model.Route;
import view.MainMenu;
import view.UserMenu;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static controllers.aerial.AerialDatabaseController.getRoutesFromDatabase;
import static model.Route.DESTINATION_AIRPORT_IATA_POSITION;
import static model.Route.SOURCE_AIRPORT_IATA_POSITION;
import static utilities.ClearConsole.cleanConsole;
import static utilities.InputOutputTools.isValidFlightOption;
import static utilities.createFakeData.*;


public class SearchFlightsController {
    boolean proceed = true;
    Calendar earliestDate;
    Calendar latestDate;
    List<Route> availableRoutes;
    Map<Integer, Flight> availableFlights;
    Account account;

    SimpleDateFormat sdfWithTime = new SimpleDateFormat("E dd-MM-yyyy, HH:mm");
    SimpleDateFormat sdfWithoutTime = new SimpleDateFormat("E dd-MM-yyyy");

    public SearchFlightsController() {
        searchFlights();
    }

    public SearchFlightsController(Account account) {
        this.account = account;
        searchFlights();
    }

    public void searchFlights() {
        searchForRoutes();
        askForDates();
        generateFlights();
        printFlights(availableFlights);
    }

    private void searchForRoutes() {
        AirportSearchController airportSearchController = new AirportSearchController();
        List<Airport> departureAirport = null;
        List<Airport> landingAirport = null;
        if (proceed) {
            departureAirport = airportSearchController.askForAirports("Departure");
        } else quit();
        if (proceed) {
            landingAirport = airportSearchController.askForAirports("Landing");
        } else quit();
        proceed = airportSearchController.getProceed();
        availableRoutes = searchForFlightsInDatabase(departureAirport, landingAirport);
    }

    private void askForDates() {
        DateController dateController = new DateController();
        dateController.setProceed(proceed);
        if (proceed) {
            earliestDate = dateController.askForEarliestDate();
        } else quit();
        if (proceed) {
            latestDate = dateController.askForLatestDate(earliestDate);
        } else quit();
        proceed = dateController.getProceed();
    }

    private void generateFlights() {
        List<Flight> allFlights = new ArrayList<>();
        if (proceed) {
            int amountOfFlights = 0;
            int requiredAmountOfFlights = getEstimatedAmountOfFlights();
            while (amountOfFlights < requiredAmountOfFlights) {
                Route route = chooseRandomRoute(availableRoutes);
                Calendar randomDate = createFakeDate(earliestDate, latestDate);
                Flight flight = new Flight(route, randomDate);
                allFlights.add(flight);
                amountOfFlights++;
            }
        } else quit();
        availableFlights = MapAllFlights(allFlights);
    }

    private Map<Integer, Flight> MapAllFlights(List<Flight> allFlights) {
        List<Flight> sortedFlights = sortFlights(allFlights);
        Map<Integer, Flight> flightWithIndexes = new HashMap<>();
        int index = 0;
        for (int i = index; i < sortedFlights.size(); i++) {
            flightWithIndexes.put(i + 1, sortedFlights.get(i));
        }
        return flightWithIndexes;
    }

    private List<Flight> sortFlights(List<Flight> flightList) {
        return flightList.stream().sorted(Comparator.comparing(Flight::getDepartureDate)).collect(Collectors.toList());
    }


    private void printFlights(Map<Integer, Flight> flightList) {
        if (proceed) {
            cleanConsole();
            System.out.println("All available flights between " + sdfWithoutTime.format(earliestDate.getTime()) + " and " + sdfWithoutTime.format(latestDate.getTime()) + " (sorted by departure date):\n");
            flightList.forEach((key, value) -> System.out.println(key + ". " + value));
            if (account == null) {
                returnToMainMenu();
            } else {
                chooseFlight();
            }
        } else quit();
    }

    private List<Route> searchForFlightsInDatabase(List<Airport> departureAirport, List<Airport> landingAirport) {
        List<Route> allRoutesOnPath = new ArrayList<>();
        if (proceed) {
            System.out.println("Searching for routes...");
            List<String[]> allRoutesStartingInDepartureAirports = getAllRoutesStartingOnAirport(departureAirport);
            allRoutesOnPath = getAllRoutesOnPath(allRoutesStartingInDepartureAirports, landingAirport);

            if (allRoutesOnPath.isEmpty()) {
                System.out.println("No direct flights found on this route. Please try again.");
                cleanConsole();
                searchFlights();
            } else {
                System.out.println("Found " + allRoutesOnPath.size() + " direct connections.");
            }
        } else quit();
        return allRoutesOnPath;
    }

    private void chooseFlight() {     //TODO: add option to purchase multiple tickets
        boolean validOption;
        int chosenOption = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose flight number to reserve ticket, press 'Q' to quit, or press Enter to search again: ");
        do {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                quit();
                break;
            } else if (input.isEmpty()) {
                searchFlights();
                break;
            } else {
                try {
                    chosenOption = Integer.parseInt(input);
                } catch (NumberFormatException exc) {
                } finally {
                    validOption = isValidFlightOption(chosenOption, availableFlights.size());
                }
            }
        } while (!validOption);
        addFlightToAccount(availableFlights.get(chosenOption));
        System.out.println("Flight added to account.");
        quit();
    }

    private void addFlightToAccount(Flight flight) {
        FlightDatabaseController flightDatabaseController = new FlightDatabaseController(account, flight);
        flightDatabaseController.saveFlightToDatabase();
    }

    private void returnToMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press 'enter' to return to Main Menu. If you would like to reserve a ticket, please login to your account.");
        scanner.nextLine();
        MainMenu mainMenu = new MainMenu();
        mainMenu.viewMenu();
    }

    private void quit() {
        if (account == null) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.viewMenu();
        } else {
            UserMenu userMenu = new UserMenu(account);
            userMenu.viewMenu();
        }
    }


    //todo: refactor these methods into RouteController class:
    private List<String[]> getAllRoutesStartingOnAirport(List<Airport> departureAirports) {
        List<String[]> allRoutesStartingInDepartureAirports = new ArrayList<>();
        for (Airport airport : departureAirports) {
            List<String[]> allRoutes = getRoutesFromDatabase();
            String startAirportIATA = airport.getIATA();
            for (String[] route : allRoutes) {
                if (route[SOURCE_AIRPORT_IATA_POSITION].equals(startAirportIATA)) {
                    allRoutesStartingInDepartureAirports.add(route);
                }
            }
        }
        return allRoutesStartingInDepartureAirports;
    }

    private List<Route> getAllRoutesOnPath(List<String[]>
                                                   allRoutesWithSetDepartureAirports, List<Airport> landingAirports) {
        List<Route> allRoutesOnPath = new ArrayList<>();
        for (Airport endAirport : landingAirports) {
            for (String[] route : allRoutesWithSetDepartureAirports) {
                String endAirportIATA = endAirport.getIATA();
                if (route[DESTINATION_AIRPORT_IATA_POSITION].equals(endAirportIATA)) {
                    Airport startAirport = new Airport(route[SOURCE_AIRPORT_IATA_POSITION]);
                    Route currentRoute = new Route(startAirport, endAirport);
                    allRoutesOnPath.add(currentRoute);
                }
            }
        }
        return allRoutesOnPath;
    }

    private int getEstimatedAmountOfFlights() {
        return createProbableAmountOfFlightsInTimePeriod(earliestDate, latestDate);
    }

}
