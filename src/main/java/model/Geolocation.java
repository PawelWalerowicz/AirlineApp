package model;

import static java.lang.Double.parseDouble;

public class Geolocation {
    double latitude;
    double longitude;

    public Geolocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Geolocation(String latitude, String longitude) {
        this.latitude = parseDouble(latitude);
        this.longitude = parseDouble(longitude);
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Latitude: " + latitude +
                ", longitude: " + longitude;
    }
}
