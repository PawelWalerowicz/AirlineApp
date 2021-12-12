package model;

import java.util.List;

import static database.aerial.AerialDatabaseController.getAirlinesFromDatabase;

public class Airline {
    String name;
    String IATA;
    String ICAO;

    private static final int NAME_POSITION = 1;
    private static final int IATA_POSITION = 3;
    private static final int ICAO_POSITION = 4;

    private static final int IATA_LENGTH = 2;
    private static final int ICAO_LENGTH = 3;


    public Airline(String name, String IATA, String ICAO) {
        this.name = name;
        this.IATA = IATA;
        this.ICAO = ICAO;
    }

    public static Airline createAirline(String NAMEorIATAorICAO) {
        Airline airline=null;
        if(isInputAName(NAMEorIATAorICAO)) {
            airline = createAirlineFromName(NAMEorIATAorICAO);
        } else if(isInputAIATA(NAMEorIATAorICAO)) {
            airline = createAirlineFromIATA(NAMEorIATAorICAO);
        } else if(isInputAICAO(NAMEorIATAorICAO)) {
            airline = createAirlineFromICAO(NAMEorIATAorICAO);
        }
        return airline;
    }

    private static boolean isInputAName(String input) {
        return input.length() > ICAO_LENGTH;
    }

    private static boolean isInputAIATA(String input) {
        return input.length() == IATA_LENGTH;
    }

    private static boolean isInputAICAO(String input) {
        return input.length() == ICAO_LENGTH;
    }

    private static Airline createAirlineFromName(String name) {
        String IATA = getIATAFromName(name);
        String ICAO = getICAOFromName(name);
        return new Airline(name, IATA, ICAO);
    }

    private static Airline createAirlineFromIATA(String IATA) {
        String name = getNameFromIATA(IATA);
        String ICAO = getICAOFromName(name);
        return new Airline(name, IATA, ICAO);
    }

    private static Airline createAirlineFromICAO(String ICAO) {
        String name = getNameFromICAO(ICAO);
        String IATA = getIATAFromName(name);
        return new Airline(name, IATA, ICAO);
    }

    private static String getIATAFromName(String name) {
        List<String[]> allAirlines = getAirlinesFromDatabase();
        String IATA = null;
        for (String[] airline : allAirlines) {
            if (airline[NAME_POSITION].equalsIgnoreCase(name)) {
                IATA = airline[IATA_POSITION];
            }
        }
        return IATA;
    }

    private static String getICAOFromName(String name) {
        List<String[]> allAirlines = getAirlinesFromDatabase();
        String ICAO = null;
        for (String[] airline : allAirlines) {
            if (airline[NAME_POSITION].equalsIgnoreCase(name)) {
                ICAO = airline[ICAO_POSITION];
            }
        }
        return ICAO;
    }

    private static String getNameFromIATA(String IATA) {
        List<String[]> allAirlines = getAirlinesFromDatabase();
        String name = "";
        for (String[] airline : allAirlines) {
            if (airline[IATA_POSITION].equalsIgnoreCase(IATA)) {
                name = airline[NAME_POSITION];
            }
        }
        return name;
    }

    private static String getNameFromICAO(String ICAO) {
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
