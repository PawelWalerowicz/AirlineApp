package model;

import java.util.List;

import static controllers.aerial.AerialDatabaseController.getCountriesFromDatabase;

public class Country {
    String name;
    String isoCode;

    private final int NAME_POSITION = 0;
    private final int ISO_CODE_POSITION = 1;

    private final int ISO_CODE_LENGTH = 2;

    public Country(String nameOrIso) {
        if(nameOrIso.length()>ISO_CODE_LENGTH) {
            createCountryByName(nameOrIso);
        } else {
            createCountryByIsoCode(nameOrIso);
        }
    }

    private void createCountryByName(String name) {
        this.name = name;
        this.isoCode = getIsoCodeByName(name);
    }


    private void createCountryByIsoCode(String isoCode) {
        this.isoCode = isoCode;
        this.name = getNameByIsoCode(isoCode);
    }


    private String getIsoCodeByName(String name) {
        List<String[]> countriesList = getCountriesFromDatabase();
        String isoCode="";
        for(String[] country: countriesList) {
            if(country[NAME_POSITION].equals(name)) {
                isoCode=country[ISO_CODE_POSITION];
            }
        }

        return isoCode;
    }

    private String getNameByIsoCode(String isoCode) {
        List<String[]> countriesList = getCountriesFromDatabase();
        String name="";
        for(String[] country: countriesList) {
            if(country[ISO_CODE_POSITION].equals(isoCode)) {
                name=country[NAME_POSITION];
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
