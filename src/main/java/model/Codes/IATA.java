package model.Codes;

public class IATA implements Code {
    String IATA;

    public IATA(String IATA) {
        this.IATA = IATA;
    }

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    @Override
    public String toString() {
        return getIATA();
    }
}
