package model.Codes;

public class IsoCode implements Code {
    String isoCode;

    public IsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    @Override
    public String toString() {
        return getIsoCode();
    }
}
