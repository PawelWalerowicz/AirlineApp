package model;

import model.Geolocation;

public class Distance {
    Geolocation geolocation1;
    Geolocation geolocation2;
    double distance;

    public Distance(Geolocation geolocation1, Geolocation geolocation2) {
        this.geolocation1=geolocation1;
        this.geolocation2=geolocation2;
        this.distance = calculateDistance(geolocation1, geolocation2);
    }

    public Distance(double distanceInKm) {
        this.distance = distanceInKm;
    }

    //calculates orthodromic distance in kilometers
    public static double calculateDistance(Geolocation point1, Geolocation point2) {
        double earthRadius = 6371; //km
        double latitude1 = Math.toRadians(point1.getLatitude());
        double latitude2 = Math.toRadians(point2.getLatitude());
        double longitude1 = Math.toRadians(point1.getLongitude());
        double longitude2 = Math.toRadians(point2.getLongitude());
        double deltaLongitude = Math.abs(longitude1 - longitude2);
        double angularDistance;
        angularDistance = Math.acos(
                (Math.sin(latitude1) * Math.sin(latitude2))
                        + (Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(deltaLongitude))
        );

        return roundDistanceToWholeKilometers(angularDistance * earthRadius);
    }

    public static double roundDistanceToWholeKilometers(double distance) {
        int numberOfDecimalPlaces = 1;
        double decimalOperator = Math.pow(10, numberOfDecimalPlaces);
        return (Math.round(distance * decimalOperator)) / decimalOperator;
    }

    public Geolocation getGeolocation1() {
        return geolocation1;
    }

    public void setGeolocation1(Geolocation geolocation1) {
        this.geolocation1 = geolocation1;
    }

    public Geolocation getGeolocation2() {
        return geolocation2;
    }

    public void setGeolocation2(Geolocation geolocation2) {
        this.geolocation2 = geolocation2;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return distance + " km";
    }
}

