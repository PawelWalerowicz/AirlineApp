package model;

import java.util.List;

import static controllers.aerial.AerialDatabaseController.getAirportsFromDatabase;
import static java.lang.Double.parseDouble;

public class Airport {
    private String name;
    private City city;
    private String IATA;
    private Country country;
    private Geolocation geolocation;
    private double altitude;

    private final int NAME_POSITION = 1;
    private final int CITY_POSITION = 2;
    private final int COUNTRY_POSITION = 3;
    private final int IATA_POSITION = 4;
    private final int LATITUDE_POSITION = 6;
    private final int LONGITUDE_POSITION = 7;
    private final int ALTITUDE_POSITION = 8;

    private final int IATA_LENGTH=3;


    public Airport(String nameOrIATA) {
        if(nameOrIATA.length()>IATA_LENGTH) {
            this.name = nameOrIATA;
        } else {
            this.IATA = nameOrIATA;
            this.name = getNameByIATA(IATA);
        }
        getOtherParametersByName(name);
    }

    private String getNameByIATA(String iata) {
        List<String[]> allAirports = getAirportsFromDatabase();
        for(String [] airport:allAirports) {
            if(airport[IATA_POSITION].toLowerCase().equals(iata.toLowerCase())) {
                name = airport[NAME_POSITION];
            }
        }
        return name;
    }

    private void getOtherParametersByName(String name) {
        List<String[]> allAirports = getAirportsFromDatabase();
        for(String [] airport:allAirports) {
            if(airport[NAME_POSITION].toLowerCase().equals(name.toLowerCase())) {
                this.city = new City(airport[CITY_POSITION]);
                this.IATA = airport[IATA_POSITION];
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
}
