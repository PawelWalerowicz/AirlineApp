package model;

import java.util.*;

import static database.aerial.AerialDatabaseController.getAirportsFromDatabase;
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

    public Airport() {
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

    public String getICAO() {
        return ICAO;
    }

    public void setICAO(String ICAO) {
        this.ICAO = ICAO;
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
        if (IATA.equals("\\N")) {
            toString = name + " (" + country.getName() + ", " + ICAO + ")";
        } else {
            toString = name + " (" + country.getName() + ", " + IATA + ")";
        }

        return toString;
    }
}
