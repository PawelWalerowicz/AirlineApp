package model;

import javax.print.attribute.standard.MediaSize;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static controllers.aerial.AerialDatabaseController.getAirportsFromDatabase;
import static java.lang.Double.parseDouble;
import static utilities.InputOutputTools.loadDatabaseIntoScanner;
import static utilities.ResourcesIndex.AIRPORTS_DATABASE_FILE;
import static utilities.ResourcesIndex.USER_DATABASE_FILE;

public class Airport {
    private String name;
    private City city;
    private String IATA;
    private String ICAO;
    private Country country;
    private Geolocation geolocation;
    private double timeZoneOffset;
    private double altitude;

    private static final int NAME_POSITION = 1;
    private final int CITY_POSITION = 2;
    private static final int COUNTRY_POSITION = 3;
    private final int IATA_POSITION = 4;
    private final int ICAO_POSITION = 5;
    private final int LATITUDE_POSITION = 6;
    private final int LONGITUDE_POSITION = 7;
    private final int ALTITUDE_POSITION = 8;
    private final int TIMEZONE_OFFSET_POSITION = 9;

    private final int IATA_LENGTH = 3;
    private final int ICAO_LENGTH = 4;

    public Airport(String nameOrIATA) {
        if (isInputAName(nameOrIATA)) {
            this.name = nameOrIATA;
        } else if (isInputAIATA(nameOrIATA)) {
            this.IATA = nameOrIATA;
            this.name = getNameByIATA();
        }

        if (airportExist()) {
            getOtherParametersByName(name);
        } else {
            getNameByInvalidICAO(nameOrIATA);
            getOtherParametersByName(name);
        }
    }

    private boolean isInputAName(String input) {
        return input.length() > ICAO_LENGTH;
    }

    private boolean isInputAIATA(String input) {
        return input.length() == IATA_LENGTH;
    }

    private boolean isInputAICAO(String input) {
        return input.length() == ICAO_LENGTH;
    }

    private boolean airportExist() {
        List<String[]> allAirports = getAirportsFromDatabase();
        for (String[] airport : allAirports) {
            if (airport[NAME_POSITION].equalsIgnoreCase(this.name)) {
                return true;
            }
        }
        return false;
    }

    private String getNameByIATA() {
        List<String[]> allAirports = getAirportsFromDatabase();

        for (String[] airport : allAirports) {
            if (airport[IATA_POSITION].equalsIgnoreCase(this.IATA)) {
                name = airport[NAME_POSITION];
            }
        }
        return name;
    }

    //bruteforce solution for mistakes in Routes database
    private String getNameByInvalidICAO(String invalidICAO) {
        List<String[]> allAirports = getAirportsFromDatabase();
        for (String[] airport : allAirports) {
            if (airport[ICAO_POSITION].substring(1).equalsIgnoreCase(invalidICAO) || airport[ICAO_POSITION].substring(0, 2).equalsIgnoreCase(invalidICAO)) {
                name = airport[NAME_POSITION];
            } else {
                name = "Rustaq Airport";
            }

        }
        return name;
    }

    private void getOtherParametersByName(String name) {
        List<String[]> allAirports = getAirportsFromDatabase();
        for (String[] airport : allAirports) {
            if (airport[NAME_POSITION].equalsIgnoreCase(name)) {
                this.city = new City(airport[CITY_POSITION]);
                this.IATA = airport[IATA_POSITION];
                this.ICAO = airport[ICAO_POSITION];
                this.country = new Country(airport[COUNTRY_POSITION]);
                this.geolocation = new Geolocation(airport[LATITUDE_POSITION], airport[LONGITUDE_POSITION]);
                this.altitude = parseDouble(airport[ALTITUDE_POSITION]);
                this.timeZoneOffset = parseDouble(airport[TIMEZONE_OFFSET_POSITION]);
            }
        }
    }


    public static List<Airport> reciteAirportsInCountry(String countryName) {
        Country country = new Country(countryName);
        List<String[]> allAirports = getAirportsFromDatabase();
        List<Airport> airportsInCountry = new ArrayList<>();
        for (String[] airport : allAirports) {
            if (airport[COUNTRY_POSITION].equalsIgnoreCase(country.getName())) {
                Airport currentAirport = new Airport(airport[NAME_POSITION]);
                airportsInCountry.add(currentAirport);
            }
        }
        return airportsInCountry;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        String toString;
        if (IATA.toString().equals("\\N")) {
            toString = name + " (" + country.getName() + ", " + ICAO + ")";
        } else {
            toString = name + " (" + country.getName() + ", " + IATA + ")";
        }

        return toString;
    }
}
