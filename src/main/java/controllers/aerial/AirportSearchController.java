package controllers.aerial;

import model.Airport;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.Airport.getAirportsInCity;
import static model.Airport.getAirportsInCountry;
import static model.Country.isCity;
import static model.Country.isCountry;
import static utilities.InputOutputTools.capitaliseFirstLetter;
import static utilities.InputValidator.checkForQuit;

public class AirportSearchController {
    private boolean proceed=true;

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
            } while (proceed && !isAirportOrCountryValid(input));
        }

        if (proceed) {
            if (isCountry(input) || isCity(input)) {
                if(isCountry(input)) {
                    System.out.println("Listing all airports in " + input + ", it may take a while, please be patient.");
                    airports = getAirportsInCountry(input);

                } else {
                    airports = getAirportsInCity(input);
                }
                System.out.println(airports.size() + " airports found in " + input + ".");
            } else {
                Airport airport = new Airport(input);
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


    public static boolean isAirportOrCountryValid(String name) {
        boolean isCorrect=false;
        if (isCountry(name)) {
            isCorrect = true;
        } else if (isCity(name)) {
            isCorrect = true;
        } else {
            Airport airport = new Airport(name);
            isCorrect = airport.airportExist();
        }
        if(!isCorrect) {
            System.out.println("Airport/City/Country not found, please try again.");
        }
        return isCorrect;
    }


    public boolean getProceed() {
        return proceed;
    }

    public void setProceed(boolean proceed) {
        this.proceed = proceed;
    }
}
