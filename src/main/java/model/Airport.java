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
    public Airport(String NAMEorIATAorICAO) {
        System.out.println(NAMEorIATAorICAO);
        if(NAMEorIATAorICAO.length()>ICAO_LENGTH) {
            this.name = NAMEorIATAorICAO;
        }else if(NAMEorIATAorICAO.equals("\\N") || NAMEorIATAorICAO.length()==ICAO_LENGTH) {
            this.ICAO = new ICAO(NAMEorIATAorICAO);
            this.name = getNameByICAO();
        } else {
            this.IATA = new IATA(NAMEorIATAorICAO);
            this.name = getNameByIATA();
        }

        if(isValidAirport()) {
            getOtherParametersByName(name);
        } else {
            System.out.println("Invalid airport!"); //todo: figure out real exception handling
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


    private String getNameByICAO() {
        List<String[]> allAirports = getAirportsFromDatabase();
        for(String [] airport:allAirports) {
            if(airport[ICAO_POSITION].equalsIgnoreCase(this.ICAO.toString())) {
                name = airport[NAME_POSITION];
            } else if (airport[ICAO_POSITION].substring(1).equalsIgnoreCase(this.ICAO.toString())) {
                name = airport[NAME_POSITION];
            }
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
        return  name + " (" + country.getName() + ", " + IATA + ")";
    }
}
