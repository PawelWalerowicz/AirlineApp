package model.Codes;

public class ICAO implements Code {
    String ICAO;

    public ICAO(String ICAO) {
        this.ICAO = ICAO;
    }

    public String getICAO() {
        return ICAO;
    }

    public void setICAO(String ICAO) {
        this.ICAO = ICAO;
    }

    @Override
    public String toString() {
        return getICAO();
    }
}
