package model;


import java.util.List;

import static database.aerial.AerialDatabaseController.getCitiesFromDatabase;
import static database.aerial.AerialDatabaseController.getCountriesFromDatabase;

public class Country {
    String name;
    String isoCode;

    public Country(String name) {
        this.name = name;
        this.isoCode = CountryFactory.getIsoCodeByName(name);
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
