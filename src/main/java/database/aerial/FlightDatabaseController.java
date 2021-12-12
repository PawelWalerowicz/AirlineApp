package database.aerial;

import model.*;
import model.Price;
import model.TimeInterval;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static utilities.InputOutputTools.loadDatabaseIntoScanner;
import static utilities.ResourcesIndex.FLIGHTS_DATABASE_FILE;

public class FlightDatabaseController {
    Account account;
    Flight flight;

    private final int DEPARTURE_AIRPORT_IATA_INDEX = 1;
    private final int LANDING_AIRPORT_IATA_INDEX = 2;
    private final int AIRLINE_NAME_INDEX = 3;
    private final int DEPARTURE_DATE_INDEX = 4;
    private final int FLIGHT_HOURS_INDEX = 5;
    private final int FLIGHT_MINUTES_INDEX = 6;
    private final int LANDING_DATE_INDEX = 7;
    private final int PRICE_INDEX = 8;


    public FlightDatabaseController(Account account, Flight flight) {
        this.account = account;
        this.flight = flight;
    }


    public FlightDatabaseController(Account account) {
        this.account = account;
    }

    public void saveFlightToDatabase() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FLIGHTS_DATABASE_FILE, true));
            writer.println(flightToDatabaseString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printAllFlights() {
        Map<Integer, Flight> allFlights = getallFlightForAccount();
        System.out.println("All your reserved flights: ");
        allFlights.forEach((index, flight) -> System.out.println(index + ". " + flight.toString()));
    }

    private Map<Integer,Flight> getallFlightForAccount() {
        List<String[]> allFlights = getFlightsFromDatabase();
        Map<Integer,Flight> allFlightsForAccount = new HashMap<>();
        int index=1;
        for(String[] flightParameters:allFlights) {
            int userIndex = Integer.parseInt(flightParameters[0]);
            if(userIndex==account.getId()) {
                Flight flight = getFlightFromParameters(flightParameters);
                allFlightsForAccount.put(index,flight);
                index++;
            }
        }
        return allFlightsForAccount;
    }

    public List<String[]> getFlightsFromDatabase() {
        Scanner scanner = loadDatabaseIntoScanner(FLIGHTS_DATABASE_FILE);
        List<String[]> allFlights = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String item = scanner.nextLine();
            String[] flightAttributes = item.split("\\|");
            allFlights.add(flightAttributes);
        }
        return allFlights;
    }

    private Flight getFlightFromParameters(String[] flightParameters) {
        Airline airline = Airline.createAirline(flightParameters[AIRLINE_NAME_INDEX]);
        Airport departureAirport = new Airport(flightParameters[DEPARTURE_AIRPORT_IATA_INDEX]);
        Airport landingAirport = new Airport(flightParameters[LANDING_AIRPORT_IATA_INDEX]);
        Route route = new Route(airline, departureAirport,landingAirport);

        Calendar departureDate = new GregorianCalendar();
        departureDate.setTimeInMillis(Long.parseLong(flightParameters[DEPARTURE_DATE_INDEX]));

        Calendar landingDate = new GregorianCalendar();
        landingDate.setTimeInMillis(Long.parseLong((flightParameters[LANDING_DATE_INDEX])));
        int hours = Integer.parseInt(flightParameters[FLIGHT_HOURS_INDEX]);
        int minutes = Integer.parseInt(flightParameters[FLIGHT_MINUTES_INDEX]);

        TimeInterval flightTime = new TimeInterval(new int[]{hours, minutes});

        Price price = new Price(Double.parseDouble(flightParameters[PRICE_INDEX]));

        Flight flight = new Flight(route, departureDate, flightTime, landingDate, price);
        return flight;
    }

    private String flightToDatabaseString() {
        Route route = flight.getRoute();
        return account.getId() +"|"
                + route.getSourceAirport().getIATA() + "|"
                + route.getDestinationAirport().getIATA() + "|"
                + route.getAirline().getName() + "|"
                + flight.getDepartureDate().getTime().getTime() + "|"
                + flight.getFlightTime().getHours() + "|"
                + flight.getFlightTime().getMinutes() + "|"
                + flight.getLandingDate().getTime().getTime() + "|"
                + flight.getPrice().getPrice();
    }
}