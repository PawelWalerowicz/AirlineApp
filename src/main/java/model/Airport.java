package model;

import model.Codes.IATA;
import model.Codes.ICAO;

import java.util.List;

import static controllers.aerial.AerialDatabaseController.getAirportsFromDatabase;
import static java.lang.Double.parseDouble;

public class Airport {
    private String name;
    private City city;
    private IATA IATA;
    private ICAO ICAO;
    private Country country;
    private Geolocation geolocation;
    private double altitude;

    private final int NAME_POSITION = 1;
    private final int CITY_POSITION = 2;
    private final int COUNTRY_POSITION = 3;
    private final int IATA_POSITION = 4;
    private final int ICAO_POSITION = 5;
    private final int LATITUDE_POSITION = 6;
    private final int LONGITUDE_POSITION = 7;
    private final int ALTITUDE_POSITION = 8;

    private final int IATA_LENGTH=3;
    private final int ICAO_LENGTH=4;

    //PROBLEM WITH INVALID CODES FOR AIRPORTS IN ROUTES - when there is no IATA for airport, it uses substring of ICAO
    public Airport(String nameOrIATA) {
        if(nameOrIATA.length()>IATA_LENGTH) {
            this.name = nameOrIATA;
        } else {
            this.IATA = new IATA(nameOrIATA);
            this.name = getNameByIATA();
        }

        if(isValidAirport()) {
            getOtherParametersByName(name);
        } else {
            getNameByInvalidICAO(nameOrIATA);
            getOtherParametersByName(name);
//            System.out.println("Invalid airport!"); //todo: figure out real exception handling
        }

    }

    public Airport(IATA iata) {
        this.IATA = iata;
        this.name = getNameByIATA();
        if(isValidAirport()) {
            getOtherParametersByName(name);
        } else {
            System.out.println("Invalid airport!"); //todo: figure out real exception handling
        }

    }

    private boolean isValidAirport() {
        List<String[]> allAirports = getAirportsFromDatabase();
        for(String [] airport:allAirports) {
            if(airport[NAME_POSITION].equalsIgnoreCase(this.name)) {
                return true;
            }
        }
        return false;
    }

    private String getNameByIATA() {
        List<String[]> allAirports = getAirportsFromDatabase();

        for(String [] airport:allAirports) {
            if(airport[IATA_POSITION].equalsIgnoreCase(this.IATA.toString())) {
                name = airport[NAME_POSITION];
            }
        }
        return name;
    }

    //bruteforce solution for mistakes in Routes database
    private String getNameByInvalidICAO(String invalidICAO) {
        List<String[]> allAirports = getAirportsFromDatabase();
        for(String [] airport:allAirports) {
            if(airport[ICAO_POSITION].substring(1).equalsIgnoreCase(invalidICAO) || airport[ICAO_POSITION].substring(0,2).equalsIgnoreCase(invalidICAO) ) {
                name = airport[NAME_POSITION];
            } else name="Rustaq Airport";

        }
        return name;
    }


    private void getOtherParametersByName(String name) {
        List<String[]> allAirports = getAirportsFromDatabase();
        for(String [] airport:allAirports) {
            if(airport[NAME_POSITION].equalsIgnoreCase(name)) {
                this.city = new City(airport[CITY_POSITION]);
                this.IATA = new IATA(airport[IATA_POSITION]);
                this.ICAO = new ICAO(airport[ICAO_POSITION]);
                this.country = new Country(airport[COUNTRY_POSITION]);
                this.geolocation = new Geolocation(airport[LATITUDE_POSITION],airport[LONGITUDE_POSITION]);
                this.altitude = parseDouble(airport[ALTITUDE_POSITION]);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IATA getIATA() {
        return IATA;
    }

    public void setIATA(IATA IATA) {
        this.IATA = IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = new IATA(IATA);
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
        if(IATA.toString().equals("\\N")) {
            toString = name + " (" + country.getName() + ", " + ICAO + ")";
        } else {
            toString = name + " (" + country.getName() + ", " + IATA + ")";
        }

        return toString;
    }
}
