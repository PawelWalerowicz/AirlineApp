package model;

import java.util.List;

import static database.aerial.AerialDatabaseController.getAirlinesFromDatabase;

public class Airline {
    String name;
    String IATA;
    String ICAO;

    public Airline(String name, String IATA, String ICAO) {
        this.name = name;
        this.IATA = IATA;
        this.ICAO = ICAO;
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

    public String getIATAorICAO() {
        if (hasIATA()) {
            return getIATA();
        } else
            return getICAO();
    }

    public boolean hasIATA() {
        return !this.IATA.equals("");
    }

    public boolean hasICAO() {
        return !this.ICAO.equals("");
    }

    @Override
    public String toString() {
        return getName();
    }
}
