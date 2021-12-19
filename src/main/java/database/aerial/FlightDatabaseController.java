package database.aerial;

import menus.Singleton;
import model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

import static database.account.AccountDatabaseController.getAccountById;
import static database.aerial.AerialDatabaseController.getFlightsFromDatabase;
import static utilities.InputOutputTools.loadDatabaseIntoScanner;
import static utilities.ResourcesIndex.FLIGHTS_DATABASE_FILE;

public class FlightDatabaseController implements Singleton {

    private final int ACCOUNT_INDEX = 0;
    private final int DEPARTURE_AIRPORT_IATA_INDEX = 1;
    private final int LANDING_AIRPORT_IATA_INDEX = 2;
    private final int AIRLINE_NAME_INDEX = 3;
    private final int DEPARTURE_DATE_INDEX = 4;
    private final int FLIGHT_HOURS_INDEX = 5;
    private final int FLIGHT_MINUTES_INDEX = 6;
    private final int LANDING_DATE_INDEX = 7;
    private final int PRICE_INDEX = 8;
    private final int AMOUNT_INDEX = 9;

    private static FlightDatabaseController flightDatabaseControllerInstance;

    public static FlightDatabaseController getInstance() {
        if(flightDatabaseControllerInstance==null) {
            flightDatabaseControllerInstance = new FlightDatabaseController();
        }
        return flightDatabaseControllerInstance;
    }
    private FlightDatabaseController() {
    }

    public void saveReservedFlightToDatabase(ReservedFlight reservedFlight) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FLIGHTS_DATABASE_FILE, true));
            writer.println(reservedFlightToDatabaseString(reservedFlight));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printAllFlights(Map<Integer,ReservedFlight> allFlights) {
        allFlights.forEach((index, reservedFlight) -> System.out.println(index + ". " + reservedFlight.toString()));
    }

    public Map<Integer,ReservedFlight> getAllFlightsForAccount(Account account) {
        List<String[]> allFlights = getReservedFlightsFromDatabase();
        Map<Integer,ReservedFlight> allFlightsForAccount = new HashMap<>();
        int index=1;
        for(String[] flightParameters:allFlights) {
            int userIndex = Integer.parseInt(flightParameters[0]);
            if(userIndex==account.getId()) {
                ReservedFlight reservedFlight = getReservedFlightFromParameters(flightParameters);
                allFlightsForAccount.put(index,reservedFlight);
                index++;
            }
        }
        return sortFlightsByDepartureDate(allFlightsForAccount);
    }

    private Map<Integer, ReservedFlight> sortFlightsByDepartureDate(Map<Integer, ReservedFlight> allFlights) {
        List<ReservedFlight> reservedFlights = new ArrayList<>(allFlights.values());
        reservedFlights = reservedFlights.stream().sorted(Comparator.comparing(reservedFlight -> reservedFlight.getFlight().getDepartureDate())).collect(Collectors.toList());
        Map<Integer, ReservedFlight> sortedReservedFlights = new LinkedHashMap<>();
        for(int i=1;i<reservedFlights.size()+1;i++) {
            sortedReservedFlights.put(i,reservedFlights.get(i-1));
        }
        return sortedReservedFlights;
    }

    public List<String[]> getReservedFlightsFromDatabase() {
        Scanner scanner = loadDatabaseIntoScanner(FLIGHTS_DATABASE_FILE);
        List<String[]> allFlights = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String item = scanner.nextLine();
            String[] flightAttributes = item.split("\\|");
            allFlights.add(flightAttributes);
        }
        return allFlights;
    }

    public void editAmountOfTicketsInDatabase(ReservedFlight reservedFlight, int newAmount) {
        List<String[]> allFlights = getFlightsFromDatabase();
        for (String[] currentReservedFlightString : allFlights) {
            ReservedFlight currentReservedFlight = getReservedFlightFromParameters(currentReservedFlightString);
            if (currentReservedFlight.equals(reservedFlight)) {
                currentReservedFlight.setAmount(newAmount);
            }
        }
    }
//            Account currentFlight = convertDatabaseStringToAccount(user);
//            if (currentAccount.getId() == id) {
//                Account changedAccount = new Account(
//                        id,
//                        account.getName(),
//                        account.getSurname(),
//                        account.getEmail(),
//                        account.getPassword()
//                );
//                allAccounts.add(changedAccount.toString());
//            } else {
//                allAccounts.add(user);
//            }
//        }
//        rewriteDatabase(allAccounts);



//
//    public static void editAccountInDatabase(int id, Account account) {
//        List<String> allAccounts = new ArrayList<>();
//
//        Scanner scanner = loadDatabaseIntoScanner(USER_DATABASE_FILE);
//
//        while (scanner.hasNextLine()) {
//            String user = scanner.nextLine();
//            Account currentAccount = convertDatabaseStringToAccount(user);
//            if (currentAccount.getId() == id) {
//                Account changedAccount = new Account(
//                        id,
//                        account.getName(),
//                        account.getSurname(),
//                        account.getEmail(),
//                        account.getPassword()
//                );
//                allAccounts.add(changedAccount.toString());
//            } else {
//                allAccounts.add(user);
//            }
//        }
//        rewriteDatabase(allAccounts);
//    }


    private ReservedFlight getReservedFlightFromParameters(String[] reservedFlightParameters) {
        Account account = getAccountById(Integer.parseInt(reservedFlightParameters[ACCOUNT_INDEX]));
        Airline airline = AirlineFactory.createAirline(reservedFlightParameters[AIRLINE_NAME_INDEX]);
        Airport departureAirport = AirportFactory.createAirport(reservedFlightParameters[DEPARTURE_AIRPORT_IATA_INDEX]);
        Airport landingAirport = AirportFactory.createAirport(reservedFlightParameters[LANDING_AIRPORT_IATA_INDEX]);
        Route route = new Route(airline, departureAirport,landingAirport);

        Calendar departureDate = new GregorianCalendar();
        departureDate.setTimeInMillis(Long.parseLong(reservedFlightParameters[DEPARTURE_DATE_INDEX]));

        Calendar landingDate = new GregorianCalendar();
        landingDate.setTimeInMillis(Long.parseLong((reservedFlightParameters[LANDING_DATE_INDEX])));
        int hours = Integer.parseInt(reservedFlightParameters[FLIGHT_HOURS_INDEX]);
        int minutes = Integer.parseInt(reservedFlightParameters[FLIGHT_MINUTES_INDEX]);

        TimeInterval flightTime = new TimeInterval(new int[]{hours, minutes});

        Price price = new Price(Double.parseDouble(reservedFlightParameters[PRICE_INDEX]));
        int amount = Integer.parseInt(reservedFlightParameters[AMOUNT_INDEX]);

        Flight flight = FlightFactory.createFlightWithSetParameters(route, departureDate, flightTime, landingDate, price);

        ReservedFlight reservedFlight = new ReservedFlight(flight,account,amount);
        return reservedFlight;
    }

    private String reservedFlightToDatabaseString(ReservedFlight reservedFlight) {
        Account account = reservedFlight.getAccount();
        Flight flight = reservedFlight.getFlight();
        int amount = reservedFlight.getAmount();
        Route route = flight.getRoute();
        return account.getId() +"|"
                + route.getSourceAirport().getIATA() + "|"
                + route.getDestinationAirport().getIATA() + "|"
                + route.getAirline().getName() + "|"
                + flight.getDepartureDate().getTime().getTime() + "|"
                + flight.getFlightTime().getHours() + "|"
                + flight.getFlightTime().getMinutes() + "|"
                + flight.getLandingDate().getTime().getTime() + "|"
                + flight.getPrice().getPrice() + "|"
                + amount;
    }
}