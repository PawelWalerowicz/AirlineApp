package model;

import java.util.ArrayList;
import java.util.List;

import static database.aerial.AerialDatabaseController.getAirportsFromDatabase;
import static java.lang.Double.parseDouble;

public class AirportFactory {

    private static final int NAME_POSITION = 1;
    private static final int CITY_POSITION = 2;
    private static final int COUNTRY_POSITION = 3;
    private static final int IATA_POSITION = 4;
    private static final int ICAO_POSITION = 5;
    private static final int LATITUDE_POSITION = 6;
    private static final int LONGITUDE_POSITION = 7;
    private static final int ALTITUDE_POSITION = 8;
    private static final int TIMEZONE_OFFSET_POSITION = 9;

    private static final int IATA_LENGTH = 3;
    private static final int ICAO_LENGTH = 4;

    public static Airport createAirport(String nameOrIATA) {
        String name = null;
        Airport airport = new Airport();
        if (isInputAName(nameOrIATA)) {
            name = nameOrIATA;
        } else if (isInputAIATA(nameOrIATA)) {
            name = getNameByIATA(nameOrIATA);
        }
        if (!airportExist(name)) {
            name = getNameByInvalidICAO(nameOrIATA);
        }

        if(airportExist(name)) {
            String[] airportParameters = getAirportParameters(name);
            airport.setName(name);
            airport.setCity(new City(airportParameters[CITY_POSITION]));
            airport.setIATA(airportParameters[IATA_POSITION]);
            airport.setICAO(airportParameters[ICAO_POSITION]);
            airport.setCountry(new Country(airportParameters[COUNTRY_POSITION]));
            airport.setGeolocation(new Geolocation(airportParameters[LATITUDE_POSITION], airportParameters[LONGITUDE_POSITION]));
            airport.setAltitude(parseDouble(airportParameters[ALTITUDE_POSITION]));
            airport.setTimeZoneOffset(parseDouble(airportParameters[TIMEZONE_OFFSET_POSITION]));
        }

        return airport;
    }


    private static boolean isInputAName(String input) {
        return input.length() > ICAO_LENGTH;
    }

    private static boolean isInputAIATA(String input) {
        return input.length() == IATA_LENGTH;
    }

    private boolean isInputAICAO(String input) {
        return input.length() == ICAO_LENGTH;
    }

    public static boolean airportExist(String name) {
        List<String[]> allAirports = getAirportsFromDatabase();
        for (String[] airport : allAirports) {
            if (airport[NAME_POSITION].equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private static String getNameByIATA(String IATA) {
        List<String[]> allAirports = getAirportsFromDatabase();
        String name=null;
        for (String[] airport : allAirports) {
            if (airport[IATA_POSITION].equalsIgnoreCase(IATA)) {
                name = airport[NAME_POSITION];
            }
        }
        return name;
    }

    //bruteforce solution for mistakes in Routes database
    private static String getNameByInvalidICAO(String invalidICAO) {
        List<String[]> allAirports = getAirportsFromDatabase();
        String name=null;
        for (String[] airport : allAirports) {
            if (airport[ICAO_POSITION].substring(1).equalsIgnoreCase(invalidICAO) || airport[ICAO_POSITION].substring(0, 2).equalsIgnoreCase(invalidICAO)) {
                name = airport[NAME_POSITION];
            } else {
                name = "Rustaq Airport";
            }

        }
        return name;
    }

    private static String[] getAirportParameters(String name) {
        List<String[]> allAirports = getAirportsFromDatabase();
        String[] selectedAirport = new String[10];
        for (String[] airport : allAirports) {
            if (airport[NAME_POSITION].equalsIgnoreCase(name)) {
                selectedAirport = airport;
            }
        }
        return selectedAirport;
    }


    public static List<Airport> getAirportsInCountry(String countryName) {
        Country country = new Country(countryName);
        List<String[]> allAirports = getAirportsFromDatabase();
        List<Airport> airportsInCountry = new ArrayList<>();
        for (String[] airport : allAirports) {
            if (airport[COUNTRY_POSITION].equalsIgnoreCase(country.getName())) {
                Airport currentAirport = createAirport(airport[NAME_POSITION]);
                airportsInCountry.add(currentAirport);
            }
        }
        return airportsInCountry;
    }


    public static List<Airport> getAirportsInCity(String cityName) {
        List<String[]> allAirports = getAirportsFromDatabase();
        List<Airport> airportsInCity = new ArrayList<>();
        for (String[] airport : allAirports) {
            if (airport[CITY_POSITION].equalsIgnoreCase(cityName)) {
                Airport currentAirport = createAirport(airport[NAME_POSITION]);
                airportsInCity.add(currentAirport);
            }
        }
        return airportsInCity;
    }

}
