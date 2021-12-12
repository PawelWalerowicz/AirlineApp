package model;


import java.util.List;

import static database.aerial.AerialDatabaseController.getCitiesFromDatabase;
import static database.aerial.AerialDatabaseController.getCountriesFromDatabase;

public class Country {
    String name;
    String isoCode;

    private static final int NAME_POSITION = 0;
    private final int ISO_CODE_POSITION = 1;
    private final int ISO_CODE_LENGTH = 2;

    //todo: figure out exception handling
    public Country(String nameOrIsocode) {
        if(nameOrIsocode.length()>ISO_CODE_LENGTH) {
            this.name = nameOrIsocode;
            this.isoCode = getIsoCodeByName();
        } else {
            this.isoCode = nameOrIsocode;
            this.name = getNameByIsoCode();
        }
    }


    private String getIsoCodeByName() {
        List<String[]> countriesList = getCountriesFromDatabase();
        String isoCode=null;
        for(String[] country: countriesList) {
            if(country[NAME_POSITION].equals(name)) {
                isoCode=country[ISO_CODE_POSITION];
            }
        }

        return isoCode;
    }

    private String getNameByIsoCode() {
        List<String[]> countriesList = getCountriesFromDatabase();
        String name="";
        for(String[] country: countriesList) {
            if(country[ISO_CODE_POSITION].equalsIgnoreCase(isoCode)) {
                name=country[NAME_POSITION];
            }
        }
        return name;
    }

    public static boolean isCountry(String input) {
        List<String[]> countriesList = getCountriesFromDatabase();
        boolean answer= false;
        for(String[] country: countriesList) {
            if (country[NAME_POSITION].equalsIgnoreCase(input)) {
                answer = true;
                break;
            }
        }
        return answer;
    }


    public static boolean isCity(String input) {
        List<String> citiesList = getCitiesFromDatabase();
        boolean answer= false;
        for(String city: citiesList) {
            if(city.equalsIgnoreCase(input)) {
                answer = true;
                break;
            }
        }
        return answer;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    @Override
    public String toString() {
        return name + " (" + isoCode + ")";
    }
}
