package model;

import java.util.List;

import static database.aerial.AerialDatabaseController.getCitiesFromDatabase;
import static database.aerial.AerialDatabaseController.getCountriesFromDatabase;

public class CountryFactory {

    private static final int NAME_POSITION = 0;
    private static final int ISO_CODE_POSITION = 1;
    private static final int ISO_CODE_LENGTH = 2;

    //todo: figure out exception handling
    public Country createCountry(String nameOrIsocode) {
        String name;
        String isoCode;
        if(nameOrIsocode.length()>ISO_CODE_LENGTH) {
            name = nameOrIsocode;
        } else {
            isoCode = nameOrIsocode;
            name = getNameByIsoCode(isoCode);
        }
        return new Country(name);
    }


    public static String getIsoCodeByName(String name) {
        List<String[]> countriesList = getCountriesFromDatabase();
        String isoCode=null;
        for(String[] country: countriesList) {
            if(country[NAME_POSITION].equals(name)) {
                isoCode=country[ISO_CODE_POSITION];
            }
        }

        return isoCode;
    }

    public static String getNameByIsoCode(String isoCode) {
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



}
