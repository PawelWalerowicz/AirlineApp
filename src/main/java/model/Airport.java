package model;

import java.util.*;

import static controllers.aerial.AerialDatabaseController.getAirportsFromDatabase;
import static java.lang.Double.parseDouble;

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
    private static final int CITY_POSITION = 2;
    private static final int COUNTRY_POSITION = 3;
    private final int IATA_POSITION = 4;
    private final int ICAO_POSITION = 5;
    private final int LATITUDE_POSITION = 6;
    private final int LONGITUDE_POSITION = 7;
    private final int ALTITUDE_POSITION = 8;
    private final int TIMEZONE_OFFSET_POSITION = 9;

    private final int IATA_LENGTH = 3;
    private final int ICAO_LENGTH = 4;

    public Airport() {
    }

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

    public static Airport createAirportByName(String name) {
        Airport airport = new Airport();
        airport.setName(name);
        airport.getOtherParametersByName(name);
        return airport;
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

    public boolean airportExist() {
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


    public static List<Airport> getAirportsInCountry(String countryName) {
        Country country = new Country(countryName);
        List<String[]> allAirports = getAirportsFromDatabase();
        List<Airport> airportsInCountry = new ArrayList<>();
        for (String[] airport : allAirports) {
            if (airport[COUNTRY_POSITION].equalsIgnoreCase(country.getName())) {
                Airport currentAirport = createAirportByName(airport[NAME_POSITION]);
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
                Airport currentAirport = createAirportByName(airport[NAME_POSITION]);
                airportsInCity.add(currentAirport);
            }
        }
        return airportsInCity;
    }

    public double getTimeZoneOffset() {
        return timeZoneOffset;
    }

    public void setTimeZoneOffset(double timeZoneOffset) {
        this.timeZoneOffset = timeZoneOffset;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
