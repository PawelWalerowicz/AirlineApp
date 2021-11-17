package model;

import java.util.List;

import static controllers.aerial.AerialDatabaseController.getAirlinesFromDatabase;

public class Airline {
    String name;
    String IATA;
    String ICAO;

    private final int NAME_POSITION = 1;
    private final int IATA_POSITION = 3;
    private final int ICAO_POSITION = 4;

    private final int IATA_LENGTH = 2;
    private final int ICAO_LENGTH = 3;


    public Airline(String name, String IATA, String ICAO) {
        this.name = name;
        this.IATA = IATA;
        this.ICAO = ICAO;
    }

    public Airline(String NAMEorIATAorICAO) {
        createAirline(NAMEorIATAorICAO);
    }

    public Airline createAirline(String input) {
        Airline airline=null;
        if(isInputAName(input)) {
            airline = createAirlineFromName(input);
        } else if(isInputAIATA(input)) {
            airline = createAirlineFromIATA(input);
        } else if(isInputAICAO(input)) {
            airline = createAirlineFromICAO(input);
        }
        return airline;
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

    private Airline createAirlineFromName(String name) {
        IATA = getIATAFromName(name);
        ICAO = getICAOFromName(name);
        return new Airline(name, IATA, ICAO);
    }

    private Airline createAirlineFromIATA(String IATA) {
        name = getNameFromIATA(IATA);
        ICAO = getICAOFromName(name);
        return new Airline(name, IATA, ICAO);
    }

    private Airline createAirlineFromICAO(String ICAO) {
        name = getNameFromICAO(ICAO);
        IATA = getIATAFromName(name);
        return new Airline(name, IATA, ICAO);
    }

    private String getIATAFromName(String name) {
        List<String[]> allAirlines = getAirlinesFromDatabase();
        String IATA = null;
        for (String[] airline : allAirlines) {
            if (airline[NAME_POSITION].equalsIgnoreCase(name)) {
                IATA = airline[IATA_POSITION];
            }
        }
        return IATA;
    }

    private String getICAOFromName(String name) {
        List<String[]> allAirlines = getAirlinesFromDatabase();
        String ICAO = null;
        for (String[] airline : allAirlines) {
            if (airline[NAME_POSITION].equalsIgnoreCase(name)) {
                ICAO = airline[ICAO_POSITION];
            }
        }
        return ICAO;
    }

    private String getNameFromIATA(String IATA) {
        List<String[]> allAirlines = getAirlinesFromDatabase();
        String name = "";
        for (String[] airline : allAirlines) {
            if (airline[IATA_POSITION].equalsIgnoreCase(IATA)) {
                name = airline[NAME_POSITION];
            }
        }
        return name;
    }

    private String getNameFromICAO(String ICAO) {
        List<String[]> allAirlines = getAirlinesFromDatabase();
        String name = "";
        for (String[] airline : allAirlines) {
            if (airline[ICAO_POSITION].equalsIgnoreCase(ICAO)) {
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
