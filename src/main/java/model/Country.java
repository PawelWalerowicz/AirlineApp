package model;

import model.Codes.IsoCode;

import java.util.List;

import static controllers.aerial.AerialDatabaseController.getCountriesFromDatabase;

public class Country {
    String name;
    IsoCode isoCode;

    private final int NAME_POSITION = 0;
    private final int ISO_CODE_POSITION = 1;

    //todo: figure out exception handling
    public Country(String name) {
        this.name = name;
        this.isoCode = getIsoCodeByName();
    }

    public Country(IsoCode isoCode) {
        this.isoCode = isoCode;
        this.name = getNameByIsoCode();
    }

    private IsoCode getIsoCodeByName() {
        List<String[]> countriesList = getCountriesFromDatabase();
        IsoCode isoCode=null;
        for(String[] country: countriesList) {
            if(country[NAME_POSITION].equals(name)) {
                isoCode=new IsoCode(country[ISO_CODE_POSITION]);
            }
        }

        return isoCode;
    }

    private String getNameByIsoCode() {
        List<String[]> countriesList = getCountriesFromDatabase();
        String name="";
        for(String[] country: countriesList) {
            if(country[ISO_CODE_POSITION].equalsIgnoreCase(isoCode.toString())) {
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

    public IsoCode getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(IsoCode isoCode) {
        this.isoCode = isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = new IsoCode(isoCode);
    }

    @Override
    public String toString() {
        return name + " (" + isoCode + ")";
    }
}
