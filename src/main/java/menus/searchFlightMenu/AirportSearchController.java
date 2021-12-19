package menus.searchFlightMenu;

import menus.Singleton;
import model.AirlineFactory;
import model.Airport;
import model.AirportFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.AirportFactory.airportExist;
import static model.CountryFactory.isCity;
import static model.CountryFactory.isCountry;
import static utilities.InputOutputTools.capitaliseFirstLetter;
import static utilities.InputValidator.checkForQuit;

public class AirportSearchController implements Singleton {
    private boolean proceed=true;

    private static AirportSearchController airportSearchControllerInstance;

    public static AirportSearchController getInstance() {
        if(airportSearchControllerInstance==null) {
            airportSearchControllerInstance = new AirportSearchController();
        }
        return airportSearchControllerInstance;
    }

    private AirportSearchController() {}


    public List<Airport> askForAirports(String departureOrLanding) {
        Scanner scanner = new Scanner(System.in);
        List<Airport> airports = new ArrayList<>();
        String input = null;
        if (proceed) {
            do {
                System.out.print(departureOrLanding + " airport (name or IATA code), or " + departureOrLanding.toLowerCase() + " city or country (or Q to quit): ");
                input = scanner.nextLine();
                input = capitaliseFirstLetter(input);
                proceed = checkForQuit(input);
            } while (proceed && !isAirportOrCountryorCityValid(input));
        }
        if (proceed) {
            if (isCountry(input) || isCity(input)) {
                if(isCountry(input)) {
                    System.out.println("Listing all airports in " + input + ", it may take a while, please be patient.");
                    airports = AirportFactory.getAirportsInCountry(input);

                } else {
                    airports = AirportFactory.getAirportsInCity(input);
                }
                System.out.println(airports.size() + " airports found in " + input + ".");
            } else {
                Airport airport = AirportFactory.createAirport(input);
                if(airport.airportExist()) {
                    airports.add(airport);
                } else {
                    System.out.println("Airport/City/Country not found, please try again.");
                    askForAirports(departureOrLanding);
                }
            }
        }

        return airports;
    }


    public static boolean isAirportOrCountryorCityValid(String name) {
        boolean isCorrect=false;
        if (isCountry(name)) {
            return true;
        } else if (isCity(name)) {
            return true;
        } else if(airportExist(name)){
            return true;
        } else {
            System.out.println("Airport/City/Country not found, please try again.");
            return false;
        }
    }


    public boolean getProceed() {
        return proceed;
    }

    public void setProceed(boolean proceed) {
        this.proceed = proceed;
    }
}
