package model;

import model.Codes.Code;
import model.Codes.IATA;
import model.Codes.ICAO;

import java.util.List;

import static controllers.aerial.AerialDatabaseController.getAirlinesFromDatabase;

public class Airline {
    String name;
    IATA IATA;
    ICAO ICAO;


    private final int NAME_POSITION = 1;
    private final int IATA_POSITION = 3;
    private final int ICAO_POSITION = 4;

    private final int IATA_LENGTH = 2;
    private final int ICAO_LENGTH = 3;


    public Airline(String NAMEorIATAorICAO) {
        if (NAMEorIATAorICAO.length() > ICAO_LENGTH) {
            this.name = NAMEorIATAorICAO;
            this.IATA = getIATAFromName();
            this.ICAO = getICAOFromName();
        } else if (NAMEorIATAorICAO.length() == ICAO_LENGTH) {
            this.ICAO = new ICAO(NAMEorIATAorICAO);
            this.name = getNameFromICAO();
            this.IATA = getIATAFromName();
        } else if (NAMEorIATAorICAO.length() == IATA_LENGTH){
            this.IATA = new IATA(NAMEorIATAorICAO);
            this.name = getNameFromIATA();
            this.ICAO = getICAOFromName();
        }
    }

    public Airline(IATA IATA) {
        this.IATA = IATA;
        this.name = getNameFromIATA();
        this.ICAO = getICAOFromName();
    }

    public Airline(ICAO ICAO) {
        this.ICAO = ICAO;
        this.name = getNameFromICAO();
        this.IATA = getIATAFromName();
    }


    private IATA getIATAFromName() {
        List<String[]> allAirlines = getAirlinesFromDatabase();
        IATA iata = null;
        for (String[] airline : allAirlines) {
            if (airline[NAME_POSITION].equalsIgnoreCase(this.name)) {
                iata = new IATA(airline[IATA_POSITION]);
            }
        }
        return iata;
    }


    private ICAO getICAOFromName() {
        List<String[]> allAirlines = getAirlinesFromDatabase();
        ICAO icao = null;
        for (String[] airline : allAirlines) {
            if (airline[NAME_POSITION].equalsIgnoreCase(this.name)) {
                icao = new ICAO(airline[ICAO_POSITION]);
            }
        }
        return icao;
    }


    private String getNameFromIATA() {
        List<String[]> allAirlines = getAirlinesFromDatabase();
        String name = "";
        for (String[] airline : allAirlines) {
            if (airline[IATA_POSITION].equalsIgnoreCase(this.IATA.toString())) {
                name = airline[NAME_POSITION];
            }
        }
        return name;
    }


    private String getNameFromICAO() {
        List<String[]> allAirlines = getAirlinesFromDatabase();
        String name = "";
        for (String[] airline : allAirlines) {
            if (airline[ICAO_POSITION].equalsIgnoreCase(this.ICAO.toString())) {
                name = airline[NAME_POSITION];
            }
        }
        return name;
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

    public ICAO getICAO() {
        return ICAO;
    }

    public void setICAO(ICAO ICAO) {
        this.ICAO = ICAO;
    }

    public void setICAO(String ICAO) {
        this.ICAO = new ICAO(ICAO);
    }

    public Code getIATAorICAO() {
        if (hasIATA()) {
            return getIATA();
        } else
            return getICAO();
    }

    public boolean hasIATA() {
        return !this.IATA.toString().equals("");
    }

    public boolean hasICAO() {
        return !this.ICAO.toString().equals("");
    }


    @Override
    public String toString() {
        return getName();
    }
}
